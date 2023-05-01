package com.cristiansofthouse.whattodoifigetsick

import android.R.raw
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.annotation.Keep
import androidx.core.view.isVisible
import com.cristiansofthouse.common.BaseActivity
import com.cristiansofthouse.whattodoifigetsick.databinding.ActivityWhatToDoIfIgetSickBinding

@Keep
class WhatToDoIfIGetSickActivity : BaseActivity() {

    private val binding: ActivityWhatToDoIfIgetSickBinding by lazy {
        ActivityWhatToDoIfIgetSickBinding.inflate(layoutInflater)
    }

    override var klass: Class<*> = this::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupVideo()

        with(binding) {
            containerExamenProstatico.setOnClickListener {
                containerContenidoExamenAntigeno.isVisible =
                    containerContenidoExamenAntigeno.isVisible.not()
                containerContenidoRuta.isVisible = false
                containerContenidoTactoRectal.isVisible = false
            }

            containerTactoRectal.setOnClickListener {
                containerContenidoExamenAntigeno.isVisible = false
                containerContenidoRuta.isVisible = false
                containerContenidoTactoRectal.isVisible =
                    containerContenidoTactoRectal.isVisible.not()
            }

            containerRuta.setOnClickListener {
                containerContenidoExamenAntigeno.isVisible = false
                containerContenidoRuta.isVisible = containerContenidoRuta.isVisible.not()
                containerContenidoTactoRectal.isVisible = false
            }

            buttonPlayQueHacerSiMeEnfermo.setOnPlayButtonClickListener {
                playAudio(it, R.raw.que_hacer_si_me_enfermo)
                stopVideo()
            }
            buttonPlayExamenAntigeno.setOnPlayButtonClickListener {
                playAudio(it, R.raw.examen_de_antigeno)
                stopVideo()
            }
            buttonPlayRuta.setOnPlayButtonClickListener {
                playAudio(it, R.raw.ruta)
                stopVideo()
            }
            imagen1.setOnClickListener { showFullScreen(1) }
            imagen2.setOnClickListener { showFullScreen(2) }
            imagen3.setOnClickListener { showFullScreen(3) }
            imagen4.setOnClickListener { showFullScreen(4) }
            imageviewBackButton.setOnClickListener { onBackPressed() }
        }
    }

    private fun setupVideo() {
        val url =
            Uri.parse("android.resource://" + packageName + "/" + R.raw.tacto_rectal_video)
        binding.videoCaso.apply {
            setVideoURI(url)
            setOnClickListener {
                stopVideo()
            }
        }
        binding.buttonPlayVideo.setOnClickListener {
            binding.videoCaso.start()
            it.isVisible = false
            onPauseCustom()
        }
    }

    private fun stopVideo() {
        if (binding.videoCaso.isPlaying) binding.videoCaso.pause()
        binding.buttonPlayVideo.isVisible = true
    }

    override fun onPause() {
        super.onPause()
        binding.videoCaso.pause()
    }

    private fun showFullScreen(image: Int) {
        startActivity(
            Intent(this, ImageFullScreenActivity::class.java).apply {
                putExtra("image", image)
            }
        )
    }
}
