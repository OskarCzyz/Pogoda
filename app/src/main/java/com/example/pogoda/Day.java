package com.example.pogoda;

import java.util.ArrayList;

public class Day {
    private double tempDay,tempNight;
    private int imageDay,imageNight;
    private String day;

    public void setDay(String day) {
        this.day = day;
    }

    public void setTempDay(double tempDay) {
        this.tempDay = tempDay;
    }

    public void setTempNight(double tempNight) {
        this.tempNight = tempNight;
    }

    public void setImageDay(int imageDay) {
        this.imageDay = imageDay;
    }

    public void setImageNight(int imageNight) {
        this.imageNight = imageNight;
    }

    public Day(int tDay, int tnight, int iDay, int iNight,String date){
        tempDay = tDay;
        tempNight = tnight;
        imageDay = iDay;
        imageNight = iNight;
        day = date;
    }

    public String getDay() {
        return day;
    }

    public double getTempDay() {
        return tempDay;
    }

    public double getTempNight() {
        return tempNight;
    }

    public int getImageDay() {
        return imageDay;
    }

    public int getImageNight() {
        return imageNight;
    }

    public static ArrayList<Day> createDayList(int numDays){
        ArrayList<Day> days = new ArrayList<Day>();

        for (int i = 1; i <= numDays; i++){
            days.add(new Day(10,0,R.drawable.icons8cloud100,R.drawable.icons8cloud100,"Dziś"));
        }
        return days;
    }

}
