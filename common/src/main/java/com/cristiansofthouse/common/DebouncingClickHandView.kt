package com.cristiansofthouse.common

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat

class DebouncingClickHandView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    init {
        setDrawableAndStartAnimation()
    }

    private fun setDrawableAndStartAnimation() {
        setImageDrawable(ContextCompat.getDrawable(context, R.drawable.hand_clicking_icon))
        startAnimation()
    }

    private fun startAnimation() {
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.bounce_animation))
    }
}
