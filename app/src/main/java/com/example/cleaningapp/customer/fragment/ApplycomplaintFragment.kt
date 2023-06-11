import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.viewModel.ApplycomplaintViewModel
import com.example.cleaningapp.databinding.FragmentVictorApplycomplaintBinding

class ApplycomplaintFragment : Fragment() {
    private lateinit var binding: FragmentVictorApplycomplaintBinding
    private val viewModel: ApplycomplaintViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorApplycomplaintBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        initAppBarMenu()
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<TextView>(R.id.customer_toolbar_title).text = getString(R.string.csTitle_applyComplaint)
        arguments?.getInt("orderId")?.let {
            viewModel.fetchOrdersInfo(it)
            viewModel.orderId.value = it
        }

        binding.bntApplyComplaintCancel.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
        //拍照功能
        binding.applycomplaintPhoto.setOnClickListener {
            if (viewModel.photo3.value == null) {
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
