package com.example.lifelinefinally;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Calendar;
import java.util.Date;

public class EditProfileActivity extends AppCompatActivity {
     private NumberPicker dayPicker, monthPicker, yearPicker;
     private Calendar birthDate;
     private int monthLength[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
     private EditText surnameEdit, nameEdit, patronymicEdit;
     private RadioButton maleRB, femaleRB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        birthDate = ((LifeLine)this.getApplication()).userActions.getBirthDate();
        dayPicker = findViewById(R.id.dayPicker);
        monthPicker = findViewById(R.id.monthPicker);
        yearPicker = findViewById(R.id.yearPicker);
        monthPicker.setMaxValue(12);
        monthPicker.setMinValue(1);
        yearPicker.setMaxValue(2020);
        yearPicker.setMinValue(1920);
        dayPicker.setMinValue(1);
        yearPicker.setValue(birthDate.get(Calendar.YEAR));
        monthPicker.setValue(birthDate.get(Calendar.MONTH)+1);
        dayPicker.setMaxValue(monthLength[monthPicker.getValue()-1]);
        if(monthPicker.getValue()==2&&yearPicker.getValue()%4==0){
            dayPicker.setMaxValue(29);
        }
        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                dayPicker.setMaxValue(monthLength[monthPicker.getValue()-1]);
                if(monthPicker.getValue()==2&&yearPicker.getValue()%4==0){
                    dayPicker.setMaxValue(29);
                }
            }
        });
        yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(monthPicker.getValue()==2&&yearPicker.getValue()%4==0){
                    dayPicker.setMaxValue(29);
                }
                else if(monthPicker.getValue()==2)
                    dayPicker.setMaxValue(28);
            }
        });
        surnameEdit = findViewById(R.id.surnameEdit);
        surnameEdit.setText(((LifeLine)this.getApplication()).userActions.getSurname());
        nameEdit = findViewById(R.id.nameEdit);
        nameEdit.setText(((LifeLine)this.getApplication()).userActions.getName());
        patronymicEdit = findViewById(R.id.patronymicEdit);
        patronymicEdit.setText(((LifeLine)this.getApplication()).userActions.getPatronymic());
        maleRB = findViewById(R.id.maleRadio);
        maleRB.setChecked(((LifeLine)this.getApplication()).userActions.getMale());
        femaleRB = findViewById(R.id.femaleRadio);
        femaleRB.setChecked(!((LifeLine)this.getApplication()).userActions.getMale());
        maleRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                maleRB.setChecked(true);
                femaleRB.setChecked(false);
            }
        });
        femaleRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                femaleRB.setChecked(true);
                maleRB.setChecked(false);
            }
        });
    }

    public void onSaveProfileClick(View view){
        String surname = surnameEdit.getText().toString();
        if(surname.equals("")){
            Toast.makeText(this, getResources().
                    getString(R.string.errorFormFilling), Toast.LENGTH_SHORT).show();
            return;
        }
        String name = nameEdit.getText().toString();
        if(name.equals("")){
            Toast.makeText(this, getResources().
                    getString(R.string.errorFormFilling), Toast.LENGTH_SHORT).show();
            return;
        }
        String patronymic = patronymicEdit.getText().toString();
        ((LifeLine)this.getApplication()).userActions.setSurname(surname);
        ((LifeLine)this.getApplication()).userActions.setName(name);
        ((LifeLine)this.getApplication()).userActions.setPatronymic(patronymic);
        ((LifeLine)this.getApplication()).userActions.setBirth_Date
                (dayPicker.getValue(), monthPicker.getValue()-1, yearPicker.getValue());
        ((LifeLine)this.getApplication()).userActions.setGender(maleRB.isChecked());
        Intent intent = new Intent(this, ProfileActivity.class);
        ((LifeLine)this.getApplication()).exportUser();
        startActivityForResult(intent, 0);
    }

    public void onCancelProfileEditClick(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivityForResult(intent, 0);
    }
}
