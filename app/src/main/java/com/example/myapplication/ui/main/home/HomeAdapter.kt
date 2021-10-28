package com.example.myapplication.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.databinding.FragmentHomeItemBinding
import com.example.myapplication.model.Stories
import com.example.myapplication.model.Videos

class HomeAdapter(
    val glide: RequestManager,
    val requestOptions: RequestOptions,
    val onClicked: (Any) -> Unit
) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var listOfStoriesAndVideos = listOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentHomeItemBinding.inflate(inflater)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfStoriesAndVideos.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(listOfStoriesAndVideos[position], onClicked)

    fun setMedia(lista: List<Any>) {
        this.listOfStoriesAndVideos = lista
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(val binding: FragmentHomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any, onClicked: (Any) -> Unit) {

            with(binding) {
                if (item is Videos) {
                    glide
                        .setDefaultRequestOptions(requestOptions)
                        .load(item.thumb)
                        .into(imageHome)

                    tvTitle.text = item.title
                    tvSport.text = item.sport.name
                    layoutParent.setOnClickListener { onClicked(item) }
                    layoutAuthor.visibility = View.GONE
                    layoutViews.visibility = View.VISIBLE
                    imagePlay.visibility = View.VISIBLE
                    tvViews.text = item.views.toString()

                }
                if (item is Stories) {
                    glide
                        .setDefaultRequestOptions(requestOptions)
                        .load(item.image)
                        .into(imageHome)

                    tvTitle.text = (item.title)
                    tvSport.text = item.sport.name
                    layoutParent.setOnClickListener { onClicked(item) }
                    layoutAuthor.visibility = View.VISIBLE
                    layoutViews.visibility = View.GONE
                    imagePlay.visibility = View.GONE
                    tvAuthor.text = item.author
                    tvDate.text = item.date
                }

            }
        }

    }
}