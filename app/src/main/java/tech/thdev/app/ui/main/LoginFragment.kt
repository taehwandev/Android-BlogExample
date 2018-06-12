package tech.thdev.app.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        et_user_id.addTextChangedListener(textWatcher)
    }

    private fun LoginViewModel.init() {
        updateTime = {
            Log.e("TEMP", "now Time $it")
            tv_message.text = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        et_user_id.removeTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            p0?.let { viewModel.updateInput(it.toString()) }
        }
    }
}
