package com.cristiansofthouse.testhistory.ui.views

import android.view.View
import com.cristiansofthouse.testhistory.R
import com.cristiansofthouse.testhistory.databinding.ViewHistoryItemBinding
import com.cristiansofthouse.testhistory.models.TestHistory
import com.cristiansofthouse.testhistory.utils.formatDate
import com.xwray.groupie.viewbinding.BindableItem

class TestHistoryItem(
    private val testHistory: TestHistory
) : BindableItem<ViewHistoryItemBinding>() {

    override fun bind(viewBinding: ViewHistoryItemBinding, position: Int) {
        viewBinding.testName.text = testHistory.test
        viewBinding.testScore.text = "Puntaje: ${testHistory.score}"
        viewBinding.testDate.text = "Fecha: ${testHistory.date.formatDate()}"
    }

    override fun getLayout(): Int = R.layout.view_history_item

    override fun initializeViewBinding(view: View): ViewHistoryItemBinding =
        ViewHistoryItemBinding.bind(view)
}
