package tech.thdev.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.thdev.app.ui.main.LoginFragment
import tech.thdev.app.ui.main.LogoutFragment

class MainActivity : AppCompatActivity(), ActivityListener {

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
}
