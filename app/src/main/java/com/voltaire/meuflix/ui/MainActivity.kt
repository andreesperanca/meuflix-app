package com.voltaire.meuflix.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.voltaire.meuflix.adapters.GenreAdapter
import com.voltaire.meuflix.databinding.ActivityMainBinding
import com.voltaire.meuflix.models.Genre
import com.voltaire.meuflix.repositories.HomeRepository
import com.voltaire.meuflix.retrofit.webclient.GenreWebClient
import com.voltaire.meuflix.utils.MOVIE_ID_KEY
import com.voltaire.meuflix.utils.generics.toastCreator
import com.voltaire.meuflix.viewmodel.HomeViewModel
import com.voltaire.meuflix.viewmodel.factory.HomeViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val webClient = GenreWebClient()

    private val adapter by lazy {
        GenreAdapter(this)
    }

    private val viewModel by lazy {
        val repository = HomeRepository(webClient)
        val factory = HomeViewModelFactory(repository)
        val provider = ViewModelProvider(this, factory)
        provider[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        configureRecyclerView()
        getAllGenres()
    }

    private fun getAllGenres() {
        viewModel.getAllGenres(onComplete = { resource ->
            resource.data.let { data -> adapter.updateData(data) }
            resource.error?.let { error -> toastCreator(this, error) }
        })
    }

    private fun configureRecyclerView() {
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(this, VERTICAL, false)
    }
}
