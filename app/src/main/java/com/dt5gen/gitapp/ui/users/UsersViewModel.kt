package com.dt5gen.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dt5gen.dilibra.inject
import com.dt5gen.gitapp.domain.entities.UserEntity
import com.dt5gen.gitapp.domain.repos.UsersRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class UsersViewModel : UsersContract.ViewModel {
    private val usersRepo: UsersRepo by inject()
    override val usersData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorsData: Observable<Throwable> = BehaviorSubject.create()
    override val progressData: Observable<Boolean> = BehaviorSubject.create()
    override val openUserProfileData: Observable<UserEntity> = BehaviorSubject.create()

    override fun onRefresh() {
        loadData()
    }

    override fun onProfileClick(userEntity: UserEntity) {
openUserProfileData.toMutable().onNext(userEntity)
        loadData()
    }

    private fun loadData() {
        //  Toast.makeText(this, "Работает!", Toast.LENGTH_SHORT).show()
        progressData.toMutable().onNext(true)

        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
            onSuccess = {
                progressData.toMutable().onNext(false)
                usersData.toMutable().onNext(it)

            },
            onError = {
                progressData.toMutable().onNext(false)
                errorsData.toMutable().onError(it)
            }
        )
    }

    private fun <T> LiveData<T>.toMutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException(" It is not MutableLiveData! o_O ")
    }

    private fun <T: Any> Observable<T>.toMutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException(" It is not MutableLiveData! o_O ")
    }
}