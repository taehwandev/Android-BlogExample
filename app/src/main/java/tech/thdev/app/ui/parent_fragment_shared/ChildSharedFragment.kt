package tech.thdev.app.ui.parent_fragment_shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import tech.thdev.app.R
import tech.thdev.app.databinding.ChildSharedFragmentBinding

class ChildSharedFragment : Fragment() {

    companion object {

        private const val EXTRA_TITLE = "title"

        fun newInstance(title: String): ChildSharedFragment =
            ChildSharedFragment().apply {
                arguments = bundleOf(EXTRA_TITLE to title)
            }
    }

    private var _binding: ChildSharedFragmentBinding? = null
    private val binding: ChildSharedFragmentBinding get() = _binding!!

    private val viewModel: ChildSharedViewModel by viewModels()

    private val parentViewModel: ParentFragmentSharedViewModelSampleViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ChildSharedFragmentBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    }

    private val title: String by lazy {
        arguments?.getString(EXTRA_TITLE) ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateFragmentCount.observe(viewLifecycleOwner, Observer {
            binding.tvMessage.text = getString(R.string.default_message_view, it)
        })

        binding.btnPlus.setOnClickListener {
            viewModel.plus()
        }
        binding.btnParentFragmentPlus.setOnClickListener {
            parentViewModel.childPlus(title)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
