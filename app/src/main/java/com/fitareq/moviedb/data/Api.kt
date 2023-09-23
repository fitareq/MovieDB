package com.fitareq.moviedb.data

import com.fitareq.moviedb.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("trending/movie/day")
    suspend fun getMovies(
        @Query("language") lang: String,
        @Query("api_key") key: String,
        @Query("page") pageNo: Int
    ):Response<MovieResponse>
}