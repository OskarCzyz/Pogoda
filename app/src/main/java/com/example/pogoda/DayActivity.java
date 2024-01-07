package com.example.pogoda;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

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
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        ibTemperature = findViewById(R.id.ibTemperature);
        ibWind = findViewById(R.id.ibWind);
        ibHumidity = findViewById(R.id.ibHumidity);
        lineChart = findViewById(R.id.chart);
        TextView tvDate = findViewById(R.id.tvDate);

        String time = "";
        if (getIntent().hasExtra("time")) time = getIntent().getStringExtra("time");
        int position = getIntent().getIntExtra("position", 0);
        assert time != null;
        String[] time_array = time.substring(1, time.length() - 1).split(",");
        tvDate.setText(time_array[position].substring(1,time_array[position].length()-1));


        lineEntries = getDataSetTemperute();
        lineDataSet = new LineDataSet(lineEntries,"Temperatura");
        lineDataSet.setColors(Color.RED);
        lineDataSet.setCircleColors(Color.GREEN);
        data = new LineData(lineDataSet);
            lineChart.setData(data);
            lineChart.animateXY(200,200);
            lineChart.invalidate();
            lineDataSet.setValueTextSize(10);
            lineChart.setDescription(null);


        ibTemperature.setOnClickListener(this);
        ibWind.setOnClickListener(this);
        ibHumidity.setOnClickListener(this);
    }
    private List<Entry> getDataSetTemperute() {
        List<Entry> lineEntries = new ArrayList<>();
        String temperature_2m = "";
        if (getIntent().hasExtra("temperature")) temperature_2m = getIntent().getStringExtra("temperature");
        int position = getIntent().getIntExtra("position", 0);
        assert temperature_2m != null;
        String[] temperature = temperature_2m.substring(1, temperature_2m.length() - 1).split(",");
        for (int i = 0; i < 24; i+=2) {
            lineEntries.add(new Entry(i, Float.parseFloat(temperature[i + (position * 24)])));
        }
        return lineEntries;
    }
    private List<Entry> getDataSetWind() {
        List<Entry> lineEntries = new ArrayList<>();
        String wind_speed_10m = "";
        if (getIntent().hasExtra("wind_speed_10m")) wind_speed_10m = getIntent().getStringExtra("wind_speed_10m");
        int position = getIntent().getIntExtra("position", 0);
        assert wind_speed_10m != null;
        String[] wind = wind_speed_10m.substring(1, wind_speed_10m.length() - 1).split(",");
        for (int i = 0; i < 24; i+=2) {
            lineEntries.add(new Entry(i, Float.parseFloat(wind[i + (position * 24)])));
        }
        return lineEntries;
    }
    private List<Entry> getDataSetHumidity() {
        List<Entry> lineEntries = new ArrayList<>();
        String rain = "";
        if (getIntent().hasExtra("rain")) rain = getIntent().getStringExtra("rain");
        int position = getIntent().getIntExtra("position", 0);
        assert rain != null;
        String[] rain2 = rain.substring(1, rain.length() - 1).split(",");
        for (int i = 0; i < 24; i+=2) {
            lineEntries.add(new Entry(i, Float.parseFloat(rain2[i + (position * 24)])));
        }
        return lineEntries;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ibTemperature){
            lineEntries = getDataSetTemperute();
            lineDataSet = new LineDataSet(lineEntries,"Temperatura");
            lineDataSet.setColors(Color.RED);
        }else if(view.getId() == R.id.ibWind){
            lineEntries = getDataSetWind();
            lineDataSet = new LineDataSet(lineEntries,"Wiatr");
            lineDataSet.setColors(Color.YELLOW);
        }else if(view.getId() == R.id.ibHumidity){
            lineEntries = getDataSetHumidity();
            lineDataSet = new LineDataSet(lineEntries,"Wilgotność");
            lineDataSet.setColors(Color.BLUE);
        }
        data = new LineData(lineDataSet);
        lineChart.setData(data);
        lineDataSet.setCircleColors(Color.GREEN);
        lineDataSet.setValueTextSize(10);
        lineChart.invalidate();
    }
}