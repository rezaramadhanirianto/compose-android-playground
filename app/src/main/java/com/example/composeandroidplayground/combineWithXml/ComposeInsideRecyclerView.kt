package com.example.composeandroidplayground.combineWithXml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.composeandroidplayground.R
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