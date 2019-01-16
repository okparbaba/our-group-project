package com.techniquesmm.employeeinformationproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMS extends AppCompatActivity {

    EditText etphonenumber, etsmsbody;
    Button btsmssend;

    private static final int PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        String smsPhon = getIntent().getStringExtra("SMSPHONE");
        Toast.makeText(SMS.this, "Successful "+smsPhon, Toast.LENGTH_LONG).show();
        etphonenumber = (EditText) findViewById(R.id.et_phonenumber);
        etsmsbody = (EditText) findViewById(R.id.et_smsbody);
        btsmssend = (Button) findViewById(R.id.bt_smssend);


        btsmssend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSmsPermission();
            }
        });



        etphonenumber.setText(smsPhon);
    }

    private void SendSMS() {

        String smsBody = etsmsbody.getText().toString();
        sendSms(etphonenumber.getText().toString(), smsBody);

    }

    private void sendSms(String mobileNo, String smsBody) {
        try {

            Toast.makeText(SMS.this, "Successful "+mobileNo, Toast.LENGTH_LONG).show();
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(mobileNo, null, smsBody, null, null);
            Toast.makeText(SMS.this, "Successful to Send", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(SMS.this, "Error", Toast.LENGTH_LONG).show();
        }
    }

    private void requestSmsPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST);

                } else {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST);
                }
            } else {
                SendSMS();
            }
        }else{
            SendSMS();
        }
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(SMS.this, "permissions granted", Toast.LENGTH_SHORT).show();
            SendSMS();
        } else {
            Toast.makeText(SMS.this, "permissions denied", Toast.LENGTH_SHORT).show();
        }
    }
}

