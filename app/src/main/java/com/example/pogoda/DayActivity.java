package com.example.pogoda;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class DayActivity extends AppCompatActivity implements View.OnClickListener {
    LineChart lineChart;
    ImageButton ibTemperature,ibWind,ibHumidity;
    LineDataSet lineDataSet;
    List<Entry> lineEntries;
    LineData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        ibTemperature = findViewById(R.id.ibTemperature);
        ibWind = findViewById(R.id.ibWind);
        ibHumidity = findViewById(R.id.ibHumidity);
        lineChart = findViewById(R.id.chart);

        lineEntries = getDataSetTemperute();
        lineDataSet = new LineDataSet(lineEntries,"Temperatura");
        lineDataSet.setColors(Color.RED);
        data = new LineData(lineDataSet);
            lineChart.setData(data);
            lineChart.animateXY(200,200);
            lineChart.invalidate();

        ibTemperature.setOnClickListener(this);
        ibWind.setOnClickListener(this);
        ibHumidity.setOnClickListener(this);
    }
    private List<Entry> getDataSetTemperute() {
        List<Entry> lineEntries = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            lineEntries.add(new Entry(i, -5+i));
        }
        return lineEntries;
    }
    private List<Entry> getDataSetWind() {
        List<Entry> lineEntries = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            lineEntries.add(new Entry(i, i*2));
        }
        return lineEntries;
    }
    private List<Entry> getDataSetHumidity() {
        List<Entry> lineEntries = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            lineEntries.add(new Entry(i, 10-i));
        }
        return lineEntries;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ibTemperature){
            lineEntries = getDataSetTemperute();
            lineDataSet = new LineDataSet(lineEntries,"Temperatura");
        }else if(view.getId() == R.id.ibWind){
            lineEntries = getDataSetWind();
            lineDataSet = new LineDataSet(lineEntries,"Wiatr");
        }else if(view.getId() == R.id.ibHumidity){
            lineEntries = getDataSetHumidity();
            lineDataSet = new LineDataSet(lineEntries,"Wilgotność");
        }
        data = new LineData(lineDataSet);
        lineChart.setData(data);
        lineChart.invalidate();
    }
}