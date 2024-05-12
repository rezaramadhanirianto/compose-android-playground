package com.example.composeandroidplayground.combineWithXml.issueJumpingRecyclerView

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val dataList: List<String>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataItem = dataList[position]
        holder.bind(dataItem)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
