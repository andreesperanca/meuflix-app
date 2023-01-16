package com.voltaire.meuflix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview
import com.voltaire.meuflix.R
import com.voltaire.meuflix.adapters.GenreAdapter
import com.voltaire.meuflix.adapters.HighlightsMoviesAdapter
import com.voltaire.meuflix.databinding.FragmentMoviesBinding
import kotlin.math.floor

class MoviesFragment : Fragment() {

    val binding by lazy {
        FragmentMoviesBinding.inflate(layoutInflater)
    }

    private lateinit var recyclerViewCarousel: CarouselRecyclerview
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ConfigureRecyclerViewCarousel
        recyclerViewCarousel = binding.rvMoviesHighlights
        recyclerViewCarousel.apply {
            adapter = HighlightsMoviesAdapter()
            set3DItem(true)
            setInfinite(true)
            setIntervalRatio(100f)
        }

        //ConfigureRecyclerViewMovies
        recyclerView = binding.rvMovies
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = GenreAdapter()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupToolbar()
        return binding.root
    }


    fun setupToolbar() {
        binding.tbMoviesToolbar.apply {
            inflateMenu(R.menu.main_menu)
            title = getString(R.string.app_name)
            setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.fetchFavorites -> {
                        true
                    }
                    R.id.fetchForGenre -> {
                        true
                    }
                    R.id.searchForName -> {
                        true
                    }
                    else -> {false}
                }
            }
        }
    }
}