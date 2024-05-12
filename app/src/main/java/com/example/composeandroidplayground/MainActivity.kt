package com.example.composeandroidplayground

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeandroidplayground.combineWithXml.issueJumpingRecyclerView.ComposeInsideRecyclerView
import com.example.composeandroidplayground.combineWithXml.state.ChangeStateOutsideComposeActivity
import com.example.composeandroidplayground.common.theme.BaseTheme
import com.example.composeandroidplayground.selectable.SelectableScreenRoute
import com.example.composeandroidplayground.state.SavableAndNotSavableScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Preview
@Composable
private fun MainContent() {
    val context = LocalContext.current

    BaseTheme(
        darkTheme = false
    ) {
        Column {
            Button(onClick = {
                val intent = Intent(context, ChangeStateOutsideComposeActivity::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Change State Outside Compose")
            }
            Button(onClick = {
                val intent = Intent(context, ComposeInsideRecyclerView::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Issue Jumping Compose Inside RecyclerView")
            }
        }
    }
}
