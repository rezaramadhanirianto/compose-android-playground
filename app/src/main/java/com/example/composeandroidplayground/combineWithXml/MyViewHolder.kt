package com.example.composeandroidplayground.combineWithXml

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.composeandroidplayground.databinding.TextRectangleViewLayoutBinding
import kotlin.random.Random

class MyViewHolder(
    private val binding: TextRectangleViewLayoutBinding,
    private val state: MutableState<Int>
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(text: String) {
        binding.textView.text = text

        val height = Random.nextInt(100, 501)

        state.value = height
    }

    companion object {
        fun create(parent: ViewGroup): MyViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = TextRectangleViewLayoutBinding.inflate(inflater, parent, false)
            val state = mutableIntStateOf(Random.nextInt(100, 501)) // Random height between 100 and 500
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