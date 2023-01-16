package com.voltaire.meuflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voltaire.meuflix.SimpleMovie
import com.voltaire.meuflix.adapters.HighlightsMoviesAdapter.*
import com.voltaire.meuflix.databinding.HighlightsMovieItemBinding
import com.voltaire.meuflix.movies

class HighlightsMoviesAdapter () : RecyclerView.Adapter<HighlightsMoviesViewHolder>() {

    private val highlightsMoviesList: List<SimpleMovie> = movies.subList(0,3)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightsMoviesViewHolder {
        val binding = HighlightsMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HighlightsMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HighlightsMoviesViewHolder, position: Int) {
        holder.bind(highlightsMoviesList[position])
    }

    override fun getItemCount(): Int = highlightsMoviesList.size

    inner class HighlightsMoviesViewHolder(private val binding: HighlightsMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: SimpleMovie) {
            binding.ivImgCover.setImageResource(movie.cover)
        }
    }
}