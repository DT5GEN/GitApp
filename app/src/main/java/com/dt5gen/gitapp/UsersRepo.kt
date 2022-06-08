package com.dt5gen.gitapp

interface UsersRepo {

    // CRUD  << (-) Create, (+) Read, (-) Update, (-) Delete >>

    // Read
    fun getUsers(
        onSuccess: (List<UserEntity> )-> Unit,
        onError: ((Throwable)-> Unit)? = null
    )
}