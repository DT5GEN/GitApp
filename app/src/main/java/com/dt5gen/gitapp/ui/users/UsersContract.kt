package com.dt5gen.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dt5gen.gitapp.domain.entities.UserEntity


interface UsersContract {


//    interface View{
//        fun showUsers(users: List<UserEntity>)
//        fun showError(throwable: Throwable)
//        fun showProgress(inProgress: Boolean)
//    }

    // сделали 3 элемента через колбэки на те же значения, чтобы нельзя было достучаться снаружи

    interface ViewModel {

        val usersLiveData: LiveData<List<UserEntity>>
        val errorsLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>
        val openUserProfileLiveData: LiveData<Unit>

        fun onRefresh()

        fun onProfileClick(userEntity: UserEntity)

        fun <T> LiveData<T>.post(value: T) {
            val mutable = this as? MutableLiveData<T>
                ?: throw IllegalStateException(" It is not MutableLiveData! o_O ")
            mutable.postValue(value)
        }


    }

}

