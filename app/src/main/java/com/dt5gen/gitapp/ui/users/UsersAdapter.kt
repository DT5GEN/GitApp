package com.dt5gen.gitapp.ui.users

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dt5gen.gitapp.domain.entities.UserEntity

class UsersAdapter(private val onItemClickListener: (UserEntity) -> Unit) :
    RecyclerView.Adapter<UserViewHolder>() {
    private val data = mutableListOf<UserEntity>()

//    private val adapter = UsersAdapter { user ->
//        usersN.onProfileClick(user)
//    }

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(parent, onItemClickListener)


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = data.size

    private fun getItem(pos: Int): UserEntity = data[pos]

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<UserEntity>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }
}