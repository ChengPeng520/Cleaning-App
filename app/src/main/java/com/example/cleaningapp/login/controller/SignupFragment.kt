package com.example.cleaningapp.login.controller

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.FragmentRonaSignupBinding
import com.example.cleaningapp.login.viewModel.SignupViewModel
import com.example.cleaningapp.share.requestTask
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.JsonObject

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentRonaSignupBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var client: GoogleSignInClient
    private val myTag = "TAG_${javaClass.simpleName}"
    private val viewModel: SignupViewModel by viewModels()

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().findViewById<Toolbar>(R.id.login_toolbar).visibility = View.VISIBLE
        requireActivity().findViewById<TextView>(R.id.login_toolbar_title).text = "會員註冊"
        binding = FragmentRonaSignupBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            tvSignupCancel.setOnClickListener() {
                Navigation.findNavController(it).popBackStack()
            }

            val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SuspiciousIndentation")
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
            // 一般註冊方法
            btnSignupSignup.setOnClickListener { view ->
                if (!inputCheck()) {
                    return@setOnClickListener
                }
                spnSignupStatus.onItemSelectedListener = onItemSelectedListener
                val position = spnSignupStatus.selectedItemPosition

                if (position == 0) {
                    val url = "AccountCustomer/isExist"
                    requestTask<JsonObject>("$url/${viewModel?.account}")?.let {
                        if (it.get("result").asBoolean) {
                            tvSignupErrMsg.text = "此帳號已存在"
                        } else {
                            saveEncryptedPassword()
                            val bundle = Bundle()
                            bundle.putString("emailAccount", viewModel?.account?.value)
                            bundle.putString("password", viewModel?.password?.value)
                            findNavController().navigate(
                                R.id.action_signupFragment_to_signupContractMemberFragment,
                                bundle
                            )
                        }
                    }
                } else {
                    val url = "AccountCleaner/isExist"
                    requestTask<JsonObject>("$url/${viewModel?.account}")?.let {
                        Log.d("result", it.get("result").asBoolean.toString())
                        if (it.get("result").asBoolean) {
                            tvSignupErrMsg.text = "此帳號已存在"
                        } else {
                            saveEncryptedPassword()
                            val bundle = Bundle()
                            bundle.putString("emailAccount", viewModel?.account?.value)
                            bundle.putString("password", viewModel?.password?.value)
                            findNavController().navigate(
                                R.id.action_signupFragment_to_signupContractFragment,
                                bundle
                            )
                        }
                    }
                }
            }
            // google註冊方法
            btnSignupSignupGoogle.setOnClickListener {
                //選擇身分設定
                val position = spnSignupStatus.selectedItemPosition
                if (position == 0) {
                    // 註冊Google email, 取出email並bundle email至info頁
                    val url = "AccountCustomer/isExist"
                    requestTask<JsonObject>("$url/${viewModel?.account}")?.let {
                        if (it.get("result").asBoolean) {
                            tvSignupErrMsg.text = "此帳號已存在"
                        } else {
                            // google註冊method
                            customerSignUpGoogleLauncher.launch(client.signInIntent)
                        }
                    }
                } else {
                    // 註冊Google email, 取出email並bundle email至info頁
                    val url = "AccountCleaner/isExist"
                    requestTask<JsonObject>("$url/${viewModel?.account}")?.let {
                        Log.d("result", it.get("result").asBoolean.toString())
                        if (it.get("result").asBoolean) {
                            tvSignupErrMsg.text = "此帳號已存在"
                        } else {
                            // google註冊method
                            cleanerSignUpGoogleLauncher.launch(client.signInIntent)
                        }
                    }
                }

            }
        }
    }

    fun saveEncryptedPassword() {
        with(binding) {
            getEncryptedPassword().edit()
                .putString("password", viewModel?.password?.value)
                .apply()
        }
    }

    private fun getEncryptedPassword(): SharedPreferences {
        val masterKeyAlias = MasterKey.Builder(requireContext())
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            requireContext(),
            "encryptedPassword",
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    // customer登入註冊
    private var customerSignUpGoogleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let {
                    firebaseAuthWithGoogleCustomer(it)
                }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.e(myTag, e.toString())
            }
        }

    // Customer使用Google帳號完成Firebase驗證
    private fun firebaseAuthWithGoogleCustomer(account: GoogleSignInAccount) {
        // get the unique ID for the Google account
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                // 登入成功轉至下頁；失敗則顯示錯誤訊息
                if (task.isSuccessful) {
                    // 將Email bundle至個人填寫頁並insert至DB
                    val user = task.result.user //這裡的user指的是firebaseAuth的user
                    val bundle = Bundle()
                    bundle.putString("emailAccount", user?.email)
                    bundle.putString("password", null)
                    Log.d(myTag, "firebase user email: ${user?.email}")
                    findNavController().navigate(
                        R.id.action_signupFragment_to_signupContractMemberFragment,
                        bundle
                    )
                } else {
                    binding.tvSignupErrMsg.text = task.exception?.message
                        ?: "google登入失敗"
                }
            }
    }

    // cleaner登入註冊
    private var cleanerSignUpGoogleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let {
                    firebaseAuthWithGoogleCleaner(it)
                }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.e(myTag, e.toString())
            }
        }

    // Cleaner使用Google帳號完成Firebase驗證
    private fun firebaseAuthWithGoogleCleaner(account: GoogleSignInAccount) {
        // get the unique ID for the Google account
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                // 登入成功轉至下頁；失敗則顯示錯誤訊息
                if (task.isSuccessful) {
                    // 將Email bundle至個人填寫頁並insert至DB
                    val user = task.result.user //這裡的user指的是firebaseAuth的user
                    val bundle = Bundle()
                    bundle.putString("emailAccount", user?.email)
                    bundle.putString("password", null)
                    Log.d(myTag, "firebase user email: ${user?.email}")
                    findNavController().navigate(
                        R.id.action_signupFragment_to_signupContractFragment,
                        bundle
                    )
                } else {
                    binding.tvSignupErrMsg.text = task.exception?.message
                        ?: "google登入失敗"
                }
            }
    }

    @SuppressLint("ResourceType")
    private fun inputCheck(): Boolean {
        var check = true
        with(binding) {
            val regex =
                Regex("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
            val account = viewModel?.account?.value?.trim()
            val password = viewModel?.password?.value?.trim()
            val cPassword = viewModel?.cPassword?.value?.trim()

            if (account == null || account.isEmpty() || password == null || password.isEmpty()) {
                tvSignupErrMsg.text = "帳號或密碼不可為空白"
                check = false
            } else if (account.matches(regex)) {
                tvSignupErrMsg.text = ""
            } else {
                tvSignupErrMsg.text = ""
                edtTxtSignupAccount.error = "帳號格式錯誤" // 抓不到string的值, 先以字串代替
                check = false
            }

            if (cPassword != password) {
                tvSignupErrMsg.text = ""
                edtTxtSignupPasswordcfm.error = "密碼不一致" // 抓不到string的值, 先以字串代替
                check = false
            }
            return check
        }
    }
}

public enum class Identity { //用陣列約束
    Customer, Cleaner
}

fun getIdentity(number: Int): Identity {
    return when (number) {
        0 -> Identity.Customer
        1 -> Identity.Cleaner
        else -> throw Exception()
    }
}