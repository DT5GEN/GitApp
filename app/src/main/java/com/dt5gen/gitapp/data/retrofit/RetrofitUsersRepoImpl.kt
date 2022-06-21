package com.dt5gen.gitapp.data.retrofit

import com.dt5gen.gitapp.domain.entities.UserEntity
import com.dt5gen.gitapp.domain.repos.UsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUsersRepoImpl : UsersRepo {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val api: GithubApi = retrofit.create(GithubApi::class.java)

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