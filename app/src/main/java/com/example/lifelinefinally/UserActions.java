package com.example.lifelinefinally;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

public class UserActions {
    private UserInfo userInfo;
    private Context context;
    public String currentSection="diary";
    public boolean isReport = false;

    public UserActions(UserInfo info){
        userInfo = info;
    }

    public void addPulse(Pulse pulse){
        userInfo.pulseArrayList.add(pulse);
    }
    public ArrayList<Pulse> getPulseArrayList(){
        return userInfo.pulseArrayList;
    }

    public void addPressure(Pressure pressure) {userInfo.pressureArrayList.add(pressure);}
    public ArrayList<Pressure> getPressureArrayList(){return userInfo.pressureArrayList;}

    public void addTemperature(Temperature temperature){userInfo.temperatureArrayList.add(temperature);}
    public ArrayList<Temperature> getTemperatureArrayList(){return userInfo.temperatureArrayList;}

    public void addSleep(Sleep sleep){
        if(userInfo.sleepArrayList.size()!=0){
            Date date = new Date();
            Sleep lastSleep = userInfo.sleepArrayList.get(userInfo.sleepArrayList.size()-1);
            if(date.getYear()==lastSleep.getDate().getYear()&&date.getMonth()==lastSleep.getDate().
                    getMonth()&&date.getDay()==lastSleep.getDate().getDay()){
                userInfo.sleepArrayList.get(userInfo.sleepArrayList.size()-1).
                        setSleep(sleep.getHours(), sleep.getMinutes());
                return;
            }
        }
        userInfo.sleepArrayList.add(sleep);
    }
    public ArrayList<Sleep> getSleepArrayList(){return userInfo.sleepArrayList;}


    public void setContext(Context c){context = c;}
    public LineGraphSeries<DataPoint> getPulseLineSeries(){
        ArrayList<Pulse> pulseArrayList = userInfo.pulseArrayList;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        if(pulseArrayList.size()!=0) {
            for (int i = 0; i < pulseArrayList.size(); i++) {
                Collections.sort(pulseArrayList, new Comparator<Pulse>() {
                    public int compare(Pulse a, Pulse b) {
                        return a.getDate().compareTo(b.getDate());
                    }
                });
            }
            for (int i = 0; i < pulseArrayList.size(); i++) {
                series.appendData(new DataPoint(pulseArrayList.get(i).getDate(),
                        pulseArrayList.get(i).getPulse()), true, 61);
            }
            series.setTitle("Пульс");

            series.setColor(ContextCompat.getColor(context, R.color.mainGraphColor));
            series.setThickness(2);
            series.setDataPointsRadius(5);
            series.setDrawDataPoints(true);
        }
        return series;
    }

    public ArrayList<LineGraphSeries<DataPoint>> getPressureLineSeries(){
        ArrayList<Pressure> pressureArrayList = userInfo.pressureArrayList;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>();
        if(pressureArrayList.size()!=0) {
            for (int i = 0; i < pressureArrayList.size(); i++) {
                Collections.sort(pressureArrayList, new Comparator<Pressure>() {
                    public int compare(Pressure a, Pressure b) {
                        return a.getDate().compareTo(b.getDate());
                    }
                });
            }
            for (int i = 0; i < pressureArrayList.size(); i++) {
                series.appendData(new DataPoint(pressureArrayList.get(i).getDate(),
                        pressureArrayList.get(i).getSystolic()), true, 61);
                series2.appendData(new DataPoint(pressureArrayList.get(i).getDate(),
                        pressureArrayList.get(i).getDiastolic()), true, 61);
            }
            series.setTitle("Систолическое");

            series.setColor(ContextCompat.getColor(context, R.color.mainGraphColor));
            series.setThickness(2);
            series.setDataPointsRadius(5);
            series.setDrawDataPoints(true);

            series2.setTitle("Диастолическое");

            series2.setColor(ContextCompat.getColor(context, R.color.secondGraphColor));
            series2.setThickness(2);
            series2.setDataPointsRadius(5);
            series2.setDrawDataPoints(true);
        }
        ArrayList<LineGraphSeries<DataPoint>> seriesArrayList = new ArrayList<>();
        seriesArrayList.add(series);
        seriesArrayList.add(series2);
        return seriesArrayList;
    }

