package com.fitareq.moviedb.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitareq.moviedb.data.Data
import com.fitareq.moviedb.data.model.Details
import com.fitareq.moviedb.data.repository.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DetailsRepository
) : ViewModel() {
    private val _details = MutableLiveData<Data<Details>>()
    val details: LiveData<Data<Details>> = _details

    fun getDetails(id: Int, isMovie: Boolean) {
        _details.postValue(Data.Loading)
        viewModelScope.launch {
            repository.getDetails(id, isMovie).let {
                _details.postValue(it)
            }
        }
    }
}