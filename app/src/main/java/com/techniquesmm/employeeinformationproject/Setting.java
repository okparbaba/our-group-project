package com.techniquesmm.employeeinformationproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;



public class Setting extends AppCompatActivity {
    Button imgchangepwd, imgdeleteacc;
    Button btlogout;

    AlertDialog.Builder alertDialogBuilder;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        imgchangepwd = (Button) findViewById(R.id.bt_changepwd);
        imgdeleteacc = (Button) findViewById(R.id.bt_deleteacc);

        imgchangepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, ChangePwd.class);
                startActivity(intent);

            }
        });
        imgdeleteacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlertDialogDeleteAcc();
            }
        });

        btlogout  = (Button) findViewById(R.id.bt_logout);
        btlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlertDialogLogOut();
            }
        });
    }

    public void openAlertDialogDeleteAcc(){

        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Message");
        alertDialogBuilder.setMessage("Sure,Do you want to Delete Account!");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(Setting.this,"Delete Account", Toast.LENGTH_LONG).show();
                        SPreference.removeRegister(Setting.this);
                        Intent in = new Intent(Setting.this, HomeActivity.class);
                        startActivity(in);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    public void openAlertDialogLogOut(){
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Message");
        alertDialogBuilder.setMessage("Sure,Do you want to Logout!");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(Setting.this,"Logout", Toast.LENGTH_LONG).show();
                        //   SPreference.getSignUp(Setting.this);
                        Intent in = new Intent(Setting.this, HomeActivity.class);
                        startActivity(in);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}




