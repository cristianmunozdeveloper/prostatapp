package com.cristiansofthouse.testhistory.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristiansofthouse.testhistory.databinding.ActivityTestHistoryBinding
import com.cristiansofthouse.testhistory.ui.TestHistoryViewModel.Event
import com.cristiansofthouse.testhistory.ui.views.TestHistoryItem
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestHistoryActivity : AppCompatActivity() {

    private val viewModel: TestHistoryViewModel by viewModels()

    private val binding: ActivityTestHistoryBinding by lazy {
        ActivityTestHistoryBinding.inflate(layoutInflater)
    }

    private val groupAdapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecycler()
        viewModel.event.observe(this, ::handleEvent)
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
    }

    private fun handleEvent(event: Event) {
        if (event is Event.ShowTestHistory) {
            event.list.forEach {
                groupAdapter.add(TestHistoryItem(it))
            }
        }
    }

    private fun setupRecycler() {
        binding.testHistoryList.apply {
            layoutManager =
                LinearLayoutManager(this@TestHistoryActivity, LinearLayoutManager.VERTICAL, false)
            adapter = groupAdapter
        }
    }
}
