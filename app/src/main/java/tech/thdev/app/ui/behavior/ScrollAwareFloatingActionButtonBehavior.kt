package tech.thdev.app.ui.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Created by tae-hwan on 8/15/16.
 */
class ScrollAwareFloatingActionButtonBehavior(
    context: Context?,
    attributeSet: AttributeSet?
) : FloatingActionButton.Behavior() {

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )

        //child -> Floating Action Button
        if (child.visibility == View.VISIBLE && dyConsumed > 0) {
            child.hide()
        } else if (child.visibility == View.GONE && dyConsumed < 0) {
            child.show()
        }
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return type == ViewCompat.SCROLL_AXIS_VERTICAL
    }
}