package com.fitareq.moviedb.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    @Expose
    var page: Int? = null,
    @SerializedName("results")
    @Expose
    var results: ArrayList<Results> = arrayListOf(),
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
)

data class Results(

    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,
    @SerializedName("overview")
    @Expose
    var overview: String? = null,
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,
    @SerializedName("media_type")
    @Expose
    var mediaType: String? = null,
    @SerializedName("genre_ids")
    @Expose
    var genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null,
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null,
    @SerializedName("video")
    @Expose
    var video: Boolean? = null,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

)
