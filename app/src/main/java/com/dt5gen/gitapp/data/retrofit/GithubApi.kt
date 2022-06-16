package com.dt5gen.gitapp.data.retrofit

import com.dt5gen.gitapp.domain.entities.UserEntity
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    fun getUsers(): Call<List<UserEntity>>
}