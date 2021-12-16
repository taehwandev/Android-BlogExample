package tech.thdev.library

import androidx.annotation.IdRes
import kotlinx.coroutines.flow.Flow

interface OnClickEventControl {

    fun onClick(@IdRes resourceId: Int): Flow<Boolean>
}