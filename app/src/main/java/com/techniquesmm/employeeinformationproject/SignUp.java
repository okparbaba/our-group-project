package com.techniquesmm.employeeinformationproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Pattern;



public class SignUp extends AppCompatActivity {

    EditText etslname,etsemail,etspassword,etscpassword;
    Button btnsignUp,btCancel;
    private RadioGroup radioGroup;
    private RadioButton radioGenderButton;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        etslname = (EditText) findViewById(R.id.et_lname);
        etsemail = (EditText) findViewById(R.id.et_semail);
        etspassword = (EditText) findViewById(R.id.et_spwd);
        etscpassword = (EditText) findViewById(R.id.et_sconfirmpwd);
        btnsignUp = (Button) findViewById(R.id.bt_signup);
        btCancel = (Button) findViewById(R.id.bt_cancelsu);

        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stslname = etslname.getText().toString();
                String stsemail = etsemail.getText().toString();
                String stspassword = etspassword.getText().toString();
                String stscpassword = etscpassword.getText().toString();
                if (TextUtils.isEmpty(stslname) || TextUtils.isEmpty(stsemail) || TextUtils.isEmpty(stspassword)){
                    Toast.makeText(SignUp.this,"Please fill all the fields.",Toast.LENGTH_SHORT).show();
                }else if (!stsemail.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                    Toast.makeText(SignUp.this,"Invalid Email",Toast.LENGTH_SHORT).show();
                }else if (stscpassword.length()<5 && !isValidPassword(stspassword)) {
                    Toast.makeText(SignUp.this, "At least six passwords", Toast.LENGTH_SHORT).show();
                }else{
                    loginProcress(stslname,stsemail,stspassword);
                }

            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this,HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        radioGroup = (RadioGroup) findViewById(R.id.rdg_gender);
        int selectedOption = radioGroup.getCheckedRadioButtonId();
        radioGenderButton = (RadioButton) findViewById(selectedOption);

    }

    private void loginProcress(String lname, String email, String password){
        SPreference.saveSignUp(SignUp.this, lname,email, password);
        Intent in = new Intent(SignUp.this, HomeActivity.class);
        startActivity(in);
        finish();
        Toast.makeText(SignUp.this,"Register is successful...", Toast.LENGTH_SHORT).show();
    }

    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN = Pattern.compile("[a-z-A-Z0-9\\!\\@\\#\\$]{8,24}");
        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }
}


