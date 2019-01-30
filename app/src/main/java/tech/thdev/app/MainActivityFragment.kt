package tech.thdev.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.thdev.support.base.coroutines.ui.CoroutineScopeFragment

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : CoroutineScopeFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}
