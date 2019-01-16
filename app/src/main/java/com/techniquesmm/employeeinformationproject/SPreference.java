package com.techniquesmm.employeeinformationproject;

import android.content.Context;




public class SPreference {

    public static void saveSignUp(Context con, String lname, String email, String password) {
        android.content.SharedPreferences preferences = con.getSharedPreferences("TM_KeY", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        // editor.putString("firstName",fname);
        editor.putString("name", lname);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
        editor.commit();
    }

    public static void saveChangepwd(Context con, String newpwd) {
        android.content.SharedPreferences preferences = con.getSharedPreferences("TM_KeY", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        //editor.putString("current password", oldpwd);
        editor.putString("password", newpwd);
        editor.apply();
        editor.commit();
    }

    public static String[] getSignUp(Context con) {
        String arbranch[] = new String[6];
        android.content.SharedPreferences prefs =  con.getSharedPreferences("TM_KeY", Context.MODE_PRIVATE);
        //    String bfname = prefs.getString("firstName", "");
        String blname = prefs.getString("name", "");
        String bemail = prefs.getString("email", "");
        String bpassword = prefs.getString("password", "");
        //add to string array

        arbranch[0] = blname;
        arbranch[1] = bemail;
        arbranch[2] = bpassword;
        return  arbranch;
    }


    public static String removeRegister(Context con) {
        android.content.SharedPreferences prefs = con.getApplicationContext().getSharedPreferences("TM_KeY", Context.MODE_PRIVATE);
        String Sessiontoken = prefs.getString("Name", "");
        if (Sessiontoken != null) {

            prefs.edit().remove("Name").commit();
            prefs.edit().remove("email").commit();
            prefs.edit().remove("password").commit();
        } else {
            Sessiontoken = "Account removing is Error....!";
        }
        return Sessiontoken;
    }
}


