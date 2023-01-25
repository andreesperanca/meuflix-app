package com.voltaire.meuflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.voltaire.meuflix.R
import com.voltaire.meuflix.databinding.MovieItemBinding
import com.voltaire.meuflix.models.Movie

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
                .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                .placeholder(R.drawable.placeholder_bg)
                .into(binding.ivImgCover)

            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, "Click Item", Toast.LENGTH_SHORT).show()
            }
        }
    }
}