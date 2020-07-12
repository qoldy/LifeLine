package com.example.lifelinefinally;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class PressureActivity extends AppCompatActivity {
    private EditText dataText1, dataText2, changeText1, changeText2;
    private Adapter adapter;
    private ListView listView;
    private Dialog dialog;
    private int changeIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);
        dataText1 = findViewById(R.id.dataText);
        dataText2 = findViewById(R.id.dataText2);
        listView = findViewById(R.id.dataList);

        dialog = new Dialog(PressureActivity.this);
        dialog.setContentView(R.layout.dialog_pressure);

        adapter = new Adapter(this, "pressure",
                ((LifeLine)this.getApplication()).userActions);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                changeNote(position);
                return false;
            }
        });
    }

    public void onAddClick(View view){
        if(!dataText1.getText().toString().equals("")&&!dataText2.getText().toString().equals("")){
            int systolic = Integer.parseInt(dataText1.getText().toString());
            int diastolic = Integer.parseInt(dataText2.getText().toString());
            Date currentDate = new Date();
            ((LifeLine)this.getApplication()).userActions.
                    addPressure(new Pressure(systolic, diastolic, currentDate));
            ((LifeLine)this.getApplication()).userActions.setMin_MaxPressure(systolic,diastolic);
            ((LifeLine) this.getApplication()).exportUser();
            adapter.notifyDataSetChanged();
        }
    }

    private void changeNote(int index){
        dialog.show();
        Pressure change = ((LifeLine)this.getApplication()).
                userActions.getPressureArrayList().get(index);
        TextView dateView = dialog.findViewById(R.id.date);
        dateView.setText(change.getDateString());

        changeText1 = dialog.findViewById(R.id.dataText);
        changeText1.setHint(Integer.toString(change.getSystolic()));

        changeText2 = dialog.findViewById(R.id.dataText2);
        changeText2.setHint(Integer.toString(change.getDiastolic()));

        changeIndex = index;
    }

    public void onSaveClick(View view){
        if(!changeText1.getText().toString().equals("")&&!changeText2.getText().toString().equals("")){
            int systolic = Integer.parseInt(changeText1.getText().toString());
            int diastolic = Integer.parseInt(changeText2.getText().toString());
            ((LifeLine)this.getApplication()).userActions.getPressureArrayList().
                    get(changeIndex).setPressure(systolic, diastolic);
            ((LifeLine) this.getApplication()).exportUser();
            adapter.notifyDataSetChanged();
        }
        dialog.cancel();
    }

    public void onCancelClick(View view){
        dialog.cancel();
    }

    public void onDeleteClick(View view){
        ((LifeLine)this.getApplication()).userActions.getPressureArrayList().remove(changeIndex);
        ((LifeLine) this.getApplication()).exportUser();
        adapter.notifyDataSetChanged();
        dialog.cancel();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DiaryActivity.class);
        startActivityForResult(intent, 0);
    }
}
