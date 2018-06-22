package tech.thdev.app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.logout_fragment.*
import tech.thdev.app.ActivityListener
import tech.thdev.app.R
import tech.thdev.app.ui.main.viewmodel.LogoutViewModel
import tech.thdev.lifecycle.extensions.inject

class LogoutFragment : Fragment() {

    private lateinit var activityListener: ActivityListener

    companion object {
        fun newInstance(activityListener: ActivityListener) = LogoutFragment().apply { this.activityListener = activityListener }
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
        viewModel.init()

        viewModel.startLoginTime()

        btn_logout.setOnClickListener {
            viewModel.stopLoginTime()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewModel.stopLoginTime()
    }

    private fun LogoutViewModel.init() {
        updateLoginTime = {
            tv_login_time?.text = getString(R.string.label_login_time, it)
        }

        logoutSuccess = {
            if (::activityListener.isInitialized) {
                activityListener.showLogin()
            }
        }
    }
}