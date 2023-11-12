package com.example.pogoda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder>{
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView temperatureDay,temperatureNight;
        public ImageView humidityDay,humidityNight;

        public ViewHolder(View itemView){
            super(itemView);
            temperatureDay = itemView.findViewById(R.id.temperatureDay);
            temperatureNight = itemView.findViewById(R.id.temperatureNight);
            humidityDay = itemView.findViewById(R.id.humidityDay);
            humidityNight = itemView.findViewById(R.id.humidityNight);
        }

    }
    private List<Day> mDays;
    public DaysAdapter(List<Day> days){
        mDays = days;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View dayView = inflater.inflate(R.layout.dzien,parent,false);
        ViewHolder viewHolder = new ViewHolder(dayView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Day day = mDays.get(position);
        TextView temperatureDay = holder.temperatureDay;
        temperatureDay.setText(Integer.toString(day.getTempDay()) + "°C");
        TextView temperatureNight = holder.temperatureNight;
        temperatureNight.setText(Integer.toString(day.getTempNight())+"°C");
        ImageView humidityDay = holder.humidityDay;
        humidityDay.setImageResource(R.drawable.icons8cloudlightning100);
        ImageView humidityNight = holder.humidityNight;
        humidityNight.setImageResource(R.drawable.icons8cloud100);

    }

    @Override
    public int getItemCount() {
        return mDays.size();
    }
}
