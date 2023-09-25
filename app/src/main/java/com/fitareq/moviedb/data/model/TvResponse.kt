package com.fitareq.moviedb.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("page")
    @Expose
    var page: Int? = null,
    @SerializedName("results")
    @Expose
    var results: ArrayList<TvShow> = arrayListOf(),
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
)

data class TvShow(
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,
    @SerializedName("original_name")
    @Expose
    var originalName: String? = null,
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
    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String? = null,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null,
    @SerializedName("origin_country")
    @Expose
    var originCountry: ArrayList<String> = arrayListOf()

)