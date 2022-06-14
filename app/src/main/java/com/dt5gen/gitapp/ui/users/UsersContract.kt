package com.dt5gen.gitapp.ui.users

import com.dt5gen.gitapp.domain.entities.UserEntity


interface UsersContract {


    interface View{
        fun showUsers(users: List<UserEntity>)
        fun showError(throwable: Throwable)
        fun showProgress(inProgress: Boolean)
    }

    interface Presenter{
        fun attach(view: View)
        fun detach()

        fun onRefresh()


    }

}

