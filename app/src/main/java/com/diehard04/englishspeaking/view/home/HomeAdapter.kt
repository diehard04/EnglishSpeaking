package com.diehard04.englishspeaking.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diehard04.englishspeaking.R
import com.diehard04.englishspeaking.data.model.HomeModel
import com.diehard04.englishspeaking.view.`interface`.HomeAdapterListener

/**
 * Created by DieHard_04 on 28-07-2021.
 */
class HomeAdapter(private val context: Context, private val homeList: ArrayList<HomeModel>, private var listener: HomeAdapterListener) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private val TAG = HomeAdapter::class.java.name

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(home: HomeModel) {
            itemView.apply {
                tvTitle.text = home.title
                tvSection.text = home.section
                tvConversation.text = home.conversation
                Glide.with(context).load(home.icon).into(icon)
            }
        }

        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvSection: TextView = itemView.findViewById(R.id.tvSection)
        var icon: ImageView = itemView.findViewById(R.id.ivIcon)
        var tvConversation: TextView = itemView.findViewById(R.id.tvConversation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_home, parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(homeList[position])
        holder.itemView.setOnClickListener{
            listener.itemClicked(homeList[position].title)
        }
    }

    override fun getItemCount(): Int = homeList.size

    fun addContent(homeList: ArrayList<HomeModel>) {
        this.homeList.apply {
            clear()
            addAll(homeList)
        }
    }
}