package tech.thdev.app.ui.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import tech.thdev.app.R
import tech.thdev.app.databinding.FirstFragmentBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FirstFragmentBinding

    private val viewModel: FirstViewModel by viewModels<FirstViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FirstFragmentBinding.inflate(inflater, container, false)
            .also {
                it.lifecycleOwner = viewLifecycleOwner
                it.viewModel = viewModel

                binding = it
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // binding에서 click event 처리 시
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        // viewModel의 이벤트를 받아서 처리 시
        viewModel.clickEvent.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                viewModel.eventEnd()
            }
        }
    }
}
