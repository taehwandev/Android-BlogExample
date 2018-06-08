package tech.thdev.app.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.login_fragment.*
import tech.thdev.app.R
import tech.thdev.app.ui.main.viewmodel.LoginViewModel
import tech.thdev.lifecycle.extensions.inject

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by lazy {
        LoginViewModel().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        viewModel.loadTime()

        viewModel.init()
    }

    private fun LoginViewModel.init() {
        updateTime = {
            Log.e("TEMP", "now Time $it")
            tv_message.text = it
        }
    }
}
