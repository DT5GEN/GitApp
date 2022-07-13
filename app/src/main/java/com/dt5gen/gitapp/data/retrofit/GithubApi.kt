package com.dt5gen.gitapp.data.retrofit

import com.dt5gen.gitapp.domain.entities.UserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    fun getUsers(): Single<List<UserEntity>>  // событие, которое приходит один раз (an event that comes once)
    // fun getUsers(): Maybe<List<UserEntity>>  // Observable, который может что-то испустит, а может нет (Observable, which may or may not emit something)
    // fun getUsers(): Completable  // ничего  не испускает, а только завершает своё существование. Когда нужно передать свои данные - закрылись и всё
    // fun getUsers(): Observable<List<UserEntity>>  // цепочка, испускающая данный объект через Observable, будет постоянно их испускать
}