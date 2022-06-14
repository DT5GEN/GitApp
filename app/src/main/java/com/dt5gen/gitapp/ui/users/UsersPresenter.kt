package com.dt5gen.gitapp.ui.users

import android.widget.Toast
import com.dt5gen.gitapp.domain.repos.UsersRepo

class UsersPresenter(private val usersRepo: UsersRepo) : UsersContract.Presenter {

    private var view: UsersContract.View? = null

    override fun attach(view: UsersContract.View) {
        this.view = view
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

        usersRepo.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUsers(it)
            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
            }
        )
    }


}