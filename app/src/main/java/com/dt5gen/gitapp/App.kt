package com.dt5gen.gitapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {
}

val Context.app: App get() = applicationContext as App  // теперь в MainActivity удобно доставать app, моментальный доступ к синглтону
val Fragment.frApp: App get() = requireActivity().applicationContext as App  //  моментальный доступ к синглтону фрагмента