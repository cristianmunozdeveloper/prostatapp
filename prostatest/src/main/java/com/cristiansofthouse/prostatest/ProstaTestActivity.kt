package com.cristiansofthouse.prostatest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristiansofthouse.common.BaseActivity
import com.cristiansofthouse.common.QuestionItem
import com.cristiansofthouse.common.ResultDialog
import com.cristiansofthouse.common.ResultDialogListener
import com.cristiansofthouse.prostatest.ProstaTestViewModel.Event
import com.cristiansofthouse.prostatest.databinding.ActivityProstaTestBinding
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProstaTestActivity : BaseActivity(), ResultDialogListener {

    private val viewModel: ProstaTestViewModel by viewModels()

    private val binding: ActivityProstaTestBinding by lazy {
        ActivityProstaTestBinding.inflate(layoutInflater)
    }

    private val groupAdapter = GroupieAdapter()

    private val answersMap = mutableMapOf<Int, Boolean>()

    private var score: Int = 0
    override var klass: Class<*> = this::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecycler()
        setupQuestions()
        binding.btnSave.setOnClickListener { save() }
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
        viewModel.event.observe(this, ::handleEvents)
        binding.playButton.setOnPlayButtonClickListener {
            playAudio(it, com.cristiansofthouse.common.R.raw.instrucciones_test)
        }
    }

    private fun handleEvents(event: Event) {
        if (event is Event.Success) {
            finish()
        } else {
            Toast.makeText(
                this,
                getString(com.cristiansofthouse.common.R.string.error_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setupRecycler() {
        binding.tabutestQuestionsContainer.apply {
            layoutManager =
                LinearLayoutManager(this@ProstaTestActivity, LinearLayoutManager.VERTICAL, false)
            adapter = groupAdapter
            setItemViewCacheSize(20)
        }
    }

    private fun setupQuestions() {
        val questions = resources.getStringArray(R.array.prostate_test)
        questions.forEachIndexed { index, question ->
            groupAdapter.add(QuestionItem(index, question, ::saveQuestion))
        }
    }

    private fun saveQuestion(answer: Boolean, index: Int) {
        answersMap[index] = answer
    }

    private fun save() {
        score = answersMap.values.filter { it }.size
        val message = if (score > 0) getString(R.string.risk) else getString(R.string.no_risk)
        ResultDialog.newInstance(String.format(message))
            .show(supportFragmentManager, this.javaClass.name)
    }

    override fun callback() {
        viewModel.saveTestHistory(score.toString())
    }
}
