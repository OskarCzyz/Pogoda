package com.example.pogoda;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONObject;

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
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + getIntent().getDoubleExtra("Latitude", 0) + "&longitude="+ getIntent().getDoubleExtra("Longitude", 0)+"&current=is_day,rain,showers,weather_code&hourly=temperature_2m&timezone=auto&forecast_days=7";
    }
    private List<Entry> getDataSetTemperute() {
        List<Entry> lineEntries = new ArrayList<>();
        String temperature_2m = "";
        if (getIntent().hasExtra("temperature")) temperature_2m = getIntent().getStringExtra("temperature");
        Integer position = getIntent().getIntExtra("position", 0);
        String[] temperature = temperature_2m.substring(1, temperature_2m.length() - 1).split(",");
        for (int i = 0; i < 24; i++) {
            lineEntries.add(new Entry(i, Float.parseFloat(temperature[i + (position * 24)])));
        }
        return lineEntries;
    }
    private List<Entry> getDataSetWind() {
        List<Entry> lineEntries = new ArrayList<>();
        String wind_speed_10m = "";
        if (getIntent().hasExtra("wind_speed_10m")) wind_speed_10m = getIntent().getStringExtra("wind_speed_10m");
        Integer position = getIntent().getIntExtra("position", 0);
        String[] wind = wind_speed_10m.substring(1, wind_speed_10m.length() - 1).split(",");
        for (int i = 0; i < 24; i++) {
            lineEntries.add(new Entry(i, Float.parseFloat(wind[i + (position * 24)])));
        }
        return lineEntries;
    }
    private List<Entry> getDataSetHumidity() {
        List<Entry> lineEntries = new ArrayList<>();
        String rain = "";
        if (getIntent().hasExtra("rain")) rain = getIntent().getStringExtra("rain");
        Integer position = getIntent().getIntExtra("position", 0);
        String[] rain2 = rain.substring(1, rain.length() - 1).split(",");
        for (int i = 0; i < 24; i++) {
            lineEntries.add(new Entry(i, Float.parseFloat(rain2[i + (position * 24)])));
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