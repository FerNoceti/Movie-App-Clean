package com.pil.movieapp.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pil.movieapp.databinding.ActivityMovieBinding
import com.pil.movieapp.presentation.adapter.MovieAdapter
import com.pil.movieapp.presentation.viewmodel.MovieViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMovieBinding
    private val viewModel: MovieViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getValue().observe(this) { updateUI(it) }

        binding.buttonBack.setOnClickListener {
            viewModel.goBack()
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.callService()
    }

    private fun updateUI(data: MovieViewModel.MainData) {
        when (data.status) {
            MovieViewModel.MainStatus.SHOW_INFO -> {
                binding.recycler.adapter = MovieAdapter(data.movies)
                binding.recycler.layoutManager = LinearLayoutManager(this)
            }

            MovieViewModel.MainStatus.EMPTY -> {
                binding.recycler.visibility = View.GONE
                binding.empty.visibility = View.VISIBLE
            }

            MovieViewModel.MainStatus.GO_BACK -> {
                finish()
            }
        }
    }
}
