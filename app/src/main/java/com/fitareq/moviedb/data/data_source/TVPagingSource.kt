package com.fitareq.moviedb.data.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fitareq.moviedb.Utils
import com.fitareq.moviedb.data.Api
import com.fitareq.moviedb.data.model.TvShow

class TVPagingSource(
    private val api: Api
) : PagingSource<Int, TvShow>() {
    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        return try {
            val position = params.key ?: 1
            val response = api.getTvShows(Utils.LANGUAGE, Utils.API_KEY, position)
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}