package com.voltaire.meuflix.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.bumptech.glide.Glide
import com.voltaire.meuflix.R
import com.voltaire.meuflix.adapters.SimilarMovieAdapter
import com.voltaire.meuflix.databinding.ActivityMovieBinding
import com.voltaire.meuflix.models.Movie
import com.voltaire.meuflix.repositories.MovieRepository
import com.voltaire.meuflix.retrofit.webclient.MoviesWebClient
import com.voltaire.meuflix.utils.MOVIE_ID_KEY
import com.voltaire.meuflix.utils.toastCreator
import com.voltaire.meuflix.viewmodel.MovieViewModel
import com.voltaire.meuflix.viewmodel.factory.MovieViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMovieBinding
    private val viewModel: MovieViewModel by viewModel()
    private lateinit var args: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        args = intent.extras?.getParcelable<Movie>(MOVIE_ID_KEY)!!

        configureToolbar()
        configureInformationMovie()
    }

    override fun onResume() {
        super.onResume()
        loadSimilarMovies()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadSimilarMovies() {
        viewModel.getSimilarMovies(
            genreName = args.genre,
            onComplete = { resource ->
            resource.data.let {
                val listMovies = it?.minus(args)
                binding.movieRvSimilar.adapter = SimilarMovieAdapter(listMovies as MutableList<Movie>)
                binding.movieRvSimilar.layoutManager = GridLayoutManager(this, 2)
            }
            resource.error?.let { error ->  toastCreator(this, error) }
        })
    }

    private fun configureInformationMovie() {
        with(binding) {
            args.let {
                Glide
                    .with(this@MovieActivity)
                    .load(args.urlImage)
                    .placeholder(R.drawable.placeholder_bg)
                    .into(binding.movieImg)
                movieTitle.text = args.name
                movieTxtDesc.text = getString(R.string.description, args.description)
                movieTxtCast.text = getString(R.string.cast, args.cast)
            }
        }
    }
    private fun configureToolbar() {
        setSupportActionBar(binding.movieToolbar)
        supportActionBar?.let { toolbar ->
            toolbar.setDisplayHomeAsUpEnabled(true)
            toolbar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
            toolbar.title = null
        }
    }
}