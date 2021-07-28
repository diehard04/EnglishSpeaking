package com.diehard04.englishspeaking.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diehard04.englishspeaking.R
import com.diehard04.englishspeaking.data.model.User

/**
 * Created by DieHard_04 on 28-07-2021.
 */
class HomeAdapter(private val context: Context, private val contentList: ArrayList<User>) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvDesc: TextView = itemView.findViewById(R.id.tvDescription)
        var icon: ImageView = itemView.findViewById(R.id.ivIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_home, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = contentList[position].title
        holder.tvDesc.text = contentList[position].description
    }

    override fun getItemCount(): Int = contentList.size
}