package tech.thdev.library

import android.view.View
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding

interface ViewAccess {
    fun find(@IdRes resourceId: Int): View
}

class ViewAccessImpl(
    private val dataBinding: ViewDataBinding
) : ViewAccess {

    /**
     * todo findViewById를 1번 만 하는 부분은 이 코드에 담아두지 않음.
     * 별도 캐싱이 필요함.
     */
    override fun find(@IdRes resourceId: Int): View =
        dataBinding.root.findViewById(resourceId)
}