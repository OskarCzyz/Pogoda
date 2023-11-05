package com.example.pogoda;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GminaActivity extends AppCompatActivity {

    private TextView tvTitle;

    private ImageView ivStatus;

    private ListView listView;

    private List<String> temperatures = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
// Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmina);

        tvTitle = findViewById(R.id.tvTitle);
        ivStatus = findViewById(R.id.ivStatus);
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(GminaActivity.this, android.R.layout.simple_list_item_1, temperatures);
        listView.setAdapter(adapter);


        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + getIntent().getDoubleExtra("Latitude", 0) + "&longitude="+ getIntent().getDoubleExtra("Longitude", 0)+"&current=is_day,rain,showers,weather_code&hourly=temperature_2m&timezone=auto&forecast_days=1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject current = response.getJSONObject("current");
                    switch (current.getInt("weather_code")) {
                        case 0:
                        case 1: ivStatus.setImageResource(R.drawable.clear_status); break;
                        case 2:
                        case 3: ivStatus.setImageResource(R.drawable.cloudy_status); break;
                        case 45:
                        case 48: ivStatus.setImageResource(R.drawable.foggy_status); break;
                        case 51:
                        case 53:
                        case 55:
                        case 56:
                        case 57:
                        case 61:
                        case 63:
                        case 65:
                        case 66:
                        case 67: ivStatus.setImageResource(R.drawable.rain_status); break;
                        case 71:
                        case 73:
                        case 75:
                        case 85:
                        case 86:
                        case 77: ivStatus.setImageResource(R.drawable.snow_status); break;
                        case 80:
                        case 81:
                        case 82: ivStatus.setImageResource(R.drawable.rainshowers_status); break;
                        case 95:
                        case 96:
                        case 99: ivStatus.setImageResource(R.drawable.thunderstorm_status); break;
                        default: break;
                    }

                    tvTitle.setText(getIntent().getStringExtra("Name"));

                    JSONObject hourly = response.getJSONObject("hourly");
                    for (int i =0; i < 24; i++) {
                        temperatures.add(hourly.getJSONArray("time").getString(i) + " " + hourly.getJSONArray("temperature_2m").getString(i));
                    }
                    adapter.notifyDataSetChanged();


                } catch (JSONException e ) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvTitle.setText("error ");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}