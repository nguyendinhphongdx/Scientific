package com.example.scientificresearch.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scientificresearch.Adapter.CourseAdapter;
import com.example.scientificresearch.Adapter.NotiAdapter;
import com.example.scientificresearch.Model.Course;
import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class HomeFragment extends Fragment {
    BarChart barChart;
    ProgressBar progressBar;
    RecyclerView recycleCourse;
    private  ArrayList<Course> courses = Store.getCourse();
    CourseAdapter adapter;
    private List<String> appNameList = new LinkedList<String>();
    public HomeFragment() {
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_home, container, false);
        setViews(view);
        setUp();
        initData();
        setListeners();
        return view;
    }

    private void setViews(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        barChart =view.findViewById(R.id.chart);
        recycleCourse = view.findViewById(R.id.recyclerCourse);

    }
    private void setUp() {
        progressBar.setVisibility(View.GONE);
        barChart.setNoDataText(getResources().getString(R.string.no_data));
        adapter = new CourseAdapter(courses,getActivity());
        recycleCourse.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleCourse.setLayoutManager(layoutManager);
    }
    private void setListeners() {
    }
    private void initData() {
        initChart();
        initRecycleCourse();
    }

    private void initRecycleCourse() {

    }

    private void initChart() {
        appNameList.add("Dcm");
        appNameList.add("Dcm");
        appNameList.add("Dcm");
        appNameList.add("Dcm");
        ArrayList<Float> appList = new ArrayList<Float>();
        appList.add(0, (float) 7.8);
        appList.add(1, (float) 8.0);
        appList.add(2, (float) 7.6);
        appList.add(3, (float) 9.5);
        setBarChart(appList);
    }


    private void setBarChart(ArrayList<Float> value) {
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
//        barChart.setMaxVisibleValueCount(60);
        barChart.setVisibleXRangeMaximum(6);
        barChart.moveViewToX(10);
        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);

        barChart.setDrawGridBackground(false);
        // barChart.setDrawYLabels(false)
        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(barChart,appNameList);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
//        xAxis.setLabelCount(appNameList.size());
        xAxis.setValueFormatter(xAxisFormatter);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setLabelCount(10, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);

        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(15f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        setData(value);
    }

    private void setData(ArrayList<Float> values) {
        ArrayList<String> course= new ArrayList<>();
        course.add(0,"HTML");
        course.add(1,"CSS");
        course.add(2,"JAVASCRIPT");
        course.add(3,"NODEJS");
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < values.size(); i++) {
            float val = values.get(i);
            yVals1.add(new BarEntry(i, val));
        }
        BarDataSet set1 = new BarDataSet(yVals1,"LABEL");

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1.setDrawIcons(false);
            set1.setColors(ColorTemplate.COLORFUL_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.5f);
            barChart.setData(data);
            barChart.setVisibleXRangeMaximum(4.0f);
        }
    }

}
