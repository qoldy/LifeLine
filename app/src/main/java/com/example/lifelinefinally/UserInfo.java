package com.example.lifelinefinally;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UserInfo {
    public String surname, name, patronymic;
    public Calendar birthdate;
    public boolean male = true;
    public ArrayList<Pulse> pulseArrayList;
    public ArrayList<Pressure> pressureArrayList;
    public ArrayList<Temperature> temperatureArrayList;
    public ArrayList<Sleep> sleepArrayList;
    public int max_pulse=0, min_pulse=500;
    public int max_systolic=0, min_diastolic=500;
    public double max_temperature=0, min_temperature=500;
    public double max_sleep = 0;
    public UserInfo(){
        surname = "Иванов";
        name = "Иван";
        patronymic = "Иванович";
        birthdate = new GregorianCalendar();
        birthdate.set(2000, 1, 1);
        pulseArrayList = new ArrayList<>();
        pressureArrayList = new ArrayList<>();
        temperatureArrayList = new ArrayList<>();
        sleepArrayList = new ArrayList<>();
    }
}
