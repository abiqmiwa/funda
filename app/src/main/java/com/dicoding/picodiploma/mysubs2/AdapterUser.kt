package com.dicoding.picodiploma.mysubs2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.mysubs2.databinding.ItemUserBinding

class AdapterUser: RecyclerView.Adapter<AdapterUser.UserViewHolder>() {
    private val mData = ArrayList<ModelUser>()

    fun setData(items: ArrayList<ModelUser>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(mView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class UserViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemUserBinding.bind(itemView)

        fun bind(modelUser: ModelUser) {
            binding.root.setOnClickListener{
                onItemClickCallback?.onItemClicked(modelUser)
            }

            with(itemView){
                Glide.with(itemView.context)
                    .load(modelUser.avatar)
                    .apply(RequestOptions()).override(350,550)
                    .into(binding.imgItemAvatar)

                binding.tvItemUsername.text = modelUser.username
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ModelUser)
    }
}