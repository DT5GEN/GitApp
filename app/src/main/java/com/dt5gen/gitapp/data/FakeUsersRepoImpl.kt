package com.dt5gen.gitapp.data

import android.os.Handler
import com.dt5gen.gitapp.domain.entities.UserEntity
import com.dt5gen.gitapp.domain.repos.UsersRepo
import io.reactivex.rxjava3.core.Single

private const val DATA_LOADING_FAKE_DELAY = 2_000L

class FakeUsersRepoImpl (
    private val uiHandler: Handler
        ): UsersRepo {

    private val data: List<UserEntity> = listOf(
        UserEntity("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4"),
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        uiHandler.postDelayed({
            onSuccess(data)
                   onError?.invoke(IllegalStateException("Ya error!"))  // проверка одноразовых событий ( single event )
        }, DATA_LOADING_FAKE_DELAY)
    }

    override fun getUsers(): Single<List<UserEntity>> = Single.just(data)
}