package com.cristiansofthouse.howtotakecareofme

import android.os.Bundle
import com.cristiansofthouse.common.BaseActivity
import com.cristiansofthouse.howtotakecareofme.databinding.ActivityHowToTakeCareOfMeBinding

class HowToTakeCareOfMeActivity : BaseActivity() {

    private val binding by lazy { ActivityHowToTakeCareOfMeBinding.inflate(layoutInflater) }

    override var klass: Class<*> = this::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonPlayComoCuidarme.setOnPlayButtonClickListener {
            playAudio(it, R.raw.como_me_puedes_cuidar)
        }
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
    }
}
