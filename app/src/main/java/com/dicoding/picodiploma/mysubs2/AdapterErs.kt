package com.dicoding.picodiploma.mysubs2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mysubs2.databinding.ItemErsBinding

class AdapterErs: RecyclerView.Adapter<AdapterErs.ErsViewHolder>() {

    private val listFollowers = ArrayList<ModelErs>()

    fun setData(items: ArrayList<ModelErs>) {
        listFollowers.clear()
        listFollowers.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ErsViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_ers, parent, false)
        return ErsViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ErsViewHolder, position: Int) {
        holder.bind(listFollowers[position])
    }

    override fun getItemCount(): Int = listFollowers.size

    inner class ErsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemErsBinding.bind(itemView)

        fun bind(user: ModelErs) {
            Glide.with(itemView.context)
                .load(user.avatar)
                .apply(RequestOptions()).override(350,550)
                .into(binding.imgItemAvatar)
            binding.tvItemUsername.text = user.username
        }
    }
}