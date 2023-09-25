package com.fitareq.moviedb.data

import com.fitareq.moviedb.data.model.Details
import com.fitareq.moviedb.data.model.MovieResponse
import com.fitareq.moviedb.data.model.TvResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("trending/movie/day")
    suspend fun getMovies(
        @Query("language") lang: String,
        @Query("api_key") key: String,
        @Query("page") pageNo: Int
    ): Response<MovieResponse>

    @GET("trending/tv/day")
    suspend fun getTvShows(
        @Query("language") lang: String,
        @Query("api_key") key: String,
        @Query("page") pageNo: Int
    ): Response<TvResponse>

    @GET("movie/{id}")
    suspend fun getDetails(
        @Path("id") id: Int,
        @Query("language") lang: String,
        @Query("api_key") key: String
    ): Response<Details>

    @GET("tv/{id}")
    suspend fun getDetailsTV(
        @Path("id") id: Int,
        @Query("language") lang: String,
        @Query("api_key") key: String
    ): Response<Details>
}