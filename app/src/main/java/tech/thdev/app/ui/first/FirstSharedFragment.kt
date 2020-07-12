package tech.thdev.app.ui.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import tech.thdev.app.databinding.SecondFragmentBinding
import tech.thdev.app.ui.second.SecondFragment

class FirstSharedFragment : Fragment() {

    private var _binding: SecondFragmentBinding? = null
    private val binding: SecondFragmentBinding get() = _binding!!

    private val parentViewModel: FirstViewModel by viewModels<FirstViewModel>(ownerProducer = { requireParentFragment() })

    private val pagerAdapter: SampleViewPager by lazy {
        SampleViewPager(fragmentManager = parentFragmentManager, lifecycle = lifecycle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return SecondFragmentBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagerAdapter.addItem(SecondFragment())

        parentViewModel.updateViewItem.observe(viewLifecycleOwner, Observer {
            binding.textviewSecond.text = it
        })
        binding.buttonSecond.setOnClickListener {
            parentViewModel.load()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
