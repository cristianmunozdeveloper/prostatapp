package com.cristiansofthouse.tabutest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristiansofthouse.common.QuestionItem
import com.cristiansofthouse.common.ResultDialog
import com.cristiansofthouse.common.ResultDialogListener
import com.cristiansofthouse.tabutest.TabuTestViewModel.Event
import com.cristiansofthouse.tabutest.databinding.ActivityTabuTestBinding
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabuTestActivity : AppCompatActivity(), ResultDialogListener {

    private val viewModel: TabuTestViewModel by viewModels()

    private val binding: ActivityTabuTestBinding by lazy {
        ActivityTabuTestBinding.inflate(layoutInflater)
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
                LinearLayoutManager(this@TabuTestActivity, LinearLayoutManager.VERTICAL, false)
            adapter = groupAdapter
        }
    }

    private fun setupQuestions() {
        val questions = resources.getStringArray(R.array.tabu_test)
        questions.forEachIndexed { index, question ->
            groupAdapter.add(QuestionItem(index, question, ::saveQuestion))
        }
    }

    private fun saveQuestion(answer: Boolean, index: Int) {
        answersMap[index] = answer
    }

    private fun save() {
        val existTrue = answersMap.values.any { it }
        score = answersMap.values.filter { it }.size
        val message = if (existTrue) {
            getString(R.string.tabu_message)
        } else {
            getString(R.string.no_tabu_message)
        }
        ResultDialog.newInstance(message)
            .show(supportFragmentManager, this.javaClass.name)
    }

    override fun callback() {
        viewModel.saveTestHistory(score.toString())
    }
}
