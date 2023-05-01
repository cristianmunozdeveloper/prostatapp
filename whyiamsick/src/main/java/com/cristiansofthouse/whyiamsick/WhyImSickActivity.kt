package com.cristiansofthouse.whyiamsick

import android.content.Intent
import android.os.Bundle
import com.cristiansofthouse.common.BaseActivity
import com.cristiansofthouse.prostatest.ProstaTestActivity
import com.cristiansofthouse.whyiamsick.databinding.ActivityWhyImSickBinding

class WhyImSickActivity : BaseActivity() {

    private val binding by lazy { ActivityWhyImSickBinding.inflate(layoutInflater) }

    override var klass: Class<*> = this::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonPlayPorqueMeEnfermo.setOnPlayButtonClickListener {
            playAudio(it, R.raw.porque_me_enfermo)
        }
        binding.btnGoToProstatest.setOnClickListener {
            startActivity(Intent(this, ProstaTestActivity::class.java))
        }
        binding.buttonPlayPorqueMeEnfermo2.setOnPlayButtonClickListener {
            playAudio(it, R.raw.porque_me_enfermo_2)
        }
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
    }
}
