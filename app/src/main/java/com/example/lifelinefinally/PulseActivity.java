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

public class PulseActivity extends AppCompatActivity {
    private EditText dataText, changeText;
    private Adapter adapter;
    private ListView listView;
    private Dialog dialog;
    private int changeIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulse);
        dataText = findViewById(R.id.dataText);
        listView = findViewById(R.id.dataList);

        dialog = new Dialog(PulseActivity.this);
        dialog.setContentView(R.layout.dialog_pulse);

        adapter = new Adapter(this, "pulse",
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
        if(!dataText.getText().toString().equals("")){
            int pulse = Integer.parseInt(dataText.getText().toString());
            Date currentDate = new Date();
            ((LifeLine)this.getApplication()).userActions.
                    addPulse(new Pulse(pulse, currentDate));
            ((LifeLine)this.getApplication()).userActions.setMin_MaxPulse(pulse);
            ((LifeLine) this.getApplication()).exportUser();
            adapter.notifyDataSetChanged();
        }
    }

    private void changeNote(int index){
        dialog.show();
        Pulse change = ((LifeLine)this.getApplication()).
                userActions.getPulseArrayList().get(index);
        TextView dateView = dialog.findViewById(R.id.date);
        dateView.setText(change.getDateString());

        changeText = dialog.findViewById(R.id.dataText);
        changeText.setHint(Integer.toString(change.getPulse()));

        changeIndex = index;
    }

    public void onSaveClick(View view){
        if(!changeText.getText().toString().equals("")){
            int pulse = Integer.parseInt(changeText.getText().toString());
            ((LifeLine)this.getApplication()).userActions.getPulseArrayList().
                    get(changeIndex).setPulse(pulse);
            ((LifeLine)this.getApplication()).userActions.setMin_MaxPulse(pulse);
            ((LifeLine) this.getApplication()).exportUser();
            adapter.notifyDataSetChanged();
        }
        dialog.cancel();
    }

    public void onCancelClick(View view){
        dialog.cancel();
    }

    public void onDeleteClick(View view){
        ((LifeLine)this.getApplication()).userActions.getPulseArrayList().remove(changeIndex);
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
