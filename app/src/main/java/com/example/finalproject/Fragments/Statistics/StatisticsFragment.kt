package com.example.finalproject.Fragments.Statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import android.R
import com.anychart.AnyChartView
import android.R.attr.data
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.AnyChart
import com.anychart.charts.Pie
import kotlinx.android.synthetic.main.statistics_fragment.*
import java.util.ArrayList


@Suppress("UNREACHABLE_CODE")
class StatisticsFragment : Fragment() {

    companion object {
        fun newInstance() = StatisticsFragment()
    }

    private lateinit var viewModel: StatisticsViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(com.example.finalproject.R.string.statistics)
        return inflater.inflate(com.example.finalproject.R.layout.statistics_fragment, container, false)




    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StatisticsViewModel::class.java)

        val pie = AnyChart.pie()

        val data = ArrayList<DataEntry>()
        data.add(ValueDataEntry("January", 10000))
        data.add(ValueDataEntry("February", 10000))
        data.add(ValueDataEntry("March", 10000))
        data.add(ValueDataEntry("April", 10000))
        data.add(ValueDataEntry("May", 20000))
        pie.data(data)
        any_chart_view.setChart(pie)

    }


}
