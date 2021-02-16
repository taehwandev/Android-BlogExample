package tech.thdev.app.util

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter


@BindingAdapter("activated")
fun View.setActivated(newActivated: Boolean) {
    isActivated = newActivated
}

@BindingAdapter("visibility")
fun View.setVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}