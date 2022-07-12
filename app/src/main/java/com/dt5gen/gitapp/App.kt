package com.dt5gen.gitapp

  import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
  import com.dt5gen.gitapp.data.retrofit.GithubApi
  import com.dt5gen.gitapp.data.retrofit.RetrofitUsersRepoImpl
import com.dt5gen.gitapp.domain.repos.UsersRepo
  import retrofit2.Retrofit
  import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
  import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
private val baseUrl = "https://api.github.com/"
    private val retrofit by lazy {   Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build() }
    private val api: GithubApi by lazy { retrofit.create(GithubApi::class.java)}
    val users2Repo: UsersRepo by lazy { RetrofitUsersRepoImpl(api) }


    override fun onCreate() {
        super.onCreate()
        // todo
    }
}

val Context.app: App get() = applicationContext as App  // теперь в MainActivity удобно доставать app, моментальный доступ к синглтону
val Fragment.frApp: App get() = requireActivity().applicationContext as App  //  моментальный доступ к синглтону фрагмента