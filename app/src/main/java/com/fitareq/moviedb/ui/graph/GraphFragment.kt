package com.fitareq.moviedb.ui.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fitareq.moviedb.R
import com.fitareq.moviedb.common.BaseFragment
import com.fitareq.moviedb.ui.MainActivity

class GraphFragment : BaseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (activity is MainActivity){
            (activity as MainActivity).setToolbarTitle("Graph")
        }
        return inflater.inflate(R.layout.fragment_graph, container, false)
    }
}