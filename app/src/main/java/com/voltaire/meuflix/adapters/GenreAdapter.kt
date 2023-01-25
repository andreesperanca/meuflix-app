package com.voltaire.meuflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.voltaire.meuflix.SimpleCategory
import com.voltaire.meuflix.categories
import com.voltaire.meuflix.databinding.CategoryItemBinding
import com.voltaire.meuflix.models.Category

class GenreAdapter(): RecyclerView.Adapter<GenreAdapter.CategoryViewHolder>() {

    var categoryList: List<Category> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int = categoryList.size
    fun updateList(data: List<Category>?) {
        if(data != null) {
            categoryList = data
            notifyItemRangeChanged(0, data.size)
        }
    }

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            //name genre
            binding.txtTitle.text = category.name
            // configure recyclerView
                binding.rvCategory.layoutManager =
                    LinearLayoutManager(itemView.context, HORIZONTAL, false)
                binding.rvCategory.adapter = MovieAdapter(listMovie = category.movieList)
        }
    }
}