    public LineGraphSeries<DataPoint> getTemperatureLineSeries(){
        ArrayList<Temperature> temperatureArrayList = userInfo.temperatureArrayList;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        if(temperatureArrayList.size()!=0) {
            for (int i = 0; i < temperatureArrayList.size(); i++) {
                Collections.sort(temperatureArrayList, new Comparator<Temperature>() {
                    public int compare(Temperature a, Temperature b) {
                        return a.getDate().compareTo(b.getDate());
                    }
                });
            }
            for (int i = 0; i < temperatureArrayList.size(); i++) {
                series.appendData(new DataPoint(temperatureArrayList.get(i).getDate(),
                        temperatureArrayList.get(i).getTemperature()), true, 61);
            }
            series.setTitle("Температура");

            series.setColor(ContextCompat.getColor(context, R.color.mainGraphColor));
            series.setThickness(2);
            series.setDataPointsRadius(5);
            series.setDrawDataPoints(true);
        }
        return series;
    }

    public LineGraphSeries<DataPoint> getSleepLineSeries(){

        ArrayList<Sleep> sleepArrayList = userInfo.sleepArrayList;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        if(sleepArrayList.size()!=0) {
            for (int i = 0; i < sleepArrayList.size(); i++) {
                Collections.sort(sleepArrayList, new Comparator<Sleep>() {
                    public int compare(Sleep a, Sleep b) {
                        return a.getDate().compareTo(b.getDate());
                    }
                });
            }
            for (int i = 0; i < sleepArrayList.size(); i++) {
                series.appendData(new DataPoint(sleepArrayList.get(i).getDate(),
                        sleepArrayList.get(i).getTime()), true, 61);
            }
            series.setTitle("Время сна");

            series.setColor(ContextCompat.getColor(context, R.color.mainGraphColor));
            series.setThickness(2);
            series.setDataPointsRadius(5);
            series.setDrawDataPoints(true);
        }
        return series;
    }

    public void setMin_MaxPulse(int pulse){
        if(pulse>userInfo.max_pulse)
            userInfo.max_pulse=pulse;
        if(pulse<userInfo.min_pulse)
            userInfo.min_pulse=pulse;
    }
    public int getMinPulse(){return userInfo.min_pulse;}
    public int getMaxPulse(){return userInfo.max_pulse;}

    public void setMin_MaxPressure(int systolic, int diastolic){
        if(systolic>userInfo.max_systolic)
            userInfo.max_systolic=systolic;
        if(diastolic<userInfo.min_diastolic)
            userInfo.min_diastolic=diastolic;
    }
    public int getMaxSystolic(){return userInfo.max_systolic;}
    public int getMinDiastolic(){return userInfo.min_diastolic;}

    public void setMin_MaxTemperature(double temperature){
        if(temperature>userInfo.max_temperature)
            userInfo.max_temperature=temperature;
        if(temperature<userInfo.min_temperature)
            userInfo.min_temperature=temperature;
    }
    public double getMaxTemperature(){return userInfo.max_temperature;}
    public double getMinTemperature(){return userInfo.min_temperature;}

    public void setMaxSleep(double sleep){
        if(sleep>userInfo.max_sleep)
            userInfo.max_sleep=sleep;
    }
    public double getMaxSleep(){return userInfo.max_sleep;}

    public Calendar getBirthDate(){
        return userInfo.birthdate;
    }
    public boolean getMale(){return userInfo.male;}

    public void setSurname(String surname){
        userInfo.surname = surname;
    }
    public String getSurname(){return userInfo.surname;}
    public void setName(String name){
        userInfo.name = name;
    }
    public String getName(){return userInfo.name;}
    public void setPatronymic(String patronymic){
        userInfo.patronymic = patronymic;
    }
    public String getPatronymic(){return userInfo.patronymic;}
    public void setBirth_Date(int day, int month, int year){
        userInfo.birthdate.set(year, month, day);
    }
    public void setGender(boolean male){
        userInfo.male = male;
    }
}
