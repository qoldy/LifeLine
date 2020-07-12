package com.example.lifelinefinally;

import android.app.Application;
import android.widget.Toast;

public class LifeLine extends Application {
    private UserInfo user;
    public UserActions userActions;
    public void setUser(UserInfo user){
        this.user = user;
        userActions = new UserActions(this.user);
    }
    public void exportUser(){boolean result = JSONHelper.exportToJSON(this, user);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
    }
}
