package com.example.lifelinefinally;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = JSONHelper.importFromJSON(this);
        if(user!=null){
            Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось открыть данные", Toast.LENGTH_LONG).show();
            user = new UserInfo();
        }
        ((LifeLine)this.getApplication()).setUser(user);
        Intent intent = new Intent(this, DiaryActivity.class);
        startActivityForResult(intent, 0);
    }
}
