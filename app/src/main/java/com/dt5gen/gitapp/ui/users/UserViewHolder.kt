package com.dt5gen.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dt5gen.gitapp.R
import com.dt5gen.gitapp.domain.entities.UserEntity
import com.dt5gen.gitapp.databinding.ItemUserBinding

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val binding = ItemUserBinding.bind(itemView)


    fun bind(userEntity: UserEntity) {

       // itemView.context.applicationContext as App // некий глобальный синглтон который можно достать из любого места

        // todo разобраться с аватаркой

        binding.avatarImageView.load(userEntity.avatarUrl)
        binding.userNameTextView.text = userEntity.login
        binding.userIdTextView.text = userEntity.id.toString()
    }
}