package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.cleaningapp.BackstageActivity
import com.example.cleaningapp.CleanerActivity
import com.example.cleaningapp.CustomerActivity
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.AccountBackstage
import com.example.cleaningapp.databinding.FragmentRonaLoginBinding
import com.example.cleaningapp.login.viewModel.LoginViewModel
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.messaging.FirebaseMessaging

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentRonaLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var client: GoogleSignInClient
    private val myTag = "TAG_${javaClass.simpleName}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            // 要求輸入email
            .requestEmail()
            .build()
        client = GoogleSignIn.getClient(requireActivity(), options)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().findViewById<Toolbar>(R.id.login_toolbar).visibility = View.GONE
        requireActivity().title = "會員登入"
        val viewModel: LoginViewModel by viewModels()
        binding = FragmentRonaLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            // 一般登入
            btnLoginLogin.setOnClickListener { view ->
                if (!inputCheck()) {
                    return@setOnClickListener
                }
                val position = spnLoginStatus.selectedItemPosition
                FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { token ->
                            if (position == 0) {
                                val url =
                                    "AccountCustomer/login/"
                                requestTask<CustomerSharePreferencesUtils.ApiCustomerModel>(
                                    "$url${viewModel?.account?.value}/${viewModel?.password?.value}/$token"
                                )?.let {
                                    if (it.suspend) {
                                        tvLoginErrMsg.text = "此帳號已停權"
                                    } else {
                                        CustomerSharePreferencesUtils.saveCustomerInfoFromPreferences(
                                            it
                                        )
                                        val intent =
                                            Intent(requireContext(), CustomerActivity::class.java)
                                        requireContext().startActivity(intent)
                                    }
                                    return@addOnCompleteListener
                                }
                                tvLoginErrMsg.text = "使用者帳號或密碼不正確"
                            } else if (position == 1) {
                                val url =
                                    "AccountCleaner/login/"
                                requestTask<CleanerSharedPreferencesUtils.ApiCleanerModel>(
                                    "$url${viewModel?.account?.value}/${viewModel?.password?.value}/$token"
                                )?.let {
                                    if (it.suspend) {
                                        tvLoginErrMsg.text = "此帳號已停權"
                                    } else if (!it.verify) {
                                        findNavController().navigate(R.id.action_loginFragment_to_signupCheckingFragment)
                                    } else {
                                        CleanerSharedPreferencesUtils.saveCleanerInfoFromPreferences(
                                            it
                                        )
                                        val intent =
                                            Intent(requireContext(), CleanerActivity::class.java)
                                        requireContext().startActivity(intent)
                                    }
                                    return@addOnCompleteListener
                                }
                                tvLoginErrMsg.text = "使用者帳號或密碼不正確"
                            } else {
                                val url =
                                    "AccountBackstage/"
                                requestTask<AccountBackstage>(
                                    "$url${viewModel?.account?.value}/${viewModel?.password?.value}/$token"
                                )?.let {
                                    if (it.suspend) {
                                        tvLoginErrMsg.text = "此帳號已停權"
                                    } else {
                                        val intent =
                                            Intent(requireContext(), BackstageActivity::class.java)
                                        requireContext().startActivity(intent)
                                    }
                                    return@addOnCompleteListener
                                }
                                tvLoginErrMsg.text = "使用者帳號或密碼不正確"
                            }
                        }
                    }
                }
            }

            // google登入
            btnLoginSignupGoogle.setOnClickListener {
                signInGoogleLauncher.launch(client.signInIntent)
            }

            tvLoginSignUp.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_signupFragment)
            }

            tvLoginForgetPassword.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_loginForgetPasswordPhoneFragment)
            }
        }
    }

    private var signInGoogleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                Log.d(myTag, "account: ${account.email}")
                account?.let {
                    firebaseAuthWithGoogle(it)
                }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.e(myTag, e.toString())
            }
        }

    // 使用Google帳號完成Firebase驗證
    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        // get the unique ID for the Google account
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                // 登入成功轉至下頁；失敗則顯示錯誤訊息
                if (task.isSuccessful) {
                    // 需根據選擇身分跳頁(Customer &　Cleaner)
                    // 將UID(firebaseId)與Email儲存(insert?)至mySQL，方便記錄該使用者交易
                    //取得GoogleEmail & FCM token
                    val user = task.result.user
                    val position = binding.spnLoginStatus.selectedItemPosition
                    val email = user?.email
                    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            task.result?.let { token ->
                                if (position == 0) {
                                    val url =
                                        "AccountCustomer/google/"
                                    requestTask<CustomerSharePreferencesUtils.ApiCustomerModel>(
                                        "$url$email/$token"
                                    )?.let {
                                        if (it.suspend) {
                                            binding.tvLoginErrMsg.text = "此帳號已停權"
                                        } else {
                                            CustomerSharePreferencesUtils.saveCustomerInfoFromPreferences(
                                                it
                                            )
                                            //跳轉至CustomerActivity
                                            val intent = Intent(
                                                requireContext(),
                                                CustomerActivity::class.java
                                            )
                                            requireContext().startActivity(intent)
                                        }
                                    }
                                } else if (position == 1) {
                                    val token = FirebaseMessaging.getInstance().token
                                    val url =
                                        "AccountCleaner/google/"
                                    requestTask<CleanerSharedPreferencesUtils.ApiCleanerModel>(
                                        "$url$email/$token"
                                    )?.let {
                                        if (it.suspend) {
                                            binding.tvLoginErrMsg.text = "此帳號已停權"
                                        } else if (!it.verify) {
                                            findNavController().navigate(R.id.action_loginFragment_to_signupCheckingFragment)
                                        } else {
                                            CleanerSharedPreferencesUtils.saveCleanerInfoFromPreferences(
                                                it
                                            )
                                            //跳轉至CleanerActivity
                                            val intent = Intent(
                                                requireContext(),
                                                CleanerActivity::class.java
                                            )
                                            requireContext().startActivity(intent)
                                        }
                                    }
                                } else {
                                    Toast.makeText(context, "管理員無google登入服務", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }else {
                            binding.tvLoginErrMsg.text = task.exception?.message
                                ?: "google登入失敗"  // getString(R.string.tv_login_fail)
                        }
                    }
                }
            }
    }

    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val regex =
                Regex("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+\$")
            val account = viewModel?.account?.value?.trim()
            val password = viewModel?.password?.value?.trim()
            if (account == null || account.isEmpty() || password == null || password.isEmpty()) {
                tvLoginErrMsg.text = "帳號或密碼不可為空白" // 抓不到string的值, 先以字串代替
                check = false
            } else if (account.matches(regex)) {
                tvLoginErrMsg.text = ""
            } else {
                tvLoginErrMsg.text = ""
                edtTxtLoginAccount.error = "帳號或密碼錯誤" // 抓不到string的值, 先以字串代替
                check = false
            }
            return check
        }
    }
}


