package com.cristiansofthouse.tabutest.question

import android.view.View
import com.cristiansofthouse.tabutest.R
import com.cristiansofthouse.tabutest.databinding.ViewQuestionItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class QuestionItem(
    private val index: Int,
    private val question: String,
    private val answer: (Boolean, Int) -> Unit
) : BindableItem<ViewQuestionItemBinding>() {

    override fun bind(viewBinding: ViewQuestionItemBinding, position: Int) {
        answer.invoke(false, index)
        viewBinding.textViewQuestion.text = question
        viewBinding.switch1.setOnCheckedChangeListener { _, b ->
            answer.invoke(b, index)
        }
    }

    override fun getLayout(): Int = R.layout.view_question_item

    override fun initializeViewBinding(view: View): ViewQuestionItemBinding =
        ViewQuestionItemBinding.bind(view)
}
