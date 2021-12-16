package tech.thdev.library

import android.util.Log
import androidx.annotation.IdRes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class OnClickEventControlImpl(
    private val viewAccess: ViewAccess
) : OnClickEventControl {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onClick(@IdRes resourceId: Int): Flow<Boolean> =
        flowOf(viewAccess.find(resourceId))
            .flatMapLatest { view ->
                Log.i(TAG, "Init")
                callbackFlow {
                    view.setOnClickListener {
                        trySendBlocking(true)
                    }

                    awaitClose {
                        view.setOnClickListener(null)
                    }
                }
            }

    companion object {
        const val TAG = "OnClickEventControl"
    }
}