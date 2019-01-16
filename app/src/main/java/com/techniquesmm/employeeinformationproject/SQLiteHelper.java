package com.techniquesmm.employeeinformationproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME ="db_empList";
    public static final String TABLE_NAME="EmpList";
    public static final String col_id ="id";
    public static final String col_1 ="Name";
    public static final String col_2 ="FatherName";
    public static final String col_3 ="DOB";
    public static final String col_4 ="Age";
    public static final String col_5="Address";
    public static final String col_6="PhNo";
    public static final String col_7="Salary";
    public static final String col_8="Depart";
    public static final String col_9="Qualification";
    public static final String col_10="Position";
    public static final String col_11="Email";


    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+ SQLiteHelper.col_id+" INTEGER PRIMARY KEY, "+ SQLiteHelper.col_1+" VARCHAR, "+ SQLiteHelper.col_2+" VARCHAR, "+ SQLiteHelper.col_3+" VARCHAR, "+ SQLiteHelper.col_4+" VARCHAR, "+ SQLiteHelper.col_5+" VARCHAR, "+ SQLiteHelper.col_6+" VARCHAR, "+ SQLiteHelper.col_7+" VARCHAR, "+ SQLiteHelper.col_8+" VARCHAR, "+ SQLiteHelper.col_9+" VARCHAR, "+ SQLiteHelper.col_10+" VARCHAR, "+ SQLiteHelper.col_11+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }



}
