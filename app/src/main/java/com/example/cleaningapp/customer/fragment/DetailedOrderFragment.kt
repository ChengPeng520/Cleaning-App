import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.detailed.OrderListViewModel
import com.example.cleaningapp.databinding.FragmentVictorDetailedorderBinding

class DetailedOrderFragment : Fragment() {
    private lateinit var binding: FragmentVictorDetailedorderBinding
    val viewModel: OrderListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVictorDetailedorderBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            bundle.getSerializable("orderItem")?.let {
                viewModel.orderItem.value = it as Order
            }
        }

        // 假設您從 viewModel.photoUri 獲取要傳遞的照片的 Uri
        val photoUri = viewModel.photoUri.value

        // 將修改後的 newArguments 傳遞給下一頁
        binding.bntApplyComplaint.setOnClickListener {
            val newArguments = Bundle(arguments)
            photoUri?.let {
                newArguments.putParcelable("photoUri", it)
            }
            Navigation.findNavController(view)
                .navigate(
                    R.id.action_detailedOrderFragment_to_applycomplaintFragment,
                    newArguments
                )
        }
    }
}
