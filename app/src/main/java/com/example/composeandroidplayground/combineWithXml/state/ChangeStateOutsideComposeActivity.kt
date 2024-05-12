package com.example.composeandroidplayground.combineWithXml.state

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.composeandroidplayground.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChangeStateOutsideComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_change_state_outside_compose)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupView()
    }

    private fun setupView() {
        var text: String = "0"
        var state = mutableStateOf(ChangeStateOutsideComposeState(text))
        val abstractComposeView = ChangeStateOutsideComposeAbstractCompose(this)

        /** @note: Not update text in compose
         * lifecycleScope.launch {
         *     for (i in 1..10) {
         *         text = "$i"
         *         delay(2000)
         *     }
         * }
         * */

        /** @note: Not update state in compose
         * lifecycleScope.launch {
         *     for (i in 1..10) {
         *         state = mutableStateOf(ChangeStateOutsideComposeState("$i"))
         *         delay(2000)
         *     }
         * }
         * */

        /** @note: compose updated */
        lifecycleScope.launch {
            for (i in 1..10) {
                state.value = ChangeStateOutsideComposeState("$i")
                abstractComposeView.setData("$i")
                delay(2000)
            }
        }

        findViewById<ComposeView>(R.id.compose_view).setContent {
            ChangeStateOutsideCompose(state)
        }

        findViewById<FrameLayout>(R.id.frame_layout).addView(abstractComposeView)
    }
}