package tech.thdev.app.ui.login

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.motion.widget.MotionLayout
import tech.thdev.app.R
import tech.thdev.app.base.ui.BasePresenterActivity
import tech.thdev.app.data.source.login.LoginRepository
import tech.thdev.app.databinding.ActivityMainBinding
import tech.thdev.app.databinding.ContentMainBinding
import tech.thdev.app.ui.login.presenter.LoginContract
import tech.thdev.app.ui.login.presenter.LoginPresenter

/**
 * Created by tae-hwan on 2/17/17.
 */
class LoginActivity : BasePresenterActivity<LoginContract.View, LoginContract.Presenter>(),
    LoginContract.View {

    private lateinit var content: ContentMainBinding

    override fun onCreatePresenter() =
        LoginPresenter(LoginRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        content = ContentMainBinding.inflate(layoutInflater, binding.container, true)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        initView()
    }

    private fun initView() {
        content.etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
                true
            } else {
                false
            }
        }

        setLoginButton()
        content.etEmail.setText("test@test.com")
        content.etPassword.setText("1234")
    }

    override fun showProgress() {
        content.viewProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        content.viewProgress.visibility = View.GONE
    }

    private fun login() {
        presenter.loginUser(
            email = content.etEmail.text.toString(),
            password = content.etPassword.text.toString()
        )
    }

    override fun emailEmptyError() {
        content.layoutEmail.error = getString(R.string.error_field_required)
    }

    override fun emailInvalid() {
        content.layoutEmail.error = getString(R.string.error_invalid_email)
    }

    override fun passwordInvalid() {
        content.layoutPassword.error = getString(R.string.error_invalid_password)
    }

    override fun loginFail() {
        emailInvalid()
        content.layoutPassword.error = getString(R.string.error_incorrect_password)
    }

    override fun successLogin(email: String) {
        content.tvSuccessEmail.text = email
        content.motionLayout.run {
            setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) = Unit

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) =
                    Unit

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) =
                    Unit

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    content.btnAction.run {
                        setText(R.string.action_logout)
                        setOnClickListener {
                            presenter.logout()
                        }
                    }
                }
            })
            transitionToEnd()
        }
    }

    override fun successLogout() {
        content.motionLayout.run {
            setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) = Unit

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) =
                    Unit

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) =
                    Unit

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    setLoginButton()
                }
            })
            transitionToStart()
        }
    }

    private fun setLoginButton() {
        android.util.Log.w("TEMP", "setLoginButton!!!")
        content.btnAction.run {
            setText(R.string.action_login)
            setOnClickListener {
                content.layoutEmail.error = null
                content.layoutPassword.error = null
                android.util.Log.w("TEMP", "onClick!!!")
                login()
            }
        }
    }
}