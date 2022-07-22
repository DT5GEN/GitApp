package com.dt5gen.dilibra

import kotlin.reflect.KClass

object DiDependenciesImpl {

    private val dependenciesHolder = HashMap<KClass<*>, DependencyFabric<*>>()


    fun <T : Any> get(clazz: KClass<T>): T {
        val dependencyFabric = dependenciesHolder[clazz]
        if (dependencyFabric != null) {
            return dependencyFabric.get() as T
        } else {
            throw IllegalArgumentException("Have not class in graph -> $this")
        }
    }

    fun <T : Any> add(clazz: KClass<T>, dependencyFabric: DependencyFabric<T>) {
        dependenciesHolder[clazz] = dependencyFabric
    }

    inline fun <reified T : Any> add( dependencyFabric: DependencyFabric<T>) {
        add(T::class, dependencyFabric)
    }

    //   fun <T : Any> add(dependencyFabric: DependencyFabric) {
    //       dependenciesHolder[dependency::class] = dependencyFabric
    //   } // TODO

}


inline fun <reified T : Any> get(): T {
    return DiDependenciesImpl.get(T::class)
}


inline fun <reified T : Any> inject() = lazy {
    get<T>()
}


abstract class DependencyFabric <T: Any> (protected val creator: () -> Any) {
    abstract fun get(): Any
}

class Singleton <T: Any> (creator: () -> Any) : DependencyFabric<T>(creator) {
    private val dependency: Any by lazy { creator.invoke() }

    override fun get(): Any = dependency
}

class Fabric <T: Any> (creator: () -> Any) : DependencyFabric<T>(creator) {

    override fun get(): Any = creator()
}



class  Module(private val block: Module.() -> Any){
    fun install(){
        block()
    }



    inline fun <reified T : Any> singleton (noinline creator: () -> T) {
        DiDependenciesImpl.add(Singleton<T>(creator))
    }

    inline fun <reified T : Any> fabric (noinline creator: () -> T) {
        DiDependenciesImpl.add(Fabric<T>(creator))
    }

}
