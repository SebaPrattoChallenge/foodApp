package com.sebapp.challenge.data.model

sealed class Response<out T> {

    class Loading<out T> : Response<T>()
    data class Success<out T>(val data: T) : Response<T>()
    data class Failure(val e: Throwable) : Response<Nothing>()
}
