package com.dt5gen.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dt5gen.gitapp.domain.entities.UserEntity
import com.dt5gen.gitapp.domain.repos.UsersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.SingleSubject
import io.reactivex.rxjava3.subjects.Subject
import javax.inject.Inject
import javax.security.auth.SubjectDomainCombiner

@HiltViewModel
class UsersViewModel @Inject constructor (private val usersRepo: UsersRepo) : UsersContract.ViewModel, ViewModel() {

    override val usersObservableData: Observable<List<UserEntity>> = BehaviorSubject.create()
    override val errorsObservableData: Observable<Throwable> = BehaviorSubject.create()
    override val progressObservableData: Observable<Boolean> = BehaviorSubject.create()
    override val openObservableUserProfileData: Observable<UserEntity> = BehaviorSubject.create()

    override fun onRefresh() {
        loadData()
            // val getContext: Context = get(Context::class.java)  // способ достать контекст из любого места (НЕ РЕКОМЕНДУЕТСЯ К ИСПОЛЬЗОВАНИЮ)
    }

    override fun onProfileClick(userEntity: UserEntity) {
//openObservableUserProfileData.toMutable().onNext(userEntity)

       (openObservableUserProfileData as Subject).toMutable().onNext(userEntity)
//        loadData()
    }

    private fun loadData() {
        //  Toast.makeText(this, "Работает!", Toast.LENGTH_SHORT).show()
        progressObservableData.toMutable().onNext(true)

        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
            onSuccess = {
                progressObservableData.toMutable().onNext(false)
                usersObservableData.toMutable().onNext(it)

            },
            onError = {
                progressObservableData.toMutable().onNext(false)
                errorsObservableData.toMutable().onError(it)
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