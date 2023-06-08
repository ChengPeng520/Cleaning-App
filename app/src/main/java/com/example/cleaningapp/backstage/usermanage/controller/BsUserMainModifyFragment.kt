package com.example.cleaningapp.backstage.usermanage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.User
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserMainModifyViewModel
import com.example.cleaningapp.databinding.FragmentAlbBsUserMainModifyBinding

class BsUserMainModifyFragment : Fragment() {
    private lateinit var binding: FragmentAlbBsUserMainModifyBinding
    private val viewModel: BsUserMainModifyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAlbBsUserMainModifyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * 從用戶管理MainDetail的fragment接過來的bundle
         */
        arguments?.let { bundle ->
            bundle.getSerializable("user")?.let {
                binding.viewModel?.fetchMemberInfo(it as User)
            }

            with(binding) {
                val spinner: Spinner = view.findViewById(R.id.spinner_bs_user_main_modify_gender)
                // 創建選項內容
                val data = arrayOf("男", "女")
                // 創建 ArrayAdapter，並將spinner的樣式layout跟男或女的內容綁定
                val genderAdapter =
                    ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, data)
                genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                spinnerBsUserMainModifyGender.adapter = genderAdapter
                //創建選項監聽器
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        //在選擇選項發生變化時同時會變更viewModel中的數據
                        val selectGender = data[position]
                        if (selectGender == "男") {
                            viewModel?.user?.value?.gender = 0
                        } else if (selectGender == "女") {
                            viewModel?.user?.value?.gender = 1
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        //不執行任何操作
                    }

                }

                btnBsUserMainModifySubmit.setOnClickListener {
                    viewModel?.editMemberInfo(view)?.let { result ->
                        if (result) {
                            Toast.makeText(requireContext(), "修改成功", Toast.LENGTH_SHORT).show()
                            Navigation.findNavController(view).popBackStack()
                        } else {
                            Toast.makeText(requireContext(), "修改失敗", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                ivBsUserMainModifyBack.setOnClickListener {
                    Navigation.findNavController(view).popBackStack()
                }
            }
        }
    }
}