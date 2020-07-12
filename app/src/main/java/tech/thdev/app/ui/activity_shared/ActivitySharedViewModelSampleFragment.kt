package tech.thdev.app.ui.activity_shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tech.thdev.app.R
import tech.thdev.app.databinding.ActivitySharedFragmentBinding
import tech.thdev.app.ui.MainViewModel

class ActivitySharedViewModelSampleFragment : Fragment() {

    private val viewModel: ActivitySharedViewModelSampleViewModel by viewModels()

//    private val activityViewModel: MainViewModel by lazy {
//        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
//                MainViewModel() as T
//        }).get(MainViewModel::class.java)
//    }
    private val activityViewModel: MainViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                MainViewModel() as T
        }
    }

    private var _binding: ActivitySharedFragmentBinding? = null
    private val binding: ActivitySharedFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        ActivitySharedFragmentBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateFragmentCount.observe(viewLifecycleOwner, Observer {
            binding.tvMessage.text = getString(R.string.default_message_view, it)
        })

        binding.initView()
    }

    private fun ActivitySharedFragmentBinding.initView() {
        btnInsertCoinFragment.setOnClickListener {
            viewModel.plus()
        }

        btnInsertCoinActivity.setOnClickListener {
            activityViewModel.insertCountTwo()
        }

        btnNext.setOnClickListener {

        }
        btnPrevious.setOnClickListener {
            findNavController().navigate(R.id.action_previous_activitySharedViewModel_to_DefaultViewModelFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }
}