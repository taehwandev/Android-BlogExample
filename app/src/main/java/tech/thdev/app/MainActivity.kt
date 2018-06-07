package tech.thdev.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.thdev.app.ui.main.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commitNow()
        }
    }

}
