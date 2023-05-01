package com.cristiansofthouse.tabutest

import android.content.Intent
import android.os.Bundle
import com.cristiansofthouse.common.BaseActivity
import com.cristiansofthouse.tabutest.databinding.ActivityMythsAboutMeBinding

class MythsAboutMeActivity : BaseActivity() {

    private val binding: ActivityMythsAboutMeBinding by lazy {
        ActivityMythsAboutMeBinding.inflate(
            layoutInflater
        )
    }

    override var klass: Class<*> = this::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.examenAlterado.setOnClickListener { goToMythDetail(0) }
        binding.curaMilagrosa.setOnClickListener { goToMythDetail(1) }
        binding.impotenciaEIncontinencia.setOnClickListener { goToMythDetail(2) }
        binding.tactoRectal.setOnClickListener { goToMythDetail(3) }
        binding.buttonPlayMitosSobreMi.setOnPlayButtonClickListener { playAudio(it, R.raw.mitos_sobre_mi) }
        binding.btnGoToTabutest.setOnClickListener {
            startActivity(Intent(this, TabuTestActivity::class.java))
        }
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
    }

    private fun goToMythDetail(screen: Int) {
        startActivity(
            Intent(this, MythActivity::class.java).apply {
                putExtra("SCREEN", screen)
            }
        )
    }
}
