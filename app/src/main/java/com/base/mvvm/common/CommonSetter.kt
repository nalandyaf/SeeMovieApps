package com.base.mvvm.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object CommonSetter {
    @BindingAdapter("imageSource")
    fun imageSource(view: ImageView, imageSource: Int) {
        view.setImageResource(imageSource)
    }

    @kotlin.jvm.JvmStatic
    @BindingAdapter("isVisible")
    fun setIsVisible(view: View, isVisible: Boolean?) {
        if (isVisible == null) {
            return
        }
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}