package com.fitareq.moviedb.data

sealed class Data<out T> {
    data class Success<out T>(val data: T) : Data<T>()
    data class Error(val message: String) : Data<Nothing>()
    object Loading : Data<Nothing>()
}

