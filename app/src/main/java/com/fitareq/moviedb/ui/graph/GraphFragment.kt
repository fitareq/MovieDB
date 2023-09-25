package com.fitareq.moviedb.ui.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fitareq.moviedb.R
import com.fitareq.moviedb.common.BaseFragment
import com.fitareq.moviedb.databinding.FragmentGraphBinding
import com.fitareq.moviedb.ui.MainActivity
import java.text.SimpleDateFormat
import java.util.Locale

class GraphFragment : BaseFragment() {
    private lateinit var binding: FragmentGraphBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGraphBinding.inflate(inflater, container, false)
        if (activity is MainActivity) {
            (activity as MainActivity).setToolbarTitle("Graph")
        }

        getDataFromCsv()
        binding.graph.setData(data)
        return binding.root
    }

    private val data = ArrayList<Pair<Long, Float>>()
    private fun getDataFromCsv() {
        val inputStream = resources.openRawResource(R.raw.stock)
        val reader = inputStream.bufferedReader()

        reader.useLines { lines ->
            lines.forEach { line ->
                val values = line.split(",")
                if (values.size > 5) {
                    data.add(Pair(convertTimestampToMillis(values[0]), values[4].toFloat()))

                }
            }
        }
    }


    private fun convertTimestampToMillis(timestamp: String): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = dateFormat.parse(timestamp)
        return date?.time ?: 0L
    }

}