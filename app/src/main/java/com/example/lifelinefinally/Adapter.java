package com.example.lifelinefinally;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

public class Adapter extends BaseAdapter {
    private ArrayList<Pressure> pressureList;
    private ArrayList<Temperature> temperatureList;
    private ArrayList<Pulse> pulseList;
    private ArrayList<Sleep> sleepList;
    private LayoutInflater inflater;
    private LinearLayout layout;
    private String type;
    private int sort=0, result=0;
    private UserActions userActions;

    public Adapter(Context context, String type, UserActions userActions){
        this.userActions = userActions;
        this.type = type;
        if(type.equals("pulse"))
            pulseList = userActions.getPulseArrayList();
        else if(type.equals("pressure"))
            pressureList = userActions.getPressureArrayList();
        else if(type.equals("temperature"))
            temperatureList = userActions.getTemperatureArrayList();
        else if(type.equals("sleep"))
            sleepList = userActions.getSleepArrayList();
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(type.equals("pulse")) return pulseList.size();
        if(type.equals("pressure")) return pressureList.size();
        if(type.equals("temperature")) return temperatureList.size();
        if(type.equals("sleep")) return sleepList.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {return null;}

    @Override public long getItemId(int i) {return 0;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layout = (LinearLayout)inflater.inflate(R.layout.list_item, parent, false);
        String date = "", info = "";
        if(type.equals("pulse"))
        {
            Pulse curr = pulseList.get(position);
            date = curr.getDateString();
            info = curr.getInfo();
        }
        else if(type.equals("pressure")){
            Pressure curr = pressureList.get(position);
            date = curr.getDateString();
            info = curr.getInfo();
        }
        else if(type.equals("temperature")){
            Temperature curr = temperatureList.get(position);
            date = curr.getDateString();
            info = curr.getInfo();
        }
        else if(type.equals("sleep")){
            Sleep curr = sleepList.get(position);
            date = curr.getDateString();
            info = curr.getInfo();
        }
        TextView textView = layout.findViewById(R.id.info);
        textView.setText(info);
        textView = layout.findViewById(R.id.date);
        textView.setText(date);
        layout.setTag(position);
        return layout;
    }

    public void applyFilter(boolean isSort, int type){
        if(isSort) sort=type;
        else result = type;
        switch (this.type){
            case "pulse":{
                pulseList = new ArrayList<>(userActions.getPulseArrayList());
                Date currentDate = new Date();
                Calendar buf = new GregorianCalendar();
                buf.setTime(currentDate);
                if(result==2){
                    buf.add(Calendar.DATE, -7);
                    for(int i=0; i<pulseList.size();i++){
                        if(pulseList.get(i).getDate().before(buf.getTime())){
                            pulseList.remove(i);
                            i--;
                        }
                    }
                }
                else if(result==1){
                    buf.add(Calendar.DATE, -31);
                    for(int i=0; i<pulseList.size();i++){
                        if(pulseList.get(i).getDate().before(buf.getTime())){
                            pulseList.remove(i);
                            i--;
                        }
                    }
                }
                if(sort==0){
                    for (int i = 0; i < pulseList.size(); i++) {
                        Collections.sort(pulseList, new Comparator<Pulse>() {
                            public int compare(Pulse a, Pulse b) {
                                return a.getDate().compareTo(b.getDate());
                            }
                        });
                    }
                }
                else {
                    for (int i = 0; i < pulseList.size(); i++) {
                        Collections.sort(pulseList, new Comparator<Pulse>() {
                            public int compare(Pulse a, Pulse b) {
                                return b.getDate().compareTo(a.getDate());
                            }
                        });
                    }
                }
                break;
            }
            case "pressure":{
                pressureList = new ArrayList<>(userActions.getPressureArrayList());
                Date currentDate = new Date();
                Calendar buf = new GregorianCalendar();
                buf.setTime(currentDate);
                if(result==2){
                    buf.add(Calendar.DATE, -7);
                    for(int i=0; i<pressureList.size();i++){
                        if(pressureList.get(i).getDate().before(buf.getTime())){
                            pressureList.remove(i);
                            i--;
                        }
                    }
                }
                else if(result==1){
                    buf.add(Calendar.DATE, -31);
                    for(int i=0; i<pressureList.size();i++){
                        if(pressureList.get(i).getDate().before(buf.getTime())){
                            pressureList.remove(i);
                            i--;
                        }
                    }
                }
                if(sort==0){
                    for (int i = 0; i < pressureList.size(); i++) {
                        Collections.sort(pressureList, new Comparator<Pressure>() {
                            public int compare(Pressure a, Pressure b) {
                                return a.getDate().compareTo(b.getDate());
                            }
                        });
                    }
                }
                else {
                    for (int i = 0; i < pressureList.size(); i++) {
                        Collections.sort(pressureList, new Comparator<Pressure>() {
                            public int compare(Pressure a, Pressure b) {
                                return b.getDate().compareTo(a.getDate());
                            }
                        });
                    }
                }
                break;
            }
            case "temperature":{
                temperatureList = new ArrayList<>(userActions.getTemperatureArrayList());
                Date currentDate = new Date();
                Calendar buf = new GregorianCalendar();
                buf.setTime(currentDate);
                if(result==2){
                    buf.add(Calendar.DATE, -7);
                    for(int i=0; i<temperatureList.size();i++){
                        if(temperatureList.get(i).getDate().before(buf.getTime())){
                            temperatureList.remove(i);
                            i--;
                        }
                    }
                }
                else if(result==1){
                    buf.add(Calendar.DATE, -31);
                    for(int i=0; i<temperatureList.size();i++){
                        if(temperatureList.get(i).getDate().before(buf.getTime())){
                            temperatureList.remove(i);
                            i--;
                        }
                    }
                }
                if(sort==0){
                    for (int i = 0; i < temperatureList.size(); i++) {
                        Collections.sort(temperatureList, new Comparator<Temperature>() {
                            public int compare(Temperature a, Temperature b) {
                                return a.getDate().compareTo(b.getDate());
                            }
                        });
                    }
                }
                else {
                    for (int i = 0; i < temperatureList.size(); i++) {
                        Collections.sort(temperatureList, new Comparator<Temperature>() {
                            public int compare(Temperature a, Temperature b) {
                                return b.getDate().compareTo(a.getDate());
                            }
                        });
                    }
                }
                break;
            }
            case "sleep":{
                sleepList = new ArrayList<>(userActions.getSleepArrayList());
                Date currentDate = new Date();
                Calendar buf = new GregorianCalendar();
                buf.setTime(currentDate);
                if(result==2){
                    buf.add(Calendar.DATE, -7);
                    for(int i=0; i<sleepList.size();i++){
                        if(sleepList.get(i).getDate().before(buf.getTime())){
                            sleepList.remove(i);
                            i--;
                        }
                    }
                }
                else if(result==1){
                    buf.add(Calendar.DATE, -31);
                    for(int i=0; i<sleepList.size();i++){
                        if(sleepList.get(i).getDate().before(buf.getTime())){
                            sleepList.remove(i);
                            i--;
                        }
                    }
                }
                if(sort==0){
                    for (int i = 0; i < sleepList.size(); i++) {
                        Collections.sort(sleepList, new Comparator<Sleep>() {
                            public int compare(Sleep a, Sleep b) {
                                return a.getDate().compareTo(b.getDate());
                            }
                        });
                    }
                }
                else {
                    for (int i = 0; i < sleepList.size(); i++) {
                        Collections.sort(sleepList, new Comparator<Sleep>() {
                            public int compare(Sleep a, Sleep b) {
                                return b.getDate().compareTo(a.getDate());
                            }
                        });
                    }
                }
                break;
            }
        }
    }
}
