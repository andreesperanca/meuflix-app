package com.voltaire.meuflix.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.voltaire.meuflix.R
import com.voltaire.meuflix.adapters.GenreAdapter
import com.voltaire.meuflix.adapters.HighlightsMoviesAdapter
import com.voltaire.meuflix.databinding.FragmentMoviesBinding
import com.voltaire.meuflix.models.Request
import com.voltaire.meuflix.utils.Resource
import com.voltaire.meuflix.utils.custom.KScrollRecyclerView
import com.voltaire.meuflix.utils.custom.CustomToolbar
import com.voltaire.meuflix.utils.generics.BaseFragment
import com.voltaire.meuflix.utils.toastCreator
import com.voltaire.meuflix.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment<FragmentMoviesBinding>(
    R.layout.fragment_movies
) {
    /** UI COMPONENTS **/
    private lateinit var cToolbar: CustomToolbar
    private lateinit var recyclerViewCarousel: KScrollRecyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var pBar: ProgressBar

    /** HELPER CLASSES **/
    private val adapterTest by lazy {
        HighlightsMoviesAdapter()
    }

    /** ViewModel **/
    private val viewModel: MoviesViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewCarousel.apply {
            val linear = LinearLayoutManager(requireContext(), HORIZONTAL, false)
            layoutManager = linear
            adapter = adapterTest
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
            adapter = GenreAdapter()
        }

        binding.pgMoviesFragment.visibility = View.INVISIBLE



    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchMoviesWithGenre()
    }

    override fun initComponents() {
        with(binding) {
            cToolbar = tbMoviesToolbar
            recyclerView = rvMovies
            recyclerViewCarousel = rvMoviesHighlights
            pBar = pgMoviesFragment
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
                toastCreator("Favorite Button Click")
            }
            searchIconClick { toastCreator("Search Button Click") }
        }
    }

    override fun setupViewModel() {}
    override fun setupObservers() {

        viewModel.moviesResource.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    toastCreator(getString(R.string.connectionError))
                    binding.pgMoviesFragment.visibility = View.INVISIBLE
                }
                is Resource.Loading -> {
                    binding.pgMoviesFragment.visibility = View.VISIBLE
                }
                is Resource.Success -> {

                    recyclerViewCarousel.apply {
                        adapterTest.updateList(it.data)
                        setCanTouch(true)
                        setIsInfinite(true)
                        startAutoScroll()
                    }
                    binding.pgMoviesFragment.visibility = View.INVISIBLE
                }
            }
        }
    }
}