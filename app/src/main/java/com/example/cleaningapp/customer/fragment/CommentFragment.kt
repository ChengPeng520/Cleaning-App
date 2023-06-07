// CommentFragment.kt

package com.example.cleaningapp.customer.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.viewModel.CommentViewModel
import com.example.cleaningapp.databinding.FragmentVictorCommentBinding
import java.io.File

class CommentFragment : Fragment() {
    private lateinit var binding: FragmentVictorCommentBinding
    private val viewModel: CommentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorCommentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        initAppBarMenu()
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCommentOk.setOnClickListener {
            Navigation.findNavController(binding.btnCommentOk)
                .navigate(R.id.action_commentFragment_to_commentDoneFragment)
        }

        //拍照功能
        binding.applycomplaintPhoto.setOnClickListener {
            if (viewModel?.photo3?.value == null) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                takePictureSmallLauncher.launch(intent)
            }
        }
    }

    private var takePictureSmallLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras?.let { bundle ->
                    val picture = bundle["data"] as Bitmap?
                    picture?.let {
                        viewModel.addCapturedPhoto(it)
                    }
                }
            }
        }
    private fun initAppBarMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.ment_customer_chatroom, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.chatRoomFragment -> {
                        Navigation.findNavController(
                            requireActivity(),
                            R.id.customer_nav_host_fragment
                        ).navigate(R.id.chatRoomFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
