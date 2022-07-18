package com.dt5gen.gitapp.di

import com.dt5gen.gitapp.data.retrofit.GithubApi
import com.dt5gen.gitapp.data.retrofit.RetrofitUsersRepoImpl
import com.dt5gen.gitapp.domain.repos.UsersRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class AppModule {

    private val baseUrl = "https://api.github.com/"

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    @Provides
    fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    fun provideUsersRepo(api: GithubApi): UsersRepo {
        return RetrofitUsersRepoImpl(api)
    }


}