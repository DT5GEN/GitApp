package com.dt5gen.gitapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.dt5gen.gitapp.data.retrofit.RetrofitUsersRepoImpl
import com.dt5gen.gitapp.domain.repos.UsersRepo

class App : Application() {

    val users2Repo: UsersRepo by lazy { RetrofitUsersRepoImpl() }


    override fun onCreate() {
        super.onCreate()
        // todo
    }
}

val Context.app: App get() = applicationContext as App  // теперь в MainActivity удобно доставать app, моментальный доступ к синглтону
val Fragment.frApp: App get() = requireActivity().applicationContext as App  //  моментальный доступ к синглтону фрагмента