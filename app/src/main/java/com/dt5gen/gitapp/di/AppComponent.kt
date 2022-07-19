package com.dt5gen.gitapp.di

import com.dt5gen.gitapp.ui.users.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton

interface AppComponent {

    fun inject(mainActivity: MainActivity)
}