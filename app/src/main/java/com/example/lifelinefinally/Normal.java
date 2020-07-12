package com.example.lifelinefinally;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Normal {
    private double avTemperature = 36.6;
    public double getAvTemperature(){return avTemperature;}
    private int age;
    private boolean male;
    private double avSleep = 7.66;
    public double getAvSleep(){return avSleep;}
    public Normal(Calendar birth_date, boolean male){
        Calendar buf = new GregorianCalendar();
        buf.setTime(new Date());
        buf.add(Calendar.YEAR, - birth_date.get(Calendar.YEAR));
        buf.add(Calendar.MONTH, - birth_date.get(Calendar.MONTH));
        buf.add(Calendar.DATE, - birth_date.get(Calendar.DATE));
        age = buf.get(Calendar.YEAR);
        this.male = male;
    }

    public Integer getAvSystolic(){
        if(male){
            if(age<1)
                return 96;
            else if(age<11)
                return 103;
            else if(age<21)
                return 123;
            else if(age<31)
                return 126;
            else if(age<41)
                return 129;
            else if(age<51)
                return 135;
            else if(age<61)
                return 142;
            else if(age<71)
                return 145;
            else if(age<81)
                return 147;
            else
                return 145;
        }
        else{
            if(age<1)
                return 95;
            else if(age<11)
                return 103;
            else if(age<21)
                return 116;
            else if(age<31)
                return 120;
            else if(age<41)
                return 127;
            else if(age<51)
                return 137;
            else if(age<61)
                return 144;
            else if(age<71)
                return 159;
            else if(age<81)
                return 157;
            else
                return 150;
        }
    }
    public Integer getAvDiastolic(){
        if(male){
            if(age<1)
                return 66;
            else if(age<11)
                return 69;
            else if(age<21)
                return 76;
            else if(age<31)
                return 79;
            else if(age<41)
                return 81;
            else if(age<51)
                return 83;
            else if(age<61)
                return 85;
            else if(age<71)
                return 82;
            else if(age<81)
                return 82;
            else
                return 78;
        }
        else{
            if(age<1)
                return 65;
            else if(age<11)
                return 70;
            else if(age<21)
                return 72;
            else if(age<31)
                return 75;
            else if(age<41)
                return 80;
            else if(age<51)
                return 84;
            else if(age<61)
                return 85;
            else if(age<71)
                return 85;
            else if(age<81)
                return 83;
            else
                return 79;
        }
    }

    public Integer getAvPulse(){
        if(age<=1)
            return 132;
        else if (age<=4)
            return 124;
        else if (age<=6)
            return 106;
        else if (age<=8)
            return 98;
        else if (age<=10)
            return 88;
        else if (age<=12)
            return 80;
        else if (age<=15)
            return 75;
        else if (age<=50)
            return 70;
        else if (age<=60)
            return 74;
        else
            return 79;
    }
}