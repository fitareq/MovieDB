package com.fitareq.moviedb.ui.graph

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fitareq.moviedb.R
import com.fitareq.moviedb.common.BaseFragment
import com.fitareq.moviedb.databinding.FragmentGraphBinding
import com.fitareq.moviedb.graph.DataPoint
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
        binding.graph.setData(dataPoint)
        return binding.root
    }
    private val dataPoint = ArrayList<DataPoint>()
    private fun getDataFromCsv() {
        val inputStream = resources.openRawResource(R.raw.stock)
        val reader = inputStream.bufferedReader()

//        val timeStamps = mutableListOf<Long>()
//        val closes = mutableListOf<Float>()

        reader.useLines { lines ->
            lines.forEach { line ->
                val values = line.split(",")
                if (values.size > 5){
                    dataPoint.add(DataPoint( values[4].toFloat(), convertTimestampToMillis(values[0])))
                    //timeStamps.add(convertTimestampToMillis(values[0]))
                    //loses.add(values[4].toFloat())
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