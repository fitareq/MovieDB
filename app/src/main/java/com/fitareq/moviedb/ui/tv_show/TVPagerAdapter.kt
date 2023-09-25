package com.fitareq.moviedb.ui.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fitareq.moviedb.R
import com.fitareq.moviedb.Utils
import com.fitareq.moviedb.data.model.Movie
import com.fitareq.moviedb.data.model.TvShow
import com.fitareq.moviedb.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class TVPagerAdapter(
    private val callBack: (Int)->Unit
) :
    PagingDataAdapter<TvShow, TVPagerAdapter.TVViewHolder>(TVComparator) {


    class TVViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    object TVComparator : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.originalName == newItem.originalName
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        val movie = getItem(position)!!
        val imageUrl = "${Utils.IMAGE_BASE}${movie.posterPath}"
        holder.binding.image.apply {
            clipToOutline = true
            Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder).into(this)
        }

        holder.binding.title.text = movie.name
        holder.binding.root.setOnClickListener {
            callBack(movie.id!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        return TVViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}