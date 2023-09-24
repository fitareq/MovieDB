package com.fitareq.moviedb.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.fitareq.moviedb.Utils
import com.fitareq.moviedb.data.Api
import com.fitareq.moviedb.data.Data
import com.fitareq.moviedb.data.data_source.MoviePagingSource
import com.fitareq.moviedb.data.model.Movie
import com.fitareq.moviedb.data.model.MovieResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: Api
) {
    suspend fun getMovies(pageNo: Int): Data<MovieResponse> {
        return try {
            val response = api.getMovies(Utils.LANGUAGE, Utils.API_KEY, pageNo)
            if (response.isSuccessful && response.body() != null) {
                Data.Success(response.body()!!)
            } else {
                Data.Error("Error in loading data")
            }
        } catch (e: Exception) {
            Data.Error("Something went wrong")
        }
    }

    fun getAllMovies(): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false,
                initialLoadSize = 1
            ),
            pagingSourceFactory = {
                MoviePagingSource(api)
            },
            initialKey = 1
        ).liveData
    }
}