package com.example.lifelinefinally;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DiaryActivity.class);
        ((LifeLine)this.getApplication()).userActions.isReport=false;
        startActivityForResult(intent, 0);
    }
}
