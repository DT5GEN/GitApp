package com.dt5gen.gitapp.ui.users

import androidx.lifecycle.LiveData
import com.dt5gen.gitapp.domain.entities.UserEntity


interface UsersContract {


//    interface View{
//        fun showUsers(users: List<UserEntity>)
//        fun showError(throwable: Throwable)
//        fun showProgress(inProgress: Boolean)
//    }

    // cделали 3 элемента через колбэки на те же значения, чтобы нельзя было достучаться снаружи

    interface ViewModel{

        val usersLiveData: LiveData<List<UserEntity>>
        val errorsLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>

        fun onRefresh()


    }

}

