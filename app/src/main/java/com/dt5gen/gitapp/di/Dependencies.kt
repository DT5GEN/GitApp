package com.dt5gen.gitapp.di

import com.dt5gen.gitapp.data.retrofit.GithubApi
import com.dt5gen.gitapp.data.retrofit.RetrofitUsersRepoImpl
import com.dt5gen.gitapp.domain.repos.UsersRepo
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Array.get
import java.util.*

interface Di {

    val users2Repo : UsersRepo
    val getRandomString: String
}

class DiDependenciesImpl: Di {
    private val baseUrl = "https://api.github.com/"
    private val retrofit by lazy {   Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build() }
    private val api: GithubApi by lazy { retrofit.create(GithubApi::class.java)}
   override val users2Repo: UsersRepo by lazy { RetrofitUsersRepoImpl(api) }
    override val getRandomString: String
        get() = UUID.randomUUID().toString()

}