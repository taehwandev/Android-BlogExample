package tech.thdev.second.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import tech.thdev.base.BaseFragment
import tech.thdev.library.OnClickEventControl

class SecondFragment : BaseFragment(R.layout.second_fragment) {

    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(binding: ViewDataBinding, viewController: OnClickEventControl) {
        viewModel = SecondViewModel(viewController)
        lifecycle.addObserver(viewModel)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // todo
//        viewModel.clickEvent.observe(viewLifecycleOwner) {
//            if (it) {
//                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//                viewModel.eventEnd()
//            }
//        }
    }
}
