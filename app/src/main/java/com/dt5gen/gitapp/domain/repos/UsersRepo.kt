package com.dt5gen.gitapp.domain.repos

import com.dt5gen.gitapp.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Single

interface UsersRepo {

    // CRUD  << (-) Create, (+) Read, (-) Update, (-) Delete >>

    // Read  // на Callback-ax
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    // Read  // на Callback-ax
    fun getUsers(): Single<List<UserEntity>>
}