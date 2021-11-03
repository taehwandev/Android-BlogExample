package tech.thdev.app.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tech.thdev.app.R
import tech.thdev.app.databinding.SecondFragmentBinding
import tech.thdev.library.OnClickEventControl
import tech.thdev.library.OnClickEventControlImpl
import tech.thdev.library.ViewAccess
import tech.thdev.library.ViewAccessImpl

class SecondFragment : Fragment() {

    private lateinit var binding: SecondFragmentBinding

    private lateinit var viewModel: SecondViewModel

    private lateinit var viewController: OnClickEventControl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return SecondFragmentBinding.inflate(inflater).also {

            val viewAccess: ViewAccess = ViewAccessImpl(it)
            viewController = OnClickEventControlImpl(viewAccess)
            viewModel = SecondViewModel(viewController)
            lifecycle.addObserver(viewModel)

            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.clickEvent.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                viewModel.eventEnd()
            }
        }
    }
}
