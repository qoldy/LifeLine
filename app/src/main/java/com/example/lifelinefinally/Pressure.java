package com.example.lifelinefinally;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Pressure {
    private int systolic, diastolic;
    private Date date;
    private String dateString;

    public Pressure(int systolic, int diastolic, Date date){
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.date = date;
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(date);
        dateString = dateText + " " + timeText;
    }

    public int getSystolic(){return systolic;}
    public int getDiastolic(){return diastolic;}
    public String getInfo(){return systolic+"/"+diastolic+" мм рт. ст.";}

    public Date getDate(){return date;}
    public String getDateString(){return dateString;}

    public void setPressure(int systolic, int diastolic){
        this.systolic = systolic;
        this.diastolic = diastolic;
    }
}
