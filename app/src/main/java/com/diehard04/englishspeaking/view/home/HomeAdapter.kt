package com.diehard04.englishspeaking.view.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diehard04.englishspeaking.R
import com.diehard04.englishspeaking.data.model.ContentModel

/**
 * Created by DieHard_04 on 28-07-2021.
 */
class HomeAdapter(private val context: Context, private val contentList: ArrayList<ContentModel>) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private val TAG = HomeAdapter::class.java.name

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(content: ContentModel) {
            itemView.apply {
                tvTitle.text = content.title
                tvDesc.text = content.description
                Glide.with(context).load(content.icon).into(icon)
            }
        }
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvDesc: TextView = itemView.findViewById(R.id.tvDescription)
        var icon: ImageView = itemView.findViewById(R.id.ivIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
       MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_home, parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contentList[position])
    }

    override fun getItemCount(): Int = contentList.size

    fun addContent(contentList: ArrayList<ContentModel>) {
        this.contentList.apply {
            clear()
            addAll(contentList)
        }
    }
}