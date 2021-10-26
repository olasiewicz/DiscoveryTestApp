package com.example.myapplication.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentHomeItemBinding
import com.example.myapplication.model.Videos
import com.example.myapplication.util.DateUtils.Companion.prepareStringForData

class HomeAdapter(val onClicked: (Videos) -> Unit) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var listaVideos = listOf<Videos>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentHomeItemBinding.inflate(inflater)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = listaVideos.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(listaVideos[position], onClicked)

    fun setMedia(lista: List<Videos>) {
        this.listaVideos = lista
        notifyDataSetChanged()
    }


    inner class HomeViewHolder(val binding: FragmentHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Videos, onClicked: (Videos) -> Unit) {
            with(binding) {
                tvItem.text = prepareStringForData(item.date)
                tvItem.setOnClickListener { onClicked(item) }

            }
        }

    }

}