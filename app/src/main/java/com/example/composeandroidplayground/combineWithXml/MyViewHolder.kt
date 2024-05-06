package com.example.composeandroidplayground.combineWithXml

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.composeandroidplayground.databinding.TextRectangleViewLayoutBinding
import kotlin.random.Random

class MyViewHolder(
    private val binding: TextRectangleViewLayoutBinding,
    private val state: MutableState<Boolean>
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(text: String) {
        val randomBoolean = randomBoolean()
        binding.textView.text = text
        binding.composeView.isVisible = randomBoolean

        // Update the state value if it has changed
        if (state.value != randomBoolean) {
            state.value = randomBoolean
        }
    }

    private fun randomBoolean(): Boolean {
        return Random.nextBoolean()
    }

    companion object {
        fun create(parent: ViewGroup): MyViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = TextRectangleViewLayoutBinding.inflate(inflater, parent, false)
            val state = mutableStateOf(Random.nextBoolean())
            binding.frameLayout.addView(
                ComposeView(binding.root.context).apply {
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                    )
                    setContent {
                        RectangleMatchParentWithHeight(state)
                    }
                }
            )
            return MyViewHolder(binding, state)
        }
    }
}
