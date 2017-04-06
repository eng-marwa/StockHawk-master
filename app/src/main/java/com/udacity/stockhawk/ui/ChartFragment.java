package com.udacity.stockhawk.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.model.Quote;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Query;
import timber.log.Timber;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    @BindView(R.id.lineChartGraph)
    LineChart lineChartGraph;

    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_chart, container, false);
        ButterKnife.bind(this,v);

        return v;
    }

    public static ChartFragment getInstance(Quote quote) {

        ChartFragment chartFragment = new ChartFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("quote", quote);
        chartFragment.setArguments(bundle);
        return chartFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        List<String> xAxis = new ArrayList<>();
        List<Entry> valueSet = new ArrayList<>();

        if (bundle != null) {
            Quote quote = bundle.getParcelable("quote");
            if (quote != null) {
                String history = quote.getHistory();
                String[] line = history.split("\n");
                Log.i("eee",line.length+"");
                for(int i=0;i<line.length;i++){
                    String[] strings = line[i].split(", ");
                        xAxis.add(getDateFromString(strings[0]));
                        valueSet.add(new Entry(Float.parseFloat(strings[1]),i+=3));

                }

                }
            List<ILineDataSet> dataSet = getDataSet(valueSet, quote.getSymbol());
            LineData data = new LineData(xAxis,dataSet);
                setUpChart(lineChartGraph, data);
            }

        }

    private String getDateFromString(String dateString) {
        Date d = new Date(Long.parseLong(dateString));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(d);
    }


    private void setUpChart(LineChart lineChartGraph, LineData data) {
        XAxis xAxis = lineChartGraph.getXAxis();
        xAxis.setLabelsToSkip(5);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(12f);
        xAxis.setTextColor(R.color.colorPrimaryDark);

        YAxis left = lineChartGraph.getAxisLeft();
        left.setEnabled(true);
        left.setLabelCount(10, true);
        left.setTextColor(Color.rgb(182,182,182));

        lineChartGraph.getAxisRight().setEnabled(false);
        lineChartGraph.getLegend().setTextSize(16f);
        lineChartGraph.setDrawGridBackground(true);
        lineChartGraph.setGridBackgroundColor(Color.rgb(244,244,244));

        lineChartGraph.setDescriptionColor(R.color.colorPrimaryDark);

        lineChartGraph.animateX(2500);
        lineChartGraph.setData(data);


    }

    private List<ILineDataSet> getDataSet(List<Entry> valueSet,String symbol) {
        List<ILineDataSet> dataSets = null;


        LineDataSet lineDataSet = new LineDataSet(valueSet, symbol);
        lineDataSet.setColor(Color.rgb(0, 133, 202));

        dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        return dataSets;
    }
}
