package com.example.pogoda;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GminaActivity extends AppCompatActivity {

    public static final String KEY_DAY = "data";

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
        JSONObject hourly;


        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + getIntent().getDoubleExtra("Latitude", 0) + "&longitude="+ getIntent().getDoubleExtra("Longitude", 0)+"&current=is_day,rain,showers,weather_code&hourly=temperature_2m,rain,weather_code,wind_speed_10m&timezone=auto&forecast_days=7";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Wybor obrazka z tylu
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

                    // ustawienie nazwy gminy
                    tvTitle.setText(getIntent().getStringExtra("Name"));

                    JSONObject hourly = response.getJSONObject("hourly");
                    JSONArray temperature_2m = hourly.getJSONArray("temperature_2m");
                    JSONArray weather_code = hourly.getJSONArray("weather_code");
                    JSONArray wind_speed_10m = hourly.getJSONArray("wind_speed_10m");
                    JSONArray rain = hourly.getJSONArray("rain");
                    for (int i = 0; i < 7; i++) {
                        days.get(i).setTempDay((temperature_2m.getDouble(8 + (24 * i)) + temperature_2m.getDouble(10 + (i * 24)) + temperature_2m.getDouble(12 + (24 * i)) + temperature_2m.getDouble(14 + (24 * i)) + temperature_2m.getDouble(16 + (24 * i))) / 5);
                        days.get(i).setTempNight((temperature_2m.getDouble(20 + (24 * i)) + temperature_2m.getDouble(21 + (i * 24)) + temperature_2m.getDouble(23 + (24 * i)) + temperature_2m.getDouble(2 + (24 * i)) + temperature_2m.getDouble(3 + (24 * i)) ) / 5);

                        switch ((Integer) weather_code.get(12 + (24 * i))) {
                            case 0:
                            case 1:
                            case 2: days.get(i).setImageDay(R.drawable.icons8sun100); break;
                            case 3: days.get(i).setImageDay(R.drawable.icons8cloud100); break;
                            case 45:
                            case 48: days.get(i).setImageDay(R.drawable.icons8fog100); break;
                            case 51:
                            case 53:
                            case 55:
                            case 61:
                            case 63:
                            case 65:
                                days.get(i).setImageDay(R.drawable.icons8rain100); break;
                            case 56:
                            case 57:
                            case 66:
                            case 67:
                                days.get(i).setImageDay(R.drawable.icons8sleet100); break;
                            case 71:
                            case 73:
                            case 75:
                            case 77: days.get(i).setImageDay(R.drawable.icons8snow100); break;
                            case 85:
                            case 86: days.get(i).setImageDay(R.drawable.icons8snowstorm100); break;
                            case 80:
                            case 81:
                            case 82: days.get(i).setImageDay(R.drawable.icons8raincloud100); break;
                            case 95:
                            case 96:
                            case 99: days.get(i).setImageDay(R.drawable.icons8cloudlightning100); break;
                            default: break;
                        }
//                        Log.d("test123", "godzina 20: " + temperature_2m.getDouble(20 + (24 * i)) + "\ngodzina 21: " + temperature_2m.getDouble(21 + (24 * i)) + "\ngodzina 23: " + temperature_2m.getDouble(23 + (24 * i)) + "\ngodzina 2: " + temperature_2m.getDouble(2 + (24 * i)) + "\ngodzina 3: " + temperature_2m.getDouble(3 + (24 * i)));
                    }



                    DaysAdapter adapter = new DaysAdapter(days);
                    recyclerView.setAdapter(adapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GminaActivity.this, LinearLayoutManager.HORIZONTAL, false);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(GminaActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(GminaActivity.this,DayActivity.class);
                            intent.putExtra("position", position);
                            intent.putExtra("temperature", temperature_2m.toString());
                            intent.putExtra("rain", rain.toString());
                            intent.putExtra("wind_speed_10m", wind_speed_10m.toString());
                            intent.putExtra("weather_code", weather_code.toString());
                            startActivity(intent);
                        }
                        @Override
                        public void onLongItemClick(View view, int position) {
                            Intent intent = new Intent(GminaActivity.this,DayActivity.class);
                            intent.putExtra("temperature", temperature_2m.toString());
                            intent.putExtra("rain", rain.toString());
                            intent.putExtra("wind_speed_10m", wind_speed_10m.toString());
                            intent.putExtra("weather_code", weather_code.toString());
                            startActivity(intent);
                        }
                    }));
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