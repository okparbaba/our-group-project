package com.techniquesmm.employeeinformationproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditSingleRecordActivity extends AppCompatActivity {

    EditText eaddr,ephone,esalary,edepart,equli,epostion,eemail;
    Button update;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String IDholder;
    String SQLiteDataBaseQueryHolder ;
    SQLiteDatabase sqLiteDatabaseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_edit_single_record);

        eaddr = (EditText) findViewById(R.id.et_address);
        ephone = (EditText) findViewById(R.id.et_phone);
        esalary = (EditText) findViewById(R.id.et_salary);
        edepart = (EditText) findViewById(R.id.et_depart);
        equli = (EditText) findViewById(R.id.et_quli);
        epostion = (EditText) findViewById(R.id.et_postion);
        eemail = (EditText) findViewById(R.id.et_email);


        update = (Button) findViewById(R.id.buttonUpdate);

        sqLiteHelper = new SQLiteHelper(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String GetAddr = eaddr.getText().toString();
                String GetPhone = ephone.getText().toString();
                String GetSalary = esalary.getText().toString();
                String GetDepart = edepart.getText().toString();
                String GetQuli = equli.getText().toString();
                String GetPostion = epostion.getText().toString();
                String GetEmail = eemail.getText().toString();

                OpenSQLiteDataBase();
                if (!GetEmail.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                    Toast.makeText(EditSingleRecordActivity.this,"Invalid Email",Toast.LENGTH_SHORT).show();
                }

                SQLiteDataBaseQueryHolder = "UPDATE " + SQLiteHelper.TABLE_NAME + " SET "+SQLiteHelper.col_5+" = '"+GetAddr+"' , "+SQLiteHelper.col_6+" = '"+GetPhone+"' , "+SQLiteHelper.col_7+" = '"+GetSalary+"' , "+SQLiteHelper.col_8+" = '"+GetDepart+"' , "+SQLiteHelper.col_9+" = '"+GetQuli+"' , "+SQLiteHelper.col_10+" = '"+GetPostion+"' , "+SQLiteHelper.col_11+" = '"+GetEmail+"' WHERE id = " + IDholder + "";

                sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

                sqLiteDatabase.close();
                Toast.makeText(EditSingleRecordActivity.this,"Data Edit Successfully", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {

        ShowSRecordInEditText();

        super.onResume();
    }

    public void ShowSRecordInEditText() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        IDholder = getIntent().getStringExtra("EditID");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                eaddr.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_5)));
                ephone.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_6)));
                esalary.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_7)));
                edepart.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_8)));
                equli.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_9)));
                epostion.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_10)));
                eemail.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_11)));
            }
            while (cursor.moveToNext());

            cursor.close();

        }
    }

    public void OpenSQLiteDataBase(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

}
