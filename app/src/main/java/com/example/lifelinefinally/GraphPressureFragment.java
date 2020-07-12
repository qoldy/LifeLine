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
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class GraphPressureFragment extends Fragment {
    private GraphView graph;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph_pressure, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        final DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        graph = (GraphView)getView().findViewById(R.id.graph);
        ArrayList<LineGraphSeries<DataPoint>> seriesArrayList =
                ((LifeLine)this.getActivity().getApplication()).userActions.getPressureLineSeries();
        graph.addSeries(seriesArrayList.get(0));
        graph.addSeries(seriesArrayList.get(1));

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX){
                if(isValueX)
                    return dateFormat.format(new Date((long)value));
                return super.formatLabel(value, isValueX);
            }
        });

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().scrollToEnd();
        graph.getGridLabelRenderer().setHumanRounding(false);

        graph.getViewport().setMaxY
                (((LifeLine)this.getActivity().getApplication()).userActions.getMaxSystolic()+10);
        graph.getViewport().setMinY
                (((LifeLine)this.getActivity().getApplication()).userActions.getMinDiastolic()-10);
        Date currentDate = new Date();
        graph.getViewport().setMaxX(currentDate.getTime());
        Calendar buf = new GregorianCalendar();
        buf.setTime(currentDate);
        buf.add(Calendar.DATE, -5);
        graph.getViewport().setMinX(buf.getTime().getTime());
        graph.getGridLabelRenderer().setNumVerticalLabels(5);
        graph.getGridLabelRenderer().setNumHorizontalLabels(6);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getLegendRenderer().setTextColor(ContextCompat.getColor(
                ((LifeLine)this.getActivity().getApplication()), R.color.darkBlue));
        if(((LifeLine)this.getActivity().getApplication()).userActions.isReport)
            setReportGraph();
    }

    public void setReportGraph(){
        graph.getLegendRenderer().setVisible(false);
        LineGraphSeries<DataPoint> normal_series= new LineGraphSeries<>();
        Normal normal = new Normal(((LifeLine)this.getActivity().getApplication()).userActions.getBirthDate(),
                ((LifeLine)this.getActivity().getApplication()).userActions.getMale());
        ArrayList<Pressure> arrayList = new ArrayList<>
                (((LifeLine)this.getActivity().getApplication()).userActions.getPressureArrayList());
        int average = normal.getAvSystolic();
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
        average = normal.getAvDiastolic();
        normal_series= new LineGraphSeries<>();
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
        normal_series.setColor(ContextCompat.getColor(getView().getContext(), R.color.normal2GraphColor));
        graph.addSeries(normal_series);
    }
}