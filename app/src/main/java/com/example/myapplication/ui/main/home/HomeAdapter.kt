package com.example.myapplication.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentHomeItemBinding
import com.example.myapplication.model.Stories
import com.example.myapplication.model.Videos
import com.example.myapplication.util.DateUtils.Companion.prepareStringForData

class HomeAdapter(val onClicked: (Any) -> Unit) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

//    private var listaVideos = listOf<Videos>()
    private var listaStories = listOf<Stories>()
    private var listaCala = listOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentHomeItemBinding.inflate(inflater)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = listaCala.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(listaCala[position], onClicked)

    fun setMedia(lista: List<Any>) {
        this.listaCala = lista
        notifyDataSetChanged()
    }


    inner class HomeViewHolder(val binding: FragmentHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any, onClicked: (Any) -> Unit) {
            with(binding) {

                if (item is Videos) {
                    tvItem.text = (item.date)
                    tvItem.setOnClickListener { onClicked(item) }
                }

                if(item is Stories) {
                    tvItem.text = (item.author)
                    tvItem.setOnClickListener { onClicked(item) }
                }


            }
        }

    }

}