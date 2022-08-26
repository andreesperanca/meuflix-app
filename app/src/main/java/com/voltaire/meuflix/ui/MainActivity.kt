package com.voltaire.meuflix.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.voltaire.meuflix.adapters.GenreAdapter
import com.voltaire.meuflix.databinding.ActivityMainBinding
import com.voltaire.meuflix.repositories.HomeRepository
import com.voltaire.meuflix.retrofit.webclient.GenreWebClient
import com.voltaire.meuflix.utils.toastCreator
import com.voltaire.meuflix.viewmodel.HomeViewModel
import com.voltaire.meuflix.viewmodel.MovieViewModel
import com.voltaire.meuflix.viewmodel.factory.HomeViewModelFactory
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        GenreAdapter(this)
    }

    private val viewModel: HomeViewModel by viewModel()

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
