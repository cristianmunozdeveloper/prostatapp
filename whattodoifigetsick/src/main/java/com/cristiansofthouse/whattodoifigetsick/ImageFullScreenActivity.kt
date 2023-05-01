package com.cristiansofthouse.whattodoifigetsick

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cristiansofthouse.whattodoifigetsick.databinding.ActivityImageFullScreenBinding

class ImageFullScreenActivity : AppCompatActivity() {

    private val binding: ActivityImageFullScreenBinding by lazy {
        ActivityImageFullScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val imageRes = when (intent.extras?.getInt("image") ?: 1) {
            1 -> R.drawable.acostado_de_lado
            2 -> R.drawable.acostado_boca_abajo
            3 -> R.drawable.acostado_boca_arriba
            else -> R.drawable.ruta
        }
        binding.containerImageZoomable.setImageDrawable(
            ContextCompat.getDrawable(this, imageRes)
        )
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
    }
}
