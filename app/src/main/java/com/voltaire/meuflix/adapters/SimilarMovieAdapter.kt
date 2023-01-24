package com.voltaire.meuflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.voltaire.meuflix.databinding.MovieItemBinding
import com.voltaire.meuflix.databinding.MovieItemSimilarBinding
import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.models.Movie

class SimilarMovieAdapter(
    private val listSimilarMovies : MutableList<Movie> = mutableListOf<Movie>()
) : RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        val binding = MovieItemSimilarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimilarMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        holder.bind(listSimilarMovies[position])
    }

    override fun getItemCount(): Int = listSimilarMovies.size

    fun updateData(data: List<Movie>) {
        notifyItemRangeRemoved(0, this.listSimilarMovies.size)
        this.listSimilarMovies.clear()
        data.let { this.listSimilarMovies.addAll(it) }
        notifyItemRangeInserted(0, this.listSimilarMovies.size)
    }

    inner class SimilarMovieViewHolder(private val binding : MovieItemSimilarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide
                .with(binding.root.context)
                .load(movie.backdropPath)
                .into(binding.imgCover)
        }
    }
}