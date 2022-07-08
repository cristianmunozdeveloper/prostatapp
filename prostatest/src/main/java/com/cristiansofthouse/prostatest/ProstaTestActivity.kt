package com.cristiansofthouse.prostatest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristiansofthouse.prostatest.databinding.ActivityProstaTestBinding
import com.cristiansofthouse.prostatest.question.QuestionItem
import com.xwray.groupie.GroupieAdapter

class ProstaTestActivity : AppCompatActivity() {

    private val binding: ActivityProstaTestBinding by lazy {
        ActivityProstaTestBinding.inflate(layoutInflater)
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
        val scoreTest = answersMap.values.filter { it }.size
        val message = when (scoreTest) {
            in 0..5 -> "Riesgo Bajo"
            in 6..10 -> "Riesgo Medio"
            else -> "Riesgo Alto"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
