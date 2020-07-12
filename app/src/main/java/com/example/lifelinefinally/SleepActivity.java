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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SleepActivity extends AppCompatActivity {
    private EditText dataText1, dataText2, changeText1, changeText2;
    private Adapter adapter;
    private ListView listView;
    private Dialog dialog;
    private int changeIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        dataText1 = findViewById(R.id.dataText);
        dataText2 = findViewById(R.id.dataText2);
        listView = findViewById(R.id.dataList);

        dialog = new Dialog(SleepActivity.this);
        dialog.setContentView(R.layout.dialog_sleep);

        adapter = new Adapter(this, "sleep",
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
            int hours = Integer.parseInt(dataText1.getText().toString());
            int minutes = Integer.parseInt(dataText2.getText().toString());
            Date currentDate = new Date();
            Sleep sleep = new Sleep(hours, minutes, currentDate);
            ((LifeLine)this.getApplication()).userActions.
                    addSleep(sleep);
            ((LifeLine)this.getApplication()).userActions.setMaxSleep
                    (hours+minutes/60.);
            ((LifeLine) this.getApplication()).exportUser();
            adapter.notifyDataSetChanged();
        }
    }

    private void changeNote(int index){
        dialog.show();
        Sleep change = ((LifeLine)this.getApplication()).
                userActions.getSleepArrayList().get(index);
        TextView dateView = dialog.findViewById(R.id.date);
        dateView.setText(change.getDateString());

        changeText1 = dialog.findViewById(R.id.dataText);
        changeText1.setHint(Integer.toString(change.getHours()));

        changeText2 = dialog.findViewById(R.id.dataText2);
        changeText2.setHint(Integer.toString(change.getMinutes()));

        changeIndex = index;
    }

    public void onSaveClick(View view){
        if(!changeText1.getText().toString().equals("")&&!changeText2.getText().toString().equals("")){
            int hours = Integer.parseInt(changeText1.getText().toString());
            int minutes = Integer.parseInt(changeText2.getText().toString());
            ((LifeLine)this.getApplication()).userActions.getSleepArrayList().
                    get(changeIndex).setSleep(hours, minutes);
            ((LifeLine)this.getApplication()).userActions.setMaxSleep(hours+minutes/60.);
            ((LifeLine) this.getApplication()).exportUser();
            adapter.notifyDataSetChanged();
        }
        dialog.cancel();
    }

    public void onCancelClick(View view){
        dialog.cancel();
    }

    public void onDeleteClick(View view){
        ((LifeLine)this.getApplication()).userActions.getSleepArrayList().remove(changeIndex);
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
