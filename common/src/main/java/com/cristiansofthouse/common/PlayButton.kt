package com.cristiansofthouse.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.cristiansofthouse.common.databinding.PlayButtonBinding

class PlayButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val binding: PlayButtonBinding by lazy {
        PlayButtonBinding.inflate(
            LayoutInflater.from(context),
            this,
            false
        )
    }

    init {
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        addView(binding.root)
    }

    fun setOnPlayButtonClickListener(listener: (ImageView) -> Unit) {
        setOnClickListener {
            listener.invoke(binding.buttonPlay)
        }
    }
}
