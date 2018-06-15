package tech.thdev.app.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.login_fragment.*
import tech.thdev.app.ActivityListener
import tech.thdev.app.R
import tech.thdev.app.data.source.login.LoginRepository
import tech.thdev.app.ui.main.viewmodel.LoginViewModel
import tech.thdev.lifecycle.extensions.inject

class LoginFragment : Fragment() {

    private lateinit var activityListener: ActivityListener

    companion object {
        fun newInstance(activityListener: ActivityListener) =
                LoginFragment().apply { this.activityListener = activityListener }
    }

    private val viewModel: LoginViewModel by lazy {
        LoginViewModel(LoginRepository, activityListener.smartLockViewModel).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.init()

        btn_login.run {
            setOnClickListener {
                viewModel.startLogin(et_user_id?.text.toString(), et_password.text.toString())
            }
            isEnabled = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.startTimerWatcher()

        et_user_id.addTextChangedListener(textWatcher)
        et_password.addTextChangedListener(textWatcher)

        activityListener.smartLockViewModel.loadCredential()
    }

    private fun LoginViewModel.init() {
        statusChangeLoginButton = {
            btn_login.isEnabled = it

            tv_message.run {
                setText(R.string.label_login_sample)
                isSelected = false
            }
        }

        loginSuccess = activityListener::showLogout

        loginFail = {
            tv_message.run {
                setText(R.string.message_login_fail)
                isSelected = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        et_user_id.removeTextChangedListener(textWatcher)
        et_password.removeTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val userId = et_user_id?.text?.toString() ?: ""
            val userPassword = et_password?.text?.toString() ?: ""
            viewModel.updateInput(userId, userPassword)
        }
    }
}
