package com.example.pogoda;

import java.util.ArrayList;

public class Day {
    private int tempDay,tempNight;
    private int imageDay,imageNight;
    public Day(int tDay, int tnight, int iDay, int iNight){
        tempDay = tDay;
        tempNight = tnight;
        imageDay = iDay;
        imageNight = iNight;
    }

    public int getTempDay() {
        return tempDay;
    }

    public int getTempNight() {
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
            days.add(new Day(10,0,1,3));
        }
        return days;
    }

}
