package com.example.pogoda;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder>{
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView temperatureDay,temperatureNight,tvDay;
        public ImageView humidityDay;

        public ViewHolder(View itemView){
            super(itemView);
            temperatureDay = itemView.findViewById(R.id.temperatureDay);
            temperatureNight = itemView.findViewById(R.id.temperatureNight);
            humidityDay = itemView.findViewById(R.id.humidityDay);
            tvDay = itemView.findViewById(R.id.tvDay);
        }

    }
    private final List<Day> mDays;
    public DaysAdapter(List<Day> days){
        mDays = days;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View dayView = inflater.inflate(R.layout.dzien,parent,false);
        return new ViewHolder(dayView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Day day = mDays.get(position);
        TextView temperatureDay = holder.temperatureDay;
        String unit;
        Log.d("test123", "onBindViewHolder: " + MainActivity.currentUnit);
        if (Objects.equals(MainActivity.currentUnit, "fahrenheit")) unit = "°F";
        else unit = "°C";
        // temperatura dzien
        temperatureDay.setText((float)Math.round(day.getTempDay() * 10) / 10 + unit);
        TextView temperatureNight = holder.temperatureNight;
        // temperatura noc
        temperatureNight.setText((float)Math.round(day.getTempNight() * 10) / 10 + unit);
        // obrazek dzien
        ImageView humidityDay = holder.humidityDay;
        humidityDay.setImageResource(day.getImageDay());
        // dzien
        TextView tvDay = holder.tvDay;
        tvDay.setText(day.getDay());
    }

    @Override
    public int getItemCount() {
        return mDays.size();
    }
}
