package com.example.lifelinefinally;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class FilterAdapter extends BaseAdapter {
    private ArrayList<String> filtersList;
    private LayoutInflater inflater;
    private LinearLayout layout;
    private int selectedIndex=0;

    public FilterAdapter(Context context, ArrayList<String> filters){
        inflater=LayoutInflater.from(context);
        filtersList = filters;
    }

    @Override
    public int getCount() {
        return filtersList.size();
    }

    @Override
    public Object getItem(int i) {return null;}

    @Override public long getItemId(int i) {return 0;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layout = (LinearLayout)inflater.inflate(R.layout.filter_item, parent, false);
        TextView textView = layout.findViewById(R.id.filter);
        textView.setText(filtersList.get(position));
        RadioButton radioButton = layout.findViewById(R.id.selectFilter);

        if(selectedIndex==position) radioButton.setChecked(true);
        else radioButton.setChecked(false);

        return layout;
    }

    public void setSelectedIndex(int index){selectedIndex = index;}
}
