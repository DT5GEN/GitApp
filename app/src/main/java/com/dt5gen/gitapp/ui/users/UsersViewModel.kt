package com.dt5gen.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dt5gen.gitapp.domain.entities.UserEntity
import com.dt5gen.gitapp.domain.repos.UsersRepo

class UsersViewModel(private val usersRepo: UsersRepo) : UsersContract.ViewModel {

    private var view: UsersContract.View? = null

    private var usersList: List<UserEntity>? = null
    private var loadingError: Throwable? = null
    private var inProgress: Boolean = false

    override fun attach(viewAttach: UsersContract.View) {
        this.view = viewAttach
        viewAttach.showProgress(inProgress)
        usersList?.let { viewAttach.showUsers(it) }
        loadingError?.let { viewAttach.showError(it) }
    }

    override fun detach() {
        view = null
    }

    private val _usersLiveData = MutableLiveData<List<UserEntity>>()
    override val usersLiveData: LiveData<List<UserEntity>>
        get() = _usersLiveData
    private val _errorsLiveData = MutableLiveData<Throwable>()
    override val errorsLiveData: LiveData<Throwable>
        get() = _errorsLiveData
    private val _progressLiveData = MutableLiveData<Boolean>()
    override val progressLiveData: LiveData<Boolean>
        get() = _progressLiveData

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        //  Toast.makeText(this, "Работает!", Toast.LENGTH_SHORT).show()
        view?.showProgress(true)
        inProgress = true

        usersRepo.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUsers(it)
                usersList = it
                loadingError = null
                inProgress = false

            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
                loadingError = it
                inProgress = false
            }
        )
    }

//    fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
//        return this as? MutableLiveData<T>?:throw IllegalStateException (" It is not MutableLiveData! o_O ")
//    }
}