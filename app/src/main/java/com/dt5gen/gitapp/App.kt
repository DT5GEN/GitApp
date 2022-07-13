package com.dt5gen.gitapp

  import android.app.Application
  import android.content.Context
  import android.os.Handler
  import android.os.Looper
  import androidx.fragment.app.Fragment
  import com.dt5gen.gitapp.data.retrofit.GithubApi
  import com.dt5gen.gitapp.data.retrofit.RetrofitUsersRepoImpl
  import com.dt5gen.gitapp.di.appModule
  import com.dt5gen.gitapp.domain.repos.UsersRepo
  import org.koin.android.ext.koin.androidContext
  import org.koin.android.ext.koin.androidLogger
  import org.koin.core.context.startKoin
  import retrofit2.Retrofit
  import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
  import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}

val Context.app: App get() = applicationContext as App  // теперь в MainActivity удобно доставать app, моментальный доступ к синглтону
val Fragment.frApp: App get() = requireActivity().applicationContext as App  //  моментальный доступ к синглтону фрагмента