package com.fitareq.moviedb.ui.details

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.fitareq.moviedb.R
import com.fitareq.moviedb.Utils
import com.fitareq.moviedb.common.BaseActivity
import com.fitareq.moviedb.data.Data
import com.fitareq.moviedb.data.model.Details
import com.fitareq.moviedb.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private var isMovie = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(Utils.KEY_ID, -1)
        isMovie = intent.getBooleanExtra(Utils.KEY_IS_MOVIE, true)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Details"

        if (id != -1) {
            viewModel.getDetails(id, isMovie)
        } else {
            Toast.makeText(this, "Failed to load details", Toast.LENGTH_SHORT).show()
        }

        viewModel.details.observe(this) {
            when (it) {
                is Data.Loading -> {
                    showLoadingScreen()
                }

                is Data.Success -> {
                    loadData(it.data)
                    hideLoadingScreen()
                }

                is Data.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    hideLoadingScreen()
                }
            }
        }
    }

    private fun loadData(data: Details) {
        binding.apply {
            val imageUrl = "${Utils.IMAGE_BASE}${data.backdropPath}"
            Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder).into(image)
            if (isMovie) {
                title.text = data.title
                duration.visibility = View.VISIBLE
            } else {
                title.text = data.name
                duration.visibility = View.GONE
            }
            subtitle.text = data.tagline
            duration.text = "${data.runtime}m"
            rating.text = "${data.voteAverage}"
            overview.text = data.overview

            genre.adapter = GenreAdapter(data.genres)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}