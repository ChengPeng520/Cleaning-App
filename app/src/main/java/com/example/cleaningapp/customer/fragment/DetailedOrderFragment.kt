import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.cleaningapp.R
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
        arguments?.getInt("orderId")?.let {
            viewModel.fetchOrdersInfo(it)
        }
        binding.bntApplyComplaint.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(
                    R.id.action_detailedOrderFragment_to_applycomplaintFragment, arguments
                )
        }
    }
}