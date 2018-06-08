package tech.thdev.app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.logout_fragment.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.cancelAndJoin
import kotlinx.coroutines.experimental.launch
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

    private val timer: Job by lazy {
        launch {
            var i = 0
            while (isActive) {
                launch(UI) { tv_login_time.text = "${++i}" }
                Thread.sleep(100)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.init()

        viewModel.startLoginTime()

        btn_logout.setOnClickListener {
            viewModel.stopLoginTime()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stopLoginTime()
    }

    private fun LogoutViewModel.init() {
        updateLoginTime = {
            tv_login_time.text = getString(R.string.label_login_time, it)
        }
    }
}