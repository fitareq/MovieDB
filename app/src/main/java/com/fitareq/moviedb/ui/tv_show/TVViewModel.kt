package com.fitareq.moviedb.ui.tv_show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fitareq.moviedb.data.Data
import com.fitareq.moviedb.data.model.Movie
import com.fitareq.moviedb.data.model.MovieResponse
import com.fitareq.moviedb.data.model.TvShow
import com.fitareq.moviedb.data.repository.MovieRepository
import com.fitareq.moviedb.data.repository.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVViewModel @Inject constructor(
    private val repository: TvRepository
) : ViewModel() {
    fun getAllTvShow():LiveData<PagingData<TvShow>>{
        return repository.getAllTvShow().cachedIn(viewModelScope)
    }
}