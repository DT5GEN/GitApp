package com.dt5gen.dilibra

import kotlin.reflect.KClass

class DiDependenciesImpl {

    private val dependenciesHolder = HashMap<KClass<*>, Any>()

    fun <T : Any> get(clazz: KClass<T>): T {
        if (dependenciesHolder.containsKey(clazz)) {
            return dependenciesHolder[clazz] as T
        } else {
            throw IllegalArgumentException("Have not class in graph -> $this")
        }
    }


    fun <T : Any> add(clazz: KClass<*>, dependency: T) {
        dependenciesHolder[clazz] = dependency
    }


    fun <T : Any> add(dependency: T) {
        dependenciesHolder[dependency::class] = dependency
    }

}