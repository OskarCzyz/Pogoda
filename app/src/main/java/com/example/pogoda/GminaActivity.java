package com.example.pogoda;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GminaActivity extends AppCompatActivity {

    private TextView tvTitle;

    private ImageView ivStatus;

    private RecyclerView recyclerView;

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
        recyclerView = findViewById(R.id.recyclerView);
        ArrayList<Day> days = Day.createDayList(7);
        DaysAdapter adapter = new DaysAdapter(days);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GminaActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(GminaActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(GminaActivity.this,DayActivity.class);
                startActivity(intent);
            }
            @Override
            public void onLongItemClick(View view, int position) {
                Intent intent = new Intent(GminaActivity.this,DayActivity.class);
                startActivity(intent);
            }
        }));
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + getIntent().getDoubleExtra("Latitude", 0) + "&longitude="+ getIntent().getDoubleExtra("Longitude", 0)+"&current=is_day,rain,showers,weather_code&hourly=temperature_2m&timezone=auto&forecast_days=7";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject current = response.getJSONObject("current");
                    switch (current.getInt("weather_code")) {
                        case 0: ivStatus.setImageResource(R.drawable.status_fullclear); break;
                        case 1: ivStatus.setImageResource(R.drawable.status_clear); break;
                        case 2:
                        case 3: ivStatus.setImageResource(R.drawable.status_cloudy); break;
                        case 45:
                        case 48: ivStatus.setImageResource(R.drawable.status_foggy); break;
                        case 51:
                        case 53:
                        case 55:
                        case 56:
                        case 57:
                        case 61:
                        case 63:
                        case 65:
                        case 66:
                        case 67: ivStatus.setImageResource(R.drawable.status_rain); break;
                        case 71:
                        case 73:
                        case 75:
                        case 85:
                        case 86:
                        case 77: ivStatus.setImageResource(R.drawable.status_snow); break;
                        case 80:
                        case 81:
                        case 82: ivStatus.setImageResource(R.drawable.status_rainshowers); break;
                        case 95:
                        case 96:
                        case 99: ivStatus.setImageResource(R.drawable.status_thunderstorm); break;
                        default: break;
                    }

                    tvTitle.setText(getIntent().getStringExtra("Name"));

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