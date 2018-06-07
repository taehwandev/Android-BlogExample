package tech.thdev.app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tech.thdev.app.R
import tech.thdev.app.ui.main.viewmodel.LogoutViewModel
import tech.thdev.lifecycle.extensions.inject

class LogoutFragment : Fragment() {

    companion object {
        fun newInstance() = LogoutFragment()
    }

    private val viewModel: LogoutViewModel by lazy {
        LogoutViewModel().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.logout_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        // viewModel ...
    }
}