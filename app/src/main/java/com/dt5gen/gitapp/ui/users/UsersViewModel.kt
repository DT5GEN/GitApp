package com.dt5gen.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dt5gen.gitapp.domain.entities.UserEntity
import com.dt5gen.gitapp.domain.repos.UsersRepo
import com.dt5gen.gitapp.utils.SingleEventLiveData

class UsersViewModel(private val usersRepo: UsersRepo) : UsersContract.ViewModel {

    override val usersLiveData: LiveData<List<UserEntity>> = MutableLiveData()
    override val errorsLiveData: LiveData<Throwable> = SingleEventLiveData()
    override val progressLiveData: LiveData<Boolean> = MutableLiveData()

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        //  Toast.makeText(this, "Работает!", Toast.LENGTH_SHORT).show()
        progressLiveData.post(true)
        usersRepo.getUsers(
            onSuccess = {
                progressLiveData.post(false)
                usersLiveData.mutable().post(it)

            },
            onError = {
                progressLiveData.post(false)
                errorsLiveData.mutable().post(it)
            }
        )
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException(" It is not MutableLiveData! o_O ")
    }
}