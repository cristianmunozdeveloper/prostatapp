package com.cristiansofthouse.prostatest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristiansofthouse.common.QuestionItem
import com.cristiansofthouse.common.ResultDialog
import com.cristiansofthouse.common.ResultDialogListener
import com.cristiansofthouse.prostatest.ProstaTestViewModel.Event
import com.cristiansofthouse.prostatest.databinding.ActivityProstaTestBinding
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProstaTestActivity : AppCompatActivity(), ResultDialogListener {

    private val viewModel: ProstaTestViewModel by viewModels()

    private val binding: ActivityProstaTestBinding by lazy {
        ActivityProstaTestBinding.inflate(layoutInflater)
    }

    private val groupAdapter = GroupieAdapter()

    private val answersMap = mutableMapOf<Int, Boolean>()

    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecycler()
        setupQuestions()
        binding.btnSave.btnSave.setOnClickListener { save() }
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
        viewModel.event.observe(this, ::handleEvents)
    }

    private fun handleEvents(event: Event) {
        if (event is Event.Success) {
            finish()
        } else {
            Toast.makeText(this, getString(com.cristiansofthouse.common.R.string.error_message), Toast.LENGTH_SHORT).show()
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
        val message = when (score) {
            in 0..5 -> getString(R.string.low_risk)
            in 6..10 -> getString(R.string.middle_risk)
            else -> getString(R.string.high_risk)
        }
        ResultDialog.newInstance(String.format(getString(R.string.risk_message), message))
            .show(supportFragmentManager, this.javaClass.name)
    }

    override fun callback() {
        viewModel.saveTestHistory(score.toString())
    }
}
