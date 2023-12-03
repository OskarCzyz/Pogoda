package com.example.pogoda;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder>{
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView temperatureDay,temperatureNight,tvDay;
        public ImageView humidityDay,humidityNight;

        public ViewHolder(View itemView){
            super(itemView);
            temperatureDay = itemView.findViewById(R.id.temperatureDay);
            temperatureNight = itemView.findViewById(R.id.temperatureNight);
            humidityDay = itemView.findViewById(R.id.humidityDay);
            humidityNight = itemView.findViewById(R.id.humidityNight);
            tvDay = itemView.findViewById(R.id.tvDay);
        }

    }
    private List<Day> mDays;
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
        temperatureDay.setText((float)Math.round(day.getTempDay() * 10) / 10 + "°C");
        TextView temperatureNight = holder.temperatureNight;
        temperatureNight.setText((float)Math.round(day.getTempNight() * 10) / 10 + "°C");
        ImageView humidityDay = holder.humidityDay;
        humidityDay.setImageResource(day.getImageDay());
        ImageView humidityNight = holder.humidityNight;
        humidityNight.setImageResource(day.getImageNight());
        TextView tvDay = holder.tvDay;
        tvDay.setText(day.getDay());
    }

    @Override
    public int getItemCount() {
        return mDays.size();
    }
}
