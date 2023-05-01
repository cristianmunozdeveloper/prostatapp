package com.cristiansofthouse.aboutme

import android.os.Bundle
import com.cristiansofthouse.aboutme.databinding.ActivityAboutMeBinding
import com.cristiansofthouse.common.BaseActivity

class AboutMeActivity : BaseActivity() {

    private lateinit var binding: ActivityAboutMeBinding

    override var klass: Class<*> = this::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutMeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPlayConoceme.setOnPlayButtonClickListener {
            playAudio(it, R.raw.conoceme_prostata)
        }
        binding.buttonPlayFunciones.setOnPlayButtonClickListener {
            playAudio(it, R.raw.funciones_prostata)
        }
        binding.buttonPlayCancer.setOnPlayButtonClickListener {
            playAudio(it, R.raw.cancer_prostata)
        }
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
    }
}
