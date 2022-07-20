package com.dt5gen.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dt5gen.gitapp.R
import com.dt5gen.gitapp.databinding.ItemUserBinding
import com.dt5gen.gitapp.domain.entities.UserEntity

class UserViewHolder(
    parent: ViewGroup,
    private val inItemClickListener: (userEntity: UserEntity) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {

    private lateinit var userEntity: UserEntity

    private val binding = ItemUserBinding.bind(itemView).apply {
        root.setOnClickListener {
            // avatarImageView.setOnClickListener { // вариант активного кликания по аватарке
            inItemClickListener.invoke(userEntity)
        }
    }

    fun bind(userEntityBind: UserEntity) {
        this.userEntity = userEntityBind
        // itemView.context.applicationContext as App // некий глобальный синглтон который можно достать из любого места

        // todo разобраться с аватаркой

        binding.avatarImageView.load(userEntity.avatarUrl)
        binding.userNameTextView.text = userEntity.login
        binding.userIdTextView.text = userEntity.id.toString()
    }
}