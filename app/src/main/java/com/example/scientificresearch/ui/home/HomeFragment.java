package com.example.scientificresearch.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scientificresearch.Adapter.CourseAdapter;
import com.example.scientificresearch.Model.Store;
import com.example.scientificresearch.Model.Subject.ResponseModelSubject;
import com.example.scientificresearch.Model.Subject.Subject;
import com.example.scientificresearch.R;
import com.example.scientificresearch.Server.ApiService.StudentService;
import com.example.scientificresearch.utils.ConvertData;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    BarChart barChart;
    ProgressBar progressBar;
    RecyclerView recycleCourse;
    TextView txtTotalPrice;
    TextView txtTotalCourse;
    TextView txtAvg;
    TextView txtHoTen_TongQuan;
    private  List<Subject> subjects= new ArrayList<>();
    CourseAdapter adapter;
    ArrayList<Float> appList = new ArrayList<Float>();
    private String _id = Store.getCurentUser().getID();
    public HomeFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_home, container, false);
        callApi(view);
        return view;
    }

    private void callApi(View view) {
        getAllSubject(view);
    }

    private void getAllSubject(View view) {
        StudentService.studentService.getAllSubject(_id).enqueue(new Callback<ResponseModelSubject>() {
            @Override
            public void onResponse(Call<ResponseModelSubject> call, Response<ResponseModelSubject> response) {
                if(response.isSuccessful()){
                    List<Subject> listSubject = response.body().getData();
                    Store.setSubject(listSubject);
                    subjects = listSubject;
                    setViews(view);
                    setUp();
                    initData();
                    setListeners();
                    Log.d("HOME >>>", "onResponse:  "+response.message());
                }else{
                    Log.d("HOME >>>", "onResponse:  error");
                }
            }

            @Override
            public void onFailure(Call<ResponseModelSubject> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setViews(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        barChart =view.findViewById(R.id.chart);
        recycleCourse = view.findViewById(R.id.recyclerCourse);
        txtTotalPrice = view.findViewById(R.id.txtTotalPrice);
        txtTotalCourse = view.findViewById(R.id.txtTotalCourse);
        txtAvg = view.findViewById(R.id.txtAvg);
        txtHoTen_TongQuan = view.findViewById(R.id.txtHoTen_TongQuan);
    }
    private void setUp() {
        progressBar.setVisibility(View.GONE);
        barChart.setNoDataText(getResources().getString(R.string.no_data));
        adapter = new CourseAdapter(subjects,getActivity());
        recycleCourse.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleCourse.setLayoutManager(layoutManager);
    }
    private void setListeners() {
    }
    private void initData() {
        initChart();
        initRecycleCourse();
        txtTotalPrice.setText(ConvertData.totalPrice(subjects)+"");
        txtTotalCourse.setText(subjects.size()+"");
        txtAvg.setText(ConvertData.avgMark(subjects)+"");
        txtHoTen_TongQuan.setText(Store.getCurentUser().getName());
    }

    private void initRecycleCourse() {

    }

    private void initChart() {
        appList.add(0, (float) 7.8);
        appList.add(1, (float) 8.0);
        appList.add(2, (float) 7.6);
        appList.add(3, (float) 9.5);
        setBarChart(subjects);
    }


    private void setBarChart(List<Subject> value) {
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
        final String xVal[]={"Val1","Val2","Val3"};
//        ValueFormatter xAxisFormatter = new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                return super.getFormattedValue(value);
//            }
//        }
        XAxis xAxis = barChart.getXAxis();
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVal));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
//        xAxis.setLabelCount(appNameList.size());
//        xAxis.setValueFormatter(xAxisFormatter);
        List<String> appNameList = Store.getApplist();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(appNameList));


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

    private void setData(List<Subject> values) {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < values.size(); i++) {
            float val = Float.parseFloat(values.get(i).getMark());
            yVals1.add(new BarEntry(i+1, val));
        }
        BarDataSet set1 = new BarDataSet(yVals1,"LABEL");
        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
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
