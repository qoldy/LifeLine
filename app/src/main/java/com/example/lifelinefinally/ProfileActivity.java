package com.example.lifelinefinally;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {
    TextView surnameText, nameText, birth_dateText, genderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        surnameText = findViewById(R.id.surname);
        surnameText.setText(((LifeLine)this.getApplication()).userActions.getSurname());
        nameText = findViewById(R.id.full_name);
        nameText.setText(((LifeLine)this.getApplication()).userActions.getName() + " "
        + ((LifeLine)this.getApplication()).userActions.getPatronymic());
        birth_dateText = findViewById(R.id.birth_date);
        Calendar birth_date = ((LifeLine)this.getApplication()).userActions.getBirthDate();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        birth_dateText.setText(getResources().getString(R.string.birth_date) + ":" +
                dateFormat.format(birth_date.getTime()) );
        genderText = findViewById(R.id.gender);
        if(((LifeLine)this.getApplication()).userActions.getMale())
            genderText.setText(getResources().getString(R.string.male));
        else
            genderText.setText(getResources().getString(R.string.female));
    }

    public void onEditClick(View view){
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivityForResult(intent, 0);
    }
    @Override
    public void onBackPressed() { }
}
