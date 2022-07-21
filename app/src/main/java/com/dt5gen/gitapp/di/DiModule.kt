package com.dt5gen.gitapp.di

import com.dt5gen.dilibra.DiDependenciesImpl
import com.dt5gen.gitapp.data.retrofit.GithubApi
import com.dt5gen.gitapp.data.retrofit.RetrofitUsersRepoImpl
import com.dt5gen.gitapp.domain.repos.UsersRepo
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class DiModule (di: DiDependenciesImpl) {

    private val baseUrl = "https://api.github.com/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    private val api: GithubApi by lazy { retrofit.create(GithubApi::class.java) }


    private val users2Repo: UsersRepo by lazy { RetrofitUsersRepoImpl(api) }
    private val randomString: String
        get() = UUID.randomUUID().toString()


    init {
        di.add(UsersRepo::class, users2Repo)
        di.add(randomString)
    }

}