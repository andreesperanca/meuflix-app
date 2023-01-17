package com.voltaire.meuflix.ui

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.voltaire.meuflix.adapters.GenreAdapter
import com.voltaire.meuflix.adapters.HighlightsMoviesAdapter
import com.voltaire.meuflix.databinding.FragmentMoviesBinding
import com.voltaire.meuflix.utils.CarouselLayoutManager
import com.voltaire.meuflix.utils.generics.AutoScrollRecyclerView
import kotlinx.coroutines.*

class MoviesFragment : Fragment() {

    private val adapterTest by lazy {
        HighlightsMoviesAdapter()
    }

    val binding by lazy {
        FragmentMoviesBinding.inflate(layoutInflater)
    }

    private lateinit var recyclerViewCarousel: AutoScrollRecyclerView
    lateinit var recyclerView: RecyclerView
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ConfigureRecyclerViewCarousel
        recyclerViewCarousel = binding.rvMoviesHighlights
        recyclerViewCarousel.apply {


            setCanTouch(true)
            isLoopEnabled = true
            setCanTouch(true)
            startAutoScroll()


            val carousel = CarouselLayoutManager(
                isLoop = false,
                isItem3D = true,
                ratio = 1000f,
                flat = true,
                alpha = false,
                isScrollingEnabled = true
            )
            val linear = LinearLayoutManager(requireContext(), HORIZONTAL, false)

            layoutManager = linear

            adapter = adapterTest
        }

        //ConfigureRecyclerViewMovies
        recyclerView = binding.rvMovies
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
            adapter = GenreAdapter()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewCarousel)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            autoScroll()
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun autoScroll() {
        val speedScroll = 3000
        val handler = Handler()
        val runnable: java.lang.Runnable = object : java.lang.Runnable {
            override fun run() {
                TODO("Not yet implemented")
            }
        }
    }
}

//    fun setupToolbar() {
//        binding.tbMoviesToolbar.toolbar.apply {
//            inflateMenu(R.menu.main_menu)
//            setOnMenuItemClickListener {
//                when(it.itemId) {
//                    R.id.fetchFavorites -> {
//                        true
//                    }
//                    R.id.fetchForGenre -> {
//                        true
//                    }
//                    R.id.searchForName -> {
//                        true
//                    }
//                    else -> {false}
//                }
//            }
//        }
//    }