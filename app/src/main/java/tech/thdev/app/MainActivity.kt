package tech.thdev.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.thdev.app.common.smartlock.SmartLockViewModel
import tech.thdev.app.ui.main.LoginFragment
import tech.thdev.app.ui.main.LogoutFragment

class MainActivity : AppCompatActivity(), ActivityListener {

    override val smartLockViewModel: SmartLockViewModel by lazy {
        SmartLockViewModel(this)
    }

    private val loginFragment: LoginFragment by lazy {
        LoginFragment.newInstance(this)
    }

    private val logoutFragment: LogoutFragment by lazy {
        LogoutFragment.newInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        showLogin()
    }

    override fun showLogin() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, loginFragment)
                .commitNow()
    }

    override fun showLogout() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, logoutFragment)
                .commitNow()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        smartLockViewModel.onActivityResult(requestCode, resultCode, data)
    }
}
