package com.example.pixabay

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pixabay.databinding.RvItemBinding

class RvAdapter : Adapter<RvAdapter.RvViewHolder>() {

    private val list = mutableListOf<ImageModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(page : Int, list:  List<ImageModel>) {
        if (page == 1) this.list.clear()
        this.list.addAll(list)
        Log.d("TAG", "addList: ${this.list}")
        notifyDataSetChanged()
    }

    class RvViewHolder(private val binding: RvItemBinding) : ViewHolder(binding.root) {
        fun bind(image: ImageModel) {
            binding.itemImgView.load(image.largeImageURL)
        }

    }
}