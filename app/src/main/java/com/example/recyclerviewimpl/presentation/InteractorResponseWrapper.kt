package com.example.recyclerviewimpl.presentation

import io.reactivex.Observable
import io.reactivex.Single

sealed class InteractorResponseWrapper<T>(val klass: Class<T>?) {

    class Data<T>(val data: T, clazz: Class<T>) : InteractorResponseWrapper<T>(clazz)

    class Error<T>(val error: Throwable, clazz: Class<T>) : InteractorResponseWrapper<T>(clazz)

    class Progress<T>(clazz: Class<T>) : InteractorResponseWrapper<T>(clazz)

    inline fun <reified T> typeOf(): Boolean = this.klass == T::class.java

    companion object {
        inline fun <reified T> errorOf(error: Throwable): Error<T> =
            Error(error, T::class.java)

        inline fun <reified T> dataOf(data: T) = Data(data, T::class.java)

        inline fun <reified T> progressOf() = Progress(T::class.java)
    }
}


inline fun <reified T> Single<T>.toIrw(): Observable<out InteractorResponseWrapper<T>> {
    return toObservable()
        .map<InteractorResponseWrapper<T>> { InteractorResponseWrapper.Data(it, T::class.java) }
        .onErrorReturn { InteractorResponseWrapper.Error(it, T::class.java) }
        .startWith(InteractorResponseWrapper.Progress(T::class.java))
}