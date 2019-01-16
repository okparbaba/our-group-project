package com.techniquesmm.employeeinformationproject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabaseObj;
    EditText ename,efathername,edob,eage,eaddr,ephone,esalary,edepart,equli,eposition,eemail ;
    String NameHolder,FatherNameHolder,DobHolder,AgeHolder,AddrHolder,PhoneHolder,SalaryHolder,DepartHolder,
            QuliHolder,PositionHolder,EmailHolder,SQLiteDataBaseQueryHolder;
    Button btncancel,btnsave;
    Boolean EditTextEmptyHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        btncancel = (Button)findViewById(R.id.bt_cancel);
        btnsave = (Button)findViewById(R.id.bt_save);
        ename = (EditText)findViewById(R.id.et_edit_name);
        efathername = (EditText)findViewById(R.id.et_edit_fathername);
        edob = (EditText)findViewById(R.id.et_edit_dob);
        eage = (EditText)findViewById(R.id.et_edit_age);
        eaddr = (EditText)findViewById(R.id.et_edit_address);
        ephone = (EditText)findViewById(R.id.et_edit_phone);
        esalary = (EditText)findViewById(R.id.et_edit_salary);
        edepart = (EditText)findViewById(R.id.et_edit_dept);
        equli = (EditText)findViewById(R.id.et_edit_qulification);
        eposition = (EditText)findViewById(R.id.et_edit_position);
        eemail = (EditText)findViewById(R.id.et_edit_email);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                CheckEditTextStatus();
                InsertDataIntoSQLiteDatabase();
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* ename.setText("");
                efathername.setText("");
                edob.setText("");
                eage.setText("");
                eaddr.setText("");
                ephone.setText("");
                esalary.setText("");
                edepart.setText("");
                equli.setText("");
                eposition.setText("");
                eemail.setText("");
                finish();*/
               EmptyEditTextAfterDataInsert();
            }
        });


    }

    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild(){

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS "+ SQLiteHelper.TABLE_NAME+"("+ SQLiteHelper.col_id+" PRIMARY KEY AUTOINCREMENT NOT NULL, "+ SQLiteHelper.col_1+" VARCHAR, "+ SQLiteHelper.col_2+" VARCHAR, "+ SQLiteHelper.col_3+" VARCHAR, "+ SQLiteHelper.col_4+" VARCHAR, "+ SQLiteHelper.col_5+" VARCHAR, "+ SQLiteHelper.col_6+" VARCHAR, "+ SQLiteHelper.col_7+" VARCHAR, "+ SQLiteHelper.col_8+" VARCHAR, "+ SQLiteHelper.col_9+" VARCHAR, "+ SQLiteHelper.col_10+" VARCHAR, "+ SQLiteHelper.col_11+" VARCHAR);");

    }

    public void CheckEditTextStatus(){

        NameHolder = ename.getText().toString() ;
        FatherNameHolder = efathername.getText().toString() ;
        DobHolder = edob.getText().toString() ;
        AgeHolder = eage.getText().toString() ;
        AddrHolder = eaddr.getText().toString() ;
        PhoneHolder = ephone.getText().toString() ;
        SalaryHolder = esalary.getText().toString() ;
        DepartHolder = edepart.getText().toString() ;
        QuliHolder = equli.getText().toString() ;
        PositionHolder = eposition.getText().toString() ;
        EmailHolder = eemail.getText().toString() ;

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(FatherNameHolder)
                || TextUtils.isEmpty(DobHolder) || TextUtils.isEmpty(AgeHolder)
                || TextUtils.isEmpty(AddrHolder) || TextUtils.isEmpty(PhoneHolder)
                || TextUtils.isEmpty(SalaryHolder) || TextUtils.isEmpty(DepartHolder)
                || TextUtils.isEmpty(QuliHolder) || TextUtils.isEmpty(PositionHolder)
                || TextUtils.isEmpty(EmailHolder)){

            EditTextEmptyHold = false ;

        }
        else {

            EditTextEmptyHold = true ;
        }
    }

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHold == true)
        {
            if (!EmailHolder.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                Toast.makeText(InsertActivity.this,"Invalid Email",Toast.LENGTH_SHORT).show();
            }else {

                SQLiteDataBaseQueryHolder = "INSERT INTO " + SQLiteHelper.TABLE_NAME + " (Name,FatherName,DOB,Age,Address,PhNo,Salary,Depart,Qualification,Position,Email) VALUES('" + NameHolder + "', '" + FatherNameHolder + "', '" + DobHolder + "', '" + AgeHolder + "', '" + AddrHolder + "', '" + PhoneHolder + "', '" + SalaryHolder + "', '" + DepartHolder + "', '" + QuliHolder + "', '" + PositionHolder + "', '" + EmailHolder + "');";

                sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

                sqLiteDatabaseObj.close();

                Toast.makeText(InsertActivity.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
            }

        }
        else {

            Toast.makeText(InsertActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }

    public void EmptyEditTextAfterDataInsert(){

        ename.getText().clear();
        efathername.getText().clear();
        edob.getText().clear();
        eage.getText().clear();
        eaddr.getText().clear();
        ephone.getText().clear();
        esalary.getText().clear();
        edepart.getText().clear();
        equli.getText().clear();
        eposition.getText().clear();
        eemail.getText().clear();


    }
}

