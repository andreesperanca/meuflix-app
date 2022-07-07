package com.voltaire.meuflix.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.voltaire.meuflix.databinding.CategoryItemBinding
import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.ui.MovieActivity
import com.voltaire.meuflix.utils.MOVIE_ID_KEY

class GenreAdapter(
    private val context: Context,
    private val listGenre: MutableList<Genre> = mutableListOf(),
    var listener: (genre: Genre) -> Unit = {}
) : RecyclerView.Adapter<GenreAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(listGenre[position])
    }

    override fun getItemCount(): Int = listGenre.size

    fun updateData(data: List<Genre>?) {
        notifyItemRangeRemoved(0, this.listGenre.size)
        this.listGenre.clear()
        data?.let { this.listGenre.addAll(it) }
        notifyItemRangeInserted(0, this.listGenre.size)
    }

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var genre: Genre

        init {
            binding.root.setOnClickListener {
                if (::genre.isInitialized) {
                    listener(genre)
                }
            }
        }

        fun bind(genre: Genre) {
            //name genre
            binding.txtTitle.text = genre.name
            // configure recyclerView
                binding.rvCategory.layoutManager =
                    LinearLayoutManager(itemView.context, HORIZONTAL, false)
                binding.rvCategory.adapter = MovieAdapter(listMovie = genre.listMovies)
        }
    }
}