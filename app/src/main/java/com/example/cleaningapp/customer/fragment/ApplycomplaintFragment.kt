import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.viewModel.ApplycomplaintViewModel
import com.example.cleaningapp.databinding.FragmentVictorApplycomplaintBinding

class ApplycomplaintFragment : Fragment() {
    private lateinit var binding: FragmentVictorApplycomplaintBinding
    private val viewModel: ApplycomplaintViewModel by viewModels()
    private lateinit var order: Order

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorApplycomplaintBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            order = bundle.getSerializable("orderItem") as Order
            viewModel.order.value = order
        }

        binding.bntApplyComplaintOk.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("orderItem", order)
            Navigation.findNavController(view)
                .navigate(R.id.action_applycomplaintFragment_to_complaintdetailsFragment, bundle)
        }

        binding.bntApplyComplaintCancel.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        binding.imageView40.setOnClickListener {
            if (viewModel.capturedCount < 3) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                takePictureSmallLauncher.launch(intent)
            }
        }
    }

    private val takePictureSmallLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras?.let { bundle ->
                    val picture = bundle["data"] as Bitmap?
                    picture?.let {
                        if (!viewModel.isPhotoExists(it) && viewModel.capturedCount < 3) {
                            viewModel.addCapturedPhoto(it)

                            when (viewModel.capturedCount) {
                                1 -> binding.imageView41.setImageBitmap(it)
                                2 -> binding.imageView42.setImageBitmap(it)
                                3 -> binding.imageView40.setImageBitmap(it)
                            }
                        }
                    }
                }
            }
        }
}
