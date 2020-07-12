package com.example.lifelinefinally;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Pulse {
    private int pulse;
    private Date date;
    private String dateString;

    public Pulse(int pulse, Date date){
        this.pulse = pulse;
        this.date = date;
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(date);
        dateString = dateText + " " + timeText;
    }

    public int getPulse(){ return pulse;}
    public String getInfo(){
        if(pulse%10==1&&pulse!=11)
            return pulse+" удар в минуту";
        if((pulse%10==2||pulse%10==3||pulse%10==4)&&pulse!=12&&pulse!=13&&pulse!=14)
            return pulse+" удара в минуту";
        return pulse+" ударов в минуту";
    }

    public Date getDate(){return date;}
    public String getDateString(){return dateString;}

    public void setPulse(int pulse){this.pulse = pulse;}
}
