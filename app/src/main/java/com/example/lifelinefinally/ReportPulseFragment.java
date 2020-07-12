package com.example.lifelinefinally;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class ReportPulseFragment extends Fragment {
    private Adapter adapter;
    private ListView listView;


    private Button filtersButton;
    private TextView filtersView;
    private Dialog dialog;
    private ListView sortTypeView, resultTypeView;
    private FilterAdapter sortAdapter, resultAdapter;
    private String sortType, resultType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pulse_report, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final Context context = (LifeLine)this.getActivity().getApplication();
        listView = getView().findViewById(R.id.dataList);
        adapter = new Adapter(this.getActivity().getApplication(), "pulse",
                ((LifeLine)this.getActivity().getApplication()).userActions);
        listView.setAdapter(adapter);

        dialog = new Dialog(getView().getContext());
        dialog.setContentView(R.layout.dialog_filters);

        sortAdapter = new FilterAdapter(dialog.getContext(), new ArrayList<>
                (Arrays.asList(context.getResources().getStringArray(R.array.sortTypeArr))));
        sortTypeView=dialog.findViewById(R.id.sortTypeList);
        sortTypeView.setAdapter(sortAdapter);
        sortTypeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sortAdapter.setSelectedIndex(position);
                sortAdapter.notifyDataSetChanged();
                adapter.applyFilter(true, position);
                adapter.notifyDataSetChanged();
                sortType = context.getResources().getStringArray(R.array.sortTypeArr)[position];
                filtersView.setText(context.getResources().getString(R.string.whichSort) + " " + sortType + "\n"
                        + context.getResources().getString(R.string.whichResult) + " " + resultType);
            }
        });

        resultAdapter = new FilterAdapter(dialog.getContext(),new ArrayList<>
                (Arrays.asList(context.getResources().getStringArray(R.array.resultTypeArr))));
        resultTypeView=dialog.findViewById(R.id.resultsTypeList);
        resultTypeView.setAdapter(resultAdapter);
        resultTypeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                resultAdapter.setSelectedIndex(position);
                resultAdapter.notifyDataSetChanged();
                adapter.applyFilter(false, position);
                adapter.notifyDataSetChanged();
                resultType = context.getResources().getStringArray(R.array.resultTypeArr)[position];
                filtersView.setText(context.getResources().getString(R.string.whichSort) + " " + sortType + "\n"
                        + context.getResources().getString(R.string.whichResult) + " " + resultType);
            }
        });

        sortType = context.getResources().getStringArray(R.array.sortTypeArr)[0];
        resultType = context.getResources().getStringArray(R.array.resultTypeArr)[0];
        filtersView = getView().findViewById(R.id.textFilters);
        filtersView.setText(context.getResources().getString(R.string.whichSort) + " " + sortType + "\n"
                + context.getResources().getString(R.string.whichResult) + " " + resultType);
        filtersButton = getView().findViewById(R.id.filters);
        filtersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

}