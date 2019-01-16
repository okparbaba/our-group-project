package com.techniquesmm.employeeinformationproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ChangePwd extends Activity {
    public SharedPreferences prefs;
    EditText newPass, currentPass,confPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepwd);
        CheckBox showPassword = findViewById(R.id.checkBox);
        currentPass=(EditText)findViewById(R.id.current);
        newPass = (EditText) findViewById(R.id.change);
        confPass = (EditText) findViewById(R.id.retype);
        Button btcancel = (Button) findViewById(R.id.cancel);

        Button btnok = findViewById(R.id.ok);
        btnok.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v) {
                String currentpwd = currentPass.getText().toString();
                String newpwd = newPass.getText().toString();
                String confipwd = confPass.getText().toString();
                String[] oldpwd = SPreference.getSignUp(ChangePwd.this);
                String oldpassword = oldpwd[2];

                if (oldpassword != ("") && oldpassword.equals(currentpwd)) {
                    if (newpwd.length() < 5 && !isValidPassword(newpwd)) {
                        Toast.makeText(ChangePwd.this, "At least six passwords", Toast.LENGTH_SHORT).show();
                    } else if (newpwd.equals(confipwd)) {
                        SPreference.removeRegister(ChangePwd.this);
                        SPreference.saveChangepwd(ChangePwd.this, newpwd);
                        Toast.makeText(ChangePwd.this, "Password successfully changed...", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangePwd.this, DisplaySQLiteDataActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(ChangePwd.this, "confirm password is invalid..", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ChangePwd.this, "Confirm password was wrong..", Toast.LENGTH_LONG).show();

                }
            }
        });

        btcancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                currentPass.setText("");
                newPass.setText("");
                confPass.setText("");
            }
        });

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    currentPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    newPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    confPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    currentPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    newPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    confPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

    }

    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN = Pattern.compile("[a-z-A-Z0-9\\!\\@\\#\\$]{8,24}");
        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }
}





