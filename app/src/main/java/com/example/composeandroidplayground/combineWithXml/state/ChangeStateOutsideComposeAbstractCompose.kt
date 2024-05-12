package com.example.composeandroidplayground.combineWithXml.state

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.AbstractComposeView

class ChangeStateOutsideComposeAbstractCompose(
    context: Context
): AbstractComposeView(context) {
    var state = mutableStateOf(ChangeStateOutsideComposeState("0"))
    @Composable
    override fun Content() {
        ChangeStateOutsideCompose(state = state)
    }

    /**
     * @note: not updated compose
     * fun setData(text: String){
     *    state.value.text = text
     * }
     * */

    /**
     * @note: not updated compose
     * fun setData(text: String){
     *     state = mutableStateOf(ChangeStateOutsideComposeState(text))
     * }
     * */

    /**
     * @note: compose view updated
     * */
    fun setData(text: String){
        state.value = ChangeStateOutsideComposeState(text)
    }

    fun getView() = this
}