package com.example.life

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.Year


class MainAdapter(
    private val birthYear: Int,
    private val id: String,
    private val years: IntArray = IntArray(90) { birthYear + it },
    private val context: Context
) : RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_yearlist, parent, false)
        val viewHolder = MainViewHolder(view)
        return viewHolder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val year = birthYear + position
        val age = position+1
        holder.monthText.text = "$age"

        holder.monthRecyler.layoutManager= object : LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL,false){
            override fun canScrollVertically(): Boolean {
                return false // 세로 스크롤 차단
            }

            override fun canScrollHorizontally(): Boolean {
                return false // 가로 스크롤 차단
            }
        }
        //holder.monthRecyler.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        holder.monthRecyler.isNestedScrollingEnabled=false
        holder.monthRecyler.adapter = MonthAdapter(year, age, id, holder.itemView.context)
    }


    override fun getItemCount(): Int = years.size
}

