package com.dt5gen.gitapp.data.retrofit

import com.dt5gen.gitapp.domain.entities.UserEntity
import com.dt5gen.gitapp.domain.repos.UsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

class RetrofitUsersRepoImpl(
private val api: GithubApi
                            ) : UsersRepo {

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
//        api.getUsers().enqueue(object : Callback<List<UserEntity>> {
//            override fun onResponse(
//                call: Call<List<UserEntity>>,
//                response: Response<List<UserEntity>>
//            ) {
//                val body = response.body()
//                if (response.isSuccessful && body != null) {
//                    onSuccess(body)
//                } else {
//                    onError?.invoke(IllegalStateException("Data error"))
//                }
//            }
//
//            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
//                onError?.invoke(t)
//            }
//        })
        api.getUsers().subscribeBy(
            onSuccess = {
                onSuccess(it)
            },
            onError = {
                onError?.invoke(it)
            }
        )
    }

    override fun getUsers(): Single<List<UserEntity>> = api.getUsers()
}