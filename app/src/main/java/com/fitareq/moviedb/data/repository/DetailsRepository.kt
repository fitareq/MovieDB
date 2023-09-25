package com.fitareq.moviedb.data.repository

import com.fitareq.moviedb.Utils
import com.fitareq.moviedb.data.Api
import com.fitareq.moviedb.data.Data
import com.fitareq.moviedb.data.model.Details
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val api: Api
) {
    suspend fun getDetails(id: Int, isMovie: Boolean): Data<Details> {
        return try {
            val response = if (isMovie) api.getDetails(
                id,
                Utils.LANGUAGE,
                Utils.API_KEY
            ) else api.getDetailsTV(id, Utils.LANGUAGE, Utils.API_KEY)
            if (response.isSuccessful && response.code() == 200) {
                Data.Success(response.body()!!)
            } else Data.Error(response.message())
        } catch (e: Exception) {
            Data.Error(e.message ?: "Something went wrong")
        }
    }
}