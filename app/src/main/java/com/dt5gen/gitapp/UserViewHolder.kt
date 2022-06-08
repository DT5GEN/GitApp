package com.dt5gen.gitapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dt5gen.gitapp.databinding.ItemUserBinding

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val binding = ItemUserBinding.bind(itemView)


    fun bind(userEntity: UserEntity) {
        // todo разобраться с аватаркой
        binding.avatarImageView.load(userEntity.avatarUrl)
        binding.userNameTextView.text = userEntity.login
        binding.userIdTextView.text = userEntity.id.toString()
    }
}