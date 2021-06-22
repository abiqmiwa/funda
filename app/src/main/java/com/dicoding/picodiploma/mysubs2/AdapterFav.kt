package com.dicoding.picodiploma.mysubs2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mysubs2.databinding.ItemFavBinding

class AdapterFav: RecyclerView.Adapter<AdapterFav.FavoriteViewHolder>() {

    private val listFavorite = ArrayList<ModelFav>()

    fun setData(items: ArrayList<ModelFav>) {
        listFavorite.clear()
        listFavorite.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_fav, parent, false)
        return FavoriteViewHolder(mView)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    override fun getItemCount(): Int = listFavorite.size

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavBinding.bind(itemView)

        fun bind(user: ModelFav) {
            Glide.with(itemView.context)
                .load(user.avatar)
                .apply(RequestOptions()).override(350,550)
                .into(binding.imgItemAvatar)
            binding.tvItemUsername.text = user.username
        }
    }

}