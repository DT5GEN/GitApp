package com.dt5gen.gitapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.dt5gen.gitapp.data.FakeUsersRepoImpl
import com.dt5gen.gitapp.domain.UsersRepo

class App: Application() {

    val users2Repo: UsersRepo by lazy { FakeUsersRepoImpl() }


    override fun onCreate() {
        super.onCreate()
        // todo
    }
}

val Context.app: App get() = applicationContext as App  // теперь в MainActivity удобно доставать app, моментальный доступ к синглтону
val Fragment.frApp: App get() = requireActivity().applicationContext as App  //  моментальный доступ к синглтону фрагмента