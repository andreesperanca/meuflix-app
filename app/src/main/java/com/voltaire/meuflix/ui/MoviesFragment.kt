package com.voltaire.meuflix.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.voltaire.meuflix.R
import com.voltaire.meuflix.adapters.GenreAdapter
import com.voltaire.meuflix.adapters.HighlightsMoviesAdapter
import com.voltaire.meuflix.databinding.FragmentMoviesBinding
import com.voltaire.meuflix.utils.Resource
import com.voltaire.meuflix.utils.custom.CustomToolbar
import com.voltaire.meuflix.utils.custom.KScrollRecyclerView
import com.voltaire.meuflix.utils.generics.BaseFragment
import com.voltaire.meuflix.utils.toastCreator
import com.voltaire.meuflix.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment<
        FragmentMoviesBinding,
        MoviesViewModel>(
    R.layout.fragment_movies
) {
    /** UI COMPONENTS **/
    private lateinit var cToolbar: CustomToolbar
    private lateinit var recyclerViewCarousel: KScrollRecyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var pBar: ProgressBar

    /** HELPER CLASSES **/
    private val normalRvAdapter by lazy {
        GenreAdapter()
    }
    private val adapterTest by lazy {
        HighlightsMoviesAdapter()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMoviesHighlights.apply {
            val linear = LinearLayoutManager(requireContext(), HORIZONTAL, false)
            layoutManager = linear
            adapter = adapterTest
        }
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
            adapter = normalRvAdapter
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.fetchContent()
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
    override fun setupViewModel() {
        val viewModel: MoviesViewModel by viewModel()
        this.viewModel = viewModel
    }
    override fun setupObservers() {
        viewModel.categoriesResource.observe(viewLifecycleOwner) { categoriesResource ->
            when (categoriesResource) {
                is Resource.Loading -> {
                    viewModel.setLoadingUI(true)
                }
                is Resource.Success -> {
                    normalRvAdapter.updateList(categoriesResource.data)
                    viewModel.setLoadingUI(false)
                }
                is Resource.Error -> {
                    toastCreator(categoriesResource.message ?: getString(R.string.connectionError))
                }
            }
        }

        viewModel.moviesResource.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    toastCreator(getString(R.string.connectionError))
                }
                is Resource.Loading -> {
                    viewModel.setLoadingUI(true)
                }
                is Resource.Success -> {
                    binding.rvMoviesHighlights.apply {
                        adapterTest.updateList(it.data)
                        setCanTouch(true)
                        setIsInfinite(true)
                        startAutoScroll()
                    }
                }
            }
        }

        viewModel.loadingUI.observe(viewLifecycleOwner) {
            when (it) {
                true -> {
                    pBar.visibility = View.VISIBLE
                }
                false -> {
                    pBar.visibility = View.INVISIBLE
                }
            }
        }
    }
}