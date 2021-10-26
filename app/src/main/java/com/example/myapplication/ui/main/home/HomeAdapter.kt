package com.example.myapplication.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentHomeItemBinding
import com.example.myapplication.model.Videos

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var listaVideos = listOf<Videos>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentHomeItemBinding.inflate(inflater)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = listaVideos.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(listaVideos[position])

    fun setMedia(lista: List<Videos>) {
        this.listaVideos = lista
        notifyDataSetChanged()
    }


    inner class HomeViewHolder(val binding: FragmentHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Videos) {
            with(binding) {
                tvItem.text = item.title

            }
        }

    }


}