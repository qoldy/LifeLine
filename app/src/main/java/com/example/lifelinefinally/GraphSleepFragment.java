package com.example.lifelinefinally;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class GraphSleepFragment extends Fragment {
    GraphView graph;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph_sleep, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        final DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        graph = (GraphView)getView().findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series =
                ((LifeLine)this.getActivity().getApplication()).userActions.getSleepLineSeries();
        graph.addSeries(series);

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX){
                if(isValueX)
                    return dateFormat.format(new Date((long)value));
                String format = Integer.toString((int)value);
                int minutes = (int)((value-(int)value)*60);
                if(minutes>10)
                    format +=  ":" + minutes;
                else format += ":0" + minutes;
                return format;
            }
        });

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().scrollToEnd();
        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.getViewport().setMaxY
                (((LifeLine)this.getActivity().getApplication()).userActions.getMaxSleep()+1);
        graph.getViewport().setMinY(0);
        Calendar buf = new GregorianCalendar();
        Date currentDate = new Date();
        graph.getViewport().setMaxX(currentDate.getTime());
        buf.setTime(currentDate);
        buf.add(Calendar.DATE, -5);
        graph.getViewport().setMinX(buf.getTime().getTime());
        graph.getGridLabelRenderer().setNumHorizontalLabels(6);
        if(((LifeLine)this.getActivity().getApplication()).userActions.isReport)
            setReportGraph();
    }

    public void setReportGraph(){
        graph.getLegendRenderer().setVisible(false);
        LineGraphSeries<DataPoint> normal_series= new LineGraphSeries<>();
        Normal normal = new Normal(((LifeLine)this.getActivity().getApplication()).userActions.getBirthDate(),
                ((LifeLine)this.getActivity().getApplication()).userActions.getMale());
        ArrayList<Sleep> arrayList = new ArrayList<>
                (((LifeLine)this.getActivity().getApplication()).userActions.getSleepArrayList());
        double average = normal.getAvSleep();
        Date date = new Date();
        Calendar buf = new GregorianCalendar();
        buf.setTime(date);
        if(!arrayList.isEmpty()) {
            normal_series.appendData(new DataPoint(arrayList.get(0).getDate(), average), true, 61);
        }
        else {
            buf.add(Calendar.DATE, -5);
            normal_series.appendData(new DataPoint(buf.getTime(), average), true, 61);
        }
        normal_series.appendData(new DataPoint(date, average), true, 61);
        normal_series.setDrawDataPoints(false);
        normal_series.setThickness(2);
        normal_series.setColor(ContextCompat.getColor(getView().getContext(), R.color.normal1GraphColor));
        graph.addSeries(normal_series);
    }
}
