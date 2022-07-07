package com.voltaire.meuflix.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.voltaire.meuflix.R
import com.voltaire.meuflix.ui.MovieActivity
import com.voltaire.meuflix.databinding.MovieItemBinding
import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.models.Movie
import com.voltaire.meuflix.utils.MOVIE_ID_KEY

class MovieAdapter (
    private val listMovie : List<Movie> = emptyList()
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(private val binding : MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            val context = binding.root.context

            Glide
                .with(binding.root.context)
                .load(movie.urlImage)
                .placeholder(R.drawable.movie_4)
                .into(binding.imgCover)

            binding.root.setOnClickListener {
                val intent = Intent(context, MovieActivity::class.java)
                intent.putExtra(MOVIE_ID_KEY, movie)
                context.startActivity(intent)
            }
        }
    }
}