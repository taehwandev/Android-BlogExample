package tech.thdev.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import tech.thdev.library.OnClickEventControl
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    protected val viewController: OnClickEventControl
) : DefaultLifecycleObserver {

    protected val coroutineScope = object : CoroutineScope {

        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main + SupervisorJob()
    }

    abstract fun onCreate()

    override fun onCreate(owner: LifecycleOwner) {
        onCreate()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        coroutineScope.coroutineContext.cancel()
    }
}