package com.dt5gen.gitapp.di

import com.dt5gen.gitapp.domain.repos.UsersRepo
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton

interface AppComponent {

    fun getUsersRepo(): UsersRepo

}