package com.techniquesmm.employeeinformationproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    public static int SPLASH_TIME_OUT = 500;
    ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar =(ProgressBar) findViewById(R.id.pb_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

    private void doWork () {
        for (int progress = 0; progress <100; progress+=10) {
            try {
                Thread.sleep(100);
                progressBar.setProgress(progress);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void startApp() {
        Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }
}



