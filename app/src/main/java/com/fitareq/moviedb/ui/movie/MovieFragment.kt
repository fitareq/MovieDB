package com.fitareq.moviedb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.fitareq.moviedb.common.BaseFragment
import com.fitareq.moviedb.data.Data
import com.fitareq.moviedb.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : BaseFragment() {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel: MovieViewModel by viewModels()
    private val adapter = MoviePagerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        binding.movieList.adapter = adapter
        /*  viewModel.movies.observe(viewLifecycleOwner){
              when(it){
                  is Data.Loading->{
                      showLoadingScreen()
                  }
                  is Data.Success->{
                      Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()
                      hideLoadingScreen()
                  }
                  is Data.Error->{
                      Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()
                      hideLoadingScreen()
                  }
              }
          }*/

        lifecycleScope.launch {
            viewModel.getAllMovies().observe(viewLifecycleOwner) {
                adapter.submitData(lifecycle, it)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel.getMovies(1)

        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading)
                showLoadingScreen()
            else {
                hideLoadingScreen()
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }

                errorState?.let {
                    Toast.makeText(requireActivity(), it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}