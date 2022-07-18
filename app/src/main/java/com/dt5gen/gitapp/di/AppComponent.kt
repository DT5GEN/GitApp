package com.dt5gen.gitapp.di

import com.dt5gen.gitapp.domain.repos.UsersRepo
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun getUsersRepo(): UsersRepo

}