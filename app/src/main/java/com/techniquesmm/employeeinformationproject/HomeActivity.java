package com.techniquesmm.employeeinformationproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;



public class HomeActivity extends AppCompatActivity {

    EditText etlname,etlpasword;
    Button btllogin,btlcancel,btlsignup;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        etlname = (EditText) findViewById(R.id.et_lname);
        etlpasword = (EditText) findViewById(R.id.et_lpasword);

        btllogin = (Button) findViewById(R.id.bt_llogin);
        btlcancel = (Button) findViewById(R.id.bt_lcancel);
        btlsignup = (Button) findViewById(R.id.bt_lsignup) ;

        btllogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strname = etlname.getText().toString();
                String strpassword = etlpasword.getText().toString();
                if (strpassword.length()<5 && !isValidPassword(strpassword)){
                    Toast.makeText(HomeActivity.this,"At leasts six passwords",Toast.LENGTH_SHORT).show();
                }else {
                    loginProcess(strname, strpassword);
                }

            }
        });
        btlcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etlname.setText("");
                etlpasword.setText("");
            }
        });

        btlsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void loginProcess(String name, String password){
        String[] data = SPreference.getSignUp(HomeActivity.this);
        String lname = data[0];
        String lemail=data[1];
        String pwd = data[2];
        String conpwd = data[3];


        if(validate() == false){
            Toast.makeText(HomeActivity.this,"Warning,Email and Password is null", Toast.LENGTH_SHORT).show();
        }else if(lname.equals(name) && pwd.equals(password)){
            Intent in = new Intent(HomeActivity.this, DisplaySQLiteDataActivity.class);
            in.putExtra("name",lname);
            in.putExtra("email",lemail);
            in.putExtra("password",pwd);
            startActivityForResult(in,1);
            finish();
        }else{
            Toast.makeText(HomeActivity.this,"Warning,Email and Password is not match", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate(){
        if(etlname.getText().toString().trim().equals(""))
            return false;
        else if(etlpasword.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }

    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN = Pattern.compile("[a-z-A-Z0-9\\!\\@\\#\\$]{8,24}");
        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }

}


