package com.dt5gen.gitapp.ui.users

import com.dt5gen.gitapp.domain.entities.UserEntity
import com.dt5gen.gitapp.domain.repos.UsersRepo

class UsersPresenter(private val usersRepo: UsersRepo) : UsersContract.Presenter {

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
}