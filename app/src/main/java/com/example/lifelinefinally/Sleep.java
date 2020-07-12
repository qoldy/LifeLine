package com.example.lifelinefinally;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Sleep {
    private int hours, minutes;
    private Date date;
    private String dateString;

    public Sleep(int hours, int minutes, Date date){
        this.hours = hours;
        this.minutes = minutes;
        this.date = date;
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        dateString = dateFormat.format(date);
    }

    public int getHours(){return hours;}
    public int getMinutes(){return minutes;}
    public String getInfo(){
        String info = Integer.toString(hours);
        if(hours%10==1&&hours!=11) info+= " час ";
        else if((hours%10==2||hours%10==3||hours%10==4)&&hours!=12&&hours!=13&&hours!=14) info+=" часа ";
        else info += " часов ";
        if(minutes<10)
            info+="0";
        info += Integer.toString(minutes);
        if(minutes%10==1&&minutes!=11) info+= " минута";
        else if((minutes%10==2||minutes%10==3||minutes%10==4)&&minutes!=12&&minutes!=13&&minutes!=14)
            info+=" минуты";
        else info += " минут";
        return info;
    }
    public double getTime(){return hours+minutes/60.;}


    public Date getDate(){return date;}
    public String getDateString(){return dateString;}

    public void setSleep(int hours, int minutes){
        this.hours = hours;
        this.minutes = minutes;
    }
}
