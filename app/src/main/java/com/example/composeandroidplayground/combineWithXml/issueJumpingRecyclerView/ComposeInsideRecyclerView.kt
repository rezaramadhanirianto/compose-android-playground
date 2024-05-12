package com.example.composeandroidplayground.combineWithXml.issueJumpingRecyclerView

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.composeandroidplayground.databinding.ActivityComposeInsideRecyclerViewBinding

class ComposeInsideRecyclerView : AppCompatActivity() {

    private lateinit var binding: ActivityComposeInsideRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComposeInsideRecyclerViewBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        val adapter = MyAdapter(mutableListOf<String>().apply {
            for (i in 1..100){
                add("Text $i")
            }
        })
        binding.recyclerView.adapter = adapter
    }
}