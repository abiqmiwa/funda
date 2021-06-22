package com.dicoding.picodiploma.mysubs2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mysubs2.databinding.ItemIngBinding

class AdapterIng: RecyclerView.Adapter<AdapterIng.IngViewHolder>() {

    private val listFollowing = ArrayList<ModelUser>()

    fun setData(items: ArrayList<ModelUser>) {
        listFollowing.clear()
        listFollowing.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_ers, parent, false)
        return IngViewHolder(mView)
    }

    override fun onBindViewHolder(holder: IngViewHolder, position: Int) {
        holder.bind(listFollowing[position])
    }

    override fun getItemCount(): Int = listFollowing.size

    inner class IngViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemIngBinding.bind(itemView)

        fun bind(user: ModelUser) {
            Glide.with(itemView.context)
                .load(user.avatarFollowing)
                .apply(RequestOptions()).override(350,550)
                .into(binding.imgItemAvatar)
            binding.tvItemUsername.text = user.usernameFollowing
        }
    }
}