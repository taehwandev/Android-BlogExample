package tech.thdev.app.ui.parent_fragment_shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import tech.thdev.app.R
import tech.thdev.app.databinding.ParentFragmentSharedFragmentBinding
import tech.thdev.app.ui.MainViewModel

class ParentFragmentSharedViewModelSampleFragment : Fragment() {

    private var _binding: ParentFragmentSharedFragmentBinding? = null
    private val binding: ParentFragmentSharedFragmentBinding get() = _binding!!

    private val activityViewModel: MainViewModel by activityViewModels()

    private val viewModel: ParentFragmentSharedViewModelSampleViewModel by viewModels<ParentFragmentSharedViewModelSampleViewModel>()

    private val pagerAdapter: ParentFragmentSharedViewModelSampleViewPager by lazy {
        ParentFragmentSharedViewModelSampleViewPager(
            fragmentManager = childFragmentManager,
            lifecycle = lifecycle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ParentFragmentSharedFragmentBinding.inflate(inflater).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewUpdate.observe(viewLifecycleOwner, Observer {
            binding.tvMessage.text = it
        })

        binding.initView()
    }

    private fun ParentFragmentSharedFragmentBinding.initView() {
        pagerAdapter.run {
            addItem(ChildSharedFragment.newInstance("Index 1"))
            addItem(ChildSharedFragment.newInstance("Index 2"))
            addItem(ChildSharedFragment.newInstance("Index 3"))
        }
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Index ${position + 1}"
        }.attach()

        btnPrevious.setOnClickListener {
            findNavController().navigate(R.id.action_previous_ParentFragmentSharedViewModel_to_ActivitySharedViewModelFragment)
        }

        btnInsertCoinFragment.setOnClickListener {
            viewModel.plus()
        }

        btnInsertCoinActivity.setOnClickListener {
            activityViewModel.insertCountTwo()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
