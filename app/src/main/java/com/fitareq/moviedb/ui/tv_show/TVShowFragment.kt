package com.fitareq.moviedb.ui.tv_show

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.fitareq.moviedb.Utils
import com.fitareq.moviedb.common.BaseFragment
import com.fitareq.moviedb.databinding.FragmentTVShowBinding
import com.fitareq.moviedb.ui.MainActivity
import com.fitareq.moviedb.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TVShowFragment : BaseFragment() {
    private lateinit var binding: FragmentTVShowBinding
    private val viewModel: TVViewModel by viewModels()
    private lateinit var adapter: TVPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTVShowBinding.inflate(inflater, container, false)
        adapter = TVPagerAdapter { id ->
            startActivity(
                Intent(requireActivity(), DetailsActivity::class.java)
                    .putExtra(Utils.KEY_ID, id)
                    .putExtra(Utils.KEY_IS_MOVIE, false)
            )
        }
        binding.tvList.adapter = adapter

        lifecycleScope.launch {
            viewModel.getAllTvShow().observe(viewLifecycleOwner) {
                adapter.submitData(lifecycle, it)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is MainActivity) {
            (activity as MainActivity).setToolbarTitle("Tv Shows")
        }
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