package com.example.lifelinefinally;


import android.content.Context;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONHelper{
    private static String file_name = "user.json";

    static void setFileName(String name){file_name = name;}
    static boolean exportToJSON(Context context, UserInfo user) {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setUser(user);
        String jsonString = gson.toJson(dataItems);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(file_name, Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    static UserInfo importFromJSON(Context context) {

        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = context.openFileInput(file_name);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            return dataItems.getUser();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private static class DataItems {
        private UserInfo user;

        UserInfo getUser() {
            return user;
        }
        void setUser(UserInfo u) {
            user = u;
        }
    }
}
