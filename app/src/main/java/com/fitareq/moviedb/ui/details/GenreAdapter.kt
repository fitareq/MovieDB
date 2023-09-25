package com.fitareq.moviedb.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fitareq.moviedb.data.model.Genres
import com.fitareq.moviedb.databinding.ItemGenreBinding

class GenreAdapter(
    private val items: List<Genres>
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding.genreChip.text = items[position].name
    }

    inner class GenreViewHolder(val binding: ItemGenreBinding) :
        ViewHolder(binding.root)
}