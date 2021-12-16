package tech.thdev.library

import android.view.View
import androidx.annotation.IdRes

interface ViewAccess {
    fun find(@IdRes resourceId: Int): View
}