package com.dt5gen.gitapp.di

import com.dt5gen.dilibra.Module
import com.dt5gen.dilibra.get
import com.dt5gen.gitapp.data.retrofit.GithubApi
import com.dt5gen.gitapp.data.retrofit.RetrofitUsersRepoImpl
import com.dt5gen.gitapp.domain.repos.UsersRepo
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

val appModule = Module{

    val baseUrl = "https://api.github.com/"

    singleton <Retrofit>{
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    singleton<GithubApi> { get<Retrofit>().create(GithubApi::class.java) }

    singleton<UsersRepo> { RetrofitUsersRepoImpl(get()) }

    fabric { UUID.randomUUID().toString() }
}