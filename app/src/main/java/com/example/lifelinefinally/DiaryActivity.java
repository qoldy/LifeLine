package com.example.lifelinefinally;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class DiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((LifeLine)this.getApplication()).userActions.setContext(this);
        setContentView(R.layout.activity_diary);
    }

    public void toPulseClick(View view){
        Intent intent = new Intent(this, PulseActivity.class);
        startActivityForResult(intent, 0);
    }

    public void toPressureClick(View view){
        Intent intent = new Intent(this, PressureActivity.class);
        startActivityForResult(intent, 0);
    }

    public void toTemperatureClick(View view){
        Intent intent = new Intent(this, TemperatureActivity.class);
        startActivityForResult(intent, 0);
    }

    public void toSleepClick(View view){
        Intent intent = new Intent(this, SleepActivity.class);
        startActivityForResult(intent, 0);
    }

    public void toReportClick(View view){
        Intent intent = new Intent(this, ReportActivity.class);
        ((LifeLine)this.getApplication()).userActions.isReport=true;
        startActivityForResult(intent, 0);
    }
}
