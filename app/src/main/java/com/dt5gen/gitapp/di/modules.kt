package com.dt5gen.gitapp.di

import com.dt5gen.gitapp.data.retrofit.GithubApi
import com.dt5gen.gitapp.data.retrofit.RetrofitUsersRepoImpl
import com.dt5gen.gitapp.domain.repos.UsersRepo
import com.dt5gen.gitapp.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    val baseUrl = "https://api.github.com/"

    // single instance of HelloRepository
    single<UsersRepo> { RetrofitUsersRepoImpl(get()) }
    single {
        Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .build()

    }

    // Simple Presenter Factory
    factory <GithubApi> {
        get<Retrofit>().create(GithubApi::class.java) }


    viewModel {

        UsersViewModel(get())

    }

}

//private val baseUrl = "https://api.github.com/"
//private val retrofit by lazy {   Retrofit.Builder()
//    .baseUrl(baseUrl)
//    .addConverterFactory(GsonConverterFactory.create())
//    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//    .build() }
//private val api: GithubApi by lazy { retrofit.create(GithubApi::class.java)}
//private val uiHandler: Handler by lazy { Handler(Looper.getMainLooper()) }
//
//val users2Repo: UsersRepo by lazy { RetrofitUsersRepoImpl(api) }




