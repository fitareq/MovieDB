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
import com.fitareq.moviedb.data.data_source.TVPagingSource
import com.fitareq.moviedb.data.model.Movie
import com.fitareq.moviedb.data.model.MovieResponse
import com.fitareq.moviedb.data.model.TvShow
import javax.inject.Inject

class TvRepository @Inject constructor(
    private val api: Api
) {
    fun getAllTvShow(): LiveData<PagingData<TvShow>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false,
                initialLoadSize = 1
            ),
            pagingSourceFactory = {
                TVPagingSource(api)
            },
            initialKey = 1
        ).liveData
    }
}