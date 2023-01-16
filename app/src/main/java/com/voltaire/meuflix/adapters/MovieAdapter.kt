package com.voltaire.meuflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voltaire.meuflix.SimpleMovie
import com.voltaire.meuflix.databinding.MovieItemBinding

class MovieAdapter (
    private val listMovie : List<SimpleMovie> = emptyList()
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
        fun bind(movie: SimpleMovie) {
            val context = binding.root.context

            binding.ivImgCover.setImageResource(movie.cover)

//            Glide
//                .with(binding.root.context)
//                .load(movie.urlImage)
//                .placeholder(R.drawable.movie_4)
//                .into(binding.imgCover)
//
//            binding.root.setOnClickListener {
//                val intent = Intent(context, MovieActivity::class.java)
//                intent.putExtra(MOVIE_ID_KEY, movie)
//                context.startActivity(intent)
//            }
        }
    }
}