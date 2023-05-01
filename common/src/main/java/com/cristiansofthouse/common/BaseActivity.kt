package com.cristiansofthouse.common

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

abstract class BaseActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var previousButtonView: ImageView? = null
    private var actualButtonView: ImageView? = null

    abstract var klass: Class<*>

    protected fun playAudio(buttonView: ImageView, @RawRes audioResource: Int) {
        actualButtonView = buttonView
        if (previousButtonView == null) {
            previousButtonView = actualButtonView
        }
        if (previousButtonView != actualButtonView) {
            mediaPlayer?.stop()
            restoreImageView(previousButtonView)
            previousButtonView = actualButtonView
        }
        Handler(Looper.getMainLooper()).postDelayed({
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.stop()
                mediaPlayer = null
                buttonView.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        android.R.drawable.ic_media_play,
                        null
                    )
                )
            } else {
                mediaPlayer = MediaPlayer.create(this, audioResource)
                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener {
                    mediaPlayer?.stop()
                    mediaPlayer = null
                    buttonView.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            android.R.drawable.ic_media_play,
                            null
                        )
                    )
                }
                buttonView.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        android.R.drawable.ic_media_pause,
                        null
                    )
                )
            }
        }, 200)
    }

    override fun onPause() {
        super.onPause()
        val classString = klass.toString()
        if (classString != "class com.cristiansofthouse.whattodoifigetsick.WhatToDoIfIGetSickActivity") {
            mediaPlayer?.stop()
            restoreImageView(actualButtonView)
        }
    }

    protected fun onPauseCustom() {
        mediaPlayer?.stop()
        restoreImageView(actualButtonView)
    }

    private fun restoreImageView(view: ImageView?) {
        view?.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                android.R.drawable.ic_media_play,
                null
            )
        )
    }
}
