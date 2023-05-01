package com.cristiansofthouse.tabutest

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.cristiansofthouse.common.BaseActivity
import com.cristiansofthouse.tabutest.databinding.ActivityMythBinding

class MythActivity : BaseActivity() {

    private val binding: ActivityMythBinding by lazy { ActivityMythBinding.inflate(layoutInflater) }

    override var klass: Class<*> = this::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val screen = intent.extras?.getInt("SCREEN") ?: 1
        val optionContent = resources.getStringArray(R.array.option_content)[screen]
        val mythContent = resources.getStringArray(R.array.myth_content)[screen]
        val audioForContent = when (screen) {
            0 -> R.raw.examen_alterado
            1 -> R.raw.cura_milagrosa
            2 -> R.raw.impotencia_e_incontinencia
            else -> R.raw.tacto_rectal
        }

        binding.optionContent.text = optionContent
        binding.mythContent.movementMethod = ScrollingMovementMethod()
        binding.mythContent.text = mythContent
        binding.buttonPlayAudioForContent.setOnPlayButtonClickListener { playAudio(it, audioForContent) }
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
    }
}
