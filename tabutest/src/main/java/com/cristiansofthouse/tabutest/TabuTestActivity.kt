package com.cristiansofthouse.tabutest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristiansofthouse.common.QuestionItem
import com.cristiansofthouse.tabutest.databinding.ActivityTabuTestBinding
import com.xwray.groupie.GroupieAdapter

class TabuTestActivity : AppCompatActivity() {

    private val binding: ActivityTabuTestBinding by lazy {
        ActivityTabuTestBinding.inflate(layoutInflater)
    }

    private val groupAdapter = GroupieAdapter()

    private val answersMap = mutableMapOf<Int, Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecycler()
        setupQuestions()
        binding.btnSave.setOnClickListener { save() }
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
        if (existTrue) {
            Toast.makeText(this, "Tabu", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No Tabu", Toast.LENGTH_SHORT).show()
        }
    }
}
