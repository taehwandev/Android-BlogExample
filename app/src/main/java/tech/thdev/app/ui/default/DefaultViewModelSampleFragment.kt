package tech.thdev.app.ui.default

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tech.thdev.app.R
import tech.thdev.app.databinding.DefaultFragmentBinding

class DefaultViewModelSampleFragment : Fragment() {

    private var _binding: DefaultFragmentBinding? = null
    private val binding: DefaultFragmentBinding get() = _binding!!

//    private val viewModel: DefaultViewModelSampleViewModel by lazy {
//        ViewModelProvider(viewModelStore, object : ViewModelProvider.Factory {
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                return DefaultViewModelSampleViewModel() as T
//            }
//        }).get(DefaultViewModelSampleViewModel::class.java)
//    }

    private val viewModel: DefaultViewModelSampleViewModel by viewModels()

//    private val viewModel: DefaultViewModelSampleViewModel by viewModels {
//        object : ViewModelProvider.Factory {
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
//                DefaultViewModelSampleViewModel() as T
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        DefaultFragmentBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateFragmentCount.observe(viewLifecycleOwner, Observer {
            binding.tvMessage.text = getString(R.string.default_message_view, it)
        })
        binding.btnInsertCoinFragment.setOnClickListener {
            viewModel.plus()
        }

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_next_DefaultFragment_to_ActivitySharedFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }
}