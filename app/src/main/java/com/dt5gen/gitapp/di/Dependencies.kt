package com.dt5gen.gitapp.di

import kotlin.reflect.KClass

interface Di {

    fun <T : Any> get(clazz: KClass<T>): T
    fun <T : Any> add(clazz: KClass<*> , dependency: T)
    fun <T : Any> add( dependency: T)

}

class DiDependenciesImpl : Di {

    private val dependenciesHolder = HashMap<KClass<*>, Any>()

    override fun <T : Any> get(clazz: KClass<T>): T {
        if (dependenciesHolder.containsKey(clazz)) {
            return dependenciesHolder[clazz] as T
        } else {
            throw IllegalArgumentException("Have not class in graph -> $this")
        }
    }


    override fun <T : Any> add(clazz: KClass<*> , dependency: T) {
        dependenciesHolder[clazz] = dependency
    }


    override fun <T : Any> add( dependency: T) {
        dependenciesHolder[dependency::class] = dependency
    }

}