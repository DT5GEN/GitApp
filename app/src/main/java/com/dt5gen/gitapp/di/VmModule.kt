package com.dt5gen.gitapp.di

import com.dt5gen.gitapp.domain.repos.UsersRepo
import com.dt5gen.gitapp.ui.users.UsersViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
class VmModule {

    @Provides

    fun provideUsersVm(usersRepo: UsersRepo): UsersViewModel {
        return UsersViewModel(usersRepo)
    }


}