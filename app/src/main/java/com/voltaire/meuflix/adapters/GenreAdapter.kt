package com.voltaire.meuflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.voltaire.meuflix.SimpleCategory
import com.voltaire.meuflix.categories
import com.voltaire.meuflix.databinding.CategoryItemBinding

class GenreAdapter(): RecyclerView.Adapter<GenreAdapter.CategoryViewHolder>() {

    val categoryList: List<SimpleCategory> = categories

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int = categoryList.size

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: SimpleCategory) {
            //name genre
            binding.txtTitle.text = category.name
            // configure recyclerView
                binding.rvCategory.layoutManager =
                    LinearLayoutManager(itemView.context, HORIZONTAL, false)
                binding.rvCategory.adapter = MovieAdapter(listMovie = category.movieList)
        }
    }
}