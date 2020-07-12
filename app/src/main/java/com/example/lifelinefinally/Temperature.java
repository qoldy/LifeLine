package com.example.lifelinefinally;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Temperature {
    private double temperature;
    private Date date;
    private String dateString;

    public Temperature(double temperature, Date date){
        this.temperature = temperature;
        this.date = date;
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(date);
        dateString = dateText + " " + timeText;
    }

    public double getTemperature(){return temperature;}
    public String getInfo(){return temperature+"\u00B0C";}

    public Date getDate(){return date;}
    public String getDateString(){return dateString;}

    public void setTemperature(double temperature){this.temperature = temperature;}
}
