package com.dt5gen.gitapp.ui.users

import android.app.Activity
import android.os.Bundle
import coil.load
import com.dt5gen.gitapp.R
import com.dt5gen.gitapp.databinding.ActivityAboutUserBinding
import com.dt5gen.gitapp.domain.entities.UserEntity

class AboutUserActivity: Activity() {

    private lateinit var binding: ActivityAboutUserBinding
    private lateinit var userEntity: UserEntity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about_user)
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