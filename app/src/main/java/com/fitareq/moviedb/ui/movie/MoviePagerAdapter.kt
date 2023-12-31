package com.fitareq.moviedb.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fitareq.moviedb.R
import com.fitareq.moviedb.Utils
import com.fitareq.moviedb.data.model.Movie
import com.fitareq.moviedb.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MoviePagerAdapter(
    private val callBack: (Int)->Unit
) :
    PagingDataAdapter<Movie, MoviePagerAdapter.MovieViewHolder>(MovieComparator) {


    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.originalTitle == newItem.originalTitle
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)!!
        val imageUrl = "${Utils.IMAGE_BASE}${movie.posterPath}"
        holder.binding.image.apply {
            clipToOutline = true
            Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder).into(this)
        }

        holder.binding.title.text = movie.title

        holder.binding.root.setOnClickListener {
            callBack(movie.id!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}