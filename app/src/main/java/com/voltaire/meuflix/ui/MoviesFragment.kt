package com.voltaire.meuflix.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.voltaire.meuflix.R
import com.voltaire.meuflix.adapters.GenreAdapter
import com.voltaire.meuflix.adapters.HighlightsMoviesAdapter
import com.voltaire.meuflix.databinding.FragmentMoviesBinding
import com.voltaire.meuflix.utils.custom.KScrollRecyclerView
import com.voltaire.meuflix.utils.custom.CustomToolbar
import com.voltaire.meuflix.utils.generics.BaseFragment
import com.voltaire.meuflix.utils.toastCreator

class MoviesFragment : BaseFragment<FragmentMoviesBinding>(
    R.layout.fragment_movies
) {
    /** UI COMPONENTS **/
    private lateinit var cToolbar: CustomToolbar
    private lateinit var recyclerViewCarousel: KScrollRecyclerView
    private lateinit var recyclerView: RecyclerView

    /** HELPER CLASSES **/
    private val adapterTest by lazy {
        HighlightsMoviesAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewCarousel.apply {
            setCanTouch(true)
            setIsInfinite(true)
            startAutoScroll()
            val linear = LinearLayoutManager(requireContext(), HORIZONTAL, false)
            layoutManager = linear
            adapter = adapterTest
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
            adapter = GenreAdapter()
        }
    }
    override fun initComponents() {
        with(binding) {
            cToolbar = tbMoviesToolbar
            recyclerView = rvMovies
            recyclerViewCarousel = rvMoviesHighlights
        }
    }
    override fun setupToolbar() {
        cToolbar.apply {
            userPhotoProfileClick {
                val configurationLayout = binding.motionLayout2
                if (configurationLayout.currentState == R.id.endMyAccount) configurationLayout.transitionToStart()
                else configurationLayout.transitionToEnd()
            }
            favoritesClick {
                toastCreator("Favorite Button Click") }
            searchIconClick { toastCreator("Search Button Click") }
        }
    }
    override fun setupViewModel() {}
    override fun setupObservers() {}
}