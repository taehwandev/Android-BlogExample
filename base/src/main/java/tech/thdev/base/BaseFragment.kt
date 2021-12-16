package tech.thdev.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import tech.thdev.library.OnClickEventControl
import tech.thdev.library.OnClickEventControlImpl
import tech.thdev.library.ViewAccess
import tech.thdev.library.ViewAccessImpl

abstract class BaseFragment(
    @LayoutRes private val layoutRes: Int
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutRes, container, false)
            .also {
                val viewAccess: ViewAccess = ViewAccessImpl(it)
                val viewController: OnClickEventControl = OnClickEventControlImpl(viewAccess)

                it.lifecycleOwner = viewLifecycleOwner

                onCreateView(it, viewController)
            }.root
    }

    abstract fun onCreateView(binding: ViewDataBinding, viewController: OnClickEventControl)
}