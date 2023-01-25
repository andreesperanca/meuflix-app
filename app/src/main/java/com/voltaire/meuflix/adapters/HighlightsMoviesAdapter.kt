package com.voltaire.meuflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.voltaire.meuflix.R
import com.voltaire.meuflix.adapters.HighlightsMoviesAdapter.*
import com.voltaire.meuflix.databinding.HighlightsMovieItemBinding
import com.voltaire.meuflix.models.Movie

class HighlightsMoviesAdapter() : RecyclerView.Adapter<HighlightsMoviesViewHolder>() {

    private var highlightsMoviesList: List<Movie> = listOf(
        Movie(
            id = 0,
            adult = false,
            backdropPath = "",
            title = "",
            genreIds = listOf(0,1),
            posterPath = "",
            overview = "",
            vote_average = 2.0f
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightsMoviesViewHolder {
        val binding =
            HighlightsMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HighlightsMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HighlightsMoviesViewHolder, position: Int) {
        holder.bind(highlightsMoviesList[position])
    }

    override fun getItemCount(): Int = highlightsMoviesList.size
    fun updateList(data: List<Movie>?) {
        if (data != null) {
            highlightsMoviesList = data
            notifyItemRangeChanged(0,data.size)
        }
    }

    inner class HighlightsMoviesViewHolder(private val binding: HighlightsMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            Glide
                .with(binding.root)
                .load("https://image.tmdb.org/t/p/w500/${movie.backdropPath}")
                .centerCrop()
                .placeholder(R.drawable.placeholder_bg)
                .into(binding.ivImgCover);
        }
    }
}