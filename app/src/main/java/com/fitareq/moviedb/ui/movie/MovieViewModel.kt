package com.fitareq.moviedb.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fitareq.moviedb.data.Data
import com.fitareq.moviedb.data.model.Movie
import com.fitareq.moviedb.data.model.MovieResponse
import com.fitareq.moviedb.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    fun getAllMovies():LiveData<PagingData<Movie>>{
        return repository.getAllMovies().cachedIn(viewModelScope)
    }
}