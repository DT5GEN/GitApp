package com.dt5gen.gitapp.domain.repos

import com.dt5gen.gitapp.domain.entities.UserEntity

interface UsersRepo {

    // CRUD  << (-) Create, (+) Read, (-) Update, (-) Delete >>

    // Read
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}