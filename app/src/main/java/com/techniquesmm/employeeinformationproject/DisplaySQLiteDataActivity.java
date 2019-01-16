package com.techniquesmm.employeeinformationproject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplaySQLiteDataActivity extends AppCompatActivity {
    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter;
    ListView LISTVIEW;
    AlertDialog.Builder alertDialogBuilder;
    private long lastpressedtime;
    private static final int PERIOD = 2000;

    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array;
    ArrayList<String> PHONE_NUMBER_Array;

    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();
    String TempHolder;
    EditText edsearchVal;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sqlite_data);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        edsearchVal = (EditText) findViewById(R.id.search);
        ID_Array = new ArrayList<String>();
        NAME_Array = new ArrayList<String>();
        PHONE_NUMBER_Array = new ArrayList<String>();
        sqLiteHelper = new SQLiteHelper(this);

        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), ShowSingleRecordActivity.class);
                intent.putExtra("ListViewClickedItemValue", ListViewClickItemArray.get(position).toString());
                startActivity(intent);

            }
        });

        edsearchVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strval = edsearchVal.getText().toString();
                SearchSQLiteDBdata(strval);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onResume() {
        ShowSQLiteDBdata();
        super.onResume();
    }

    private void SearchSQLiteDBdata(String name) {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE Name LIKE '" +name+"%' order by id ASC", null);
        ID_Array.clear();
        NAME_Array.clear();
        PHONE_NUMBER_Array.clear();
        if (cursor.moveToFirst()) {
            do {
                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_id)));
                //Inserting Column ID into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_id)));
                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_1)));
                PHONE_NUMBER_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_6)));
            } while (cursor.moveToNext());
        }else{
            Toast.makeText(DisplaySQLiteDataActivity.this,"Data not found! ", Toast.LENGTH_SHORT).show();
        }
        listAdapter = new ListAdapter(DisplaySQLiteDataActivity.this, ID_Array, NAME_Array, PHONE_NUMBER_Array);
        LISTVIEW.setAdapter(listAdapter);
        cursor.close();

    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + "", null);

        ID_Array.clear();
        NAME_Array.clear();
        PHONE_NUMBER_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_id)));

                //Inserting Column ID into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_id)));

                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_1)));

                PHONE_NUMBER_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_6)));


            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(DisplaySQLiteDataActivity.this, ID_Array, NAME_Array
                , PHONE_NUMBER_Array);
        {


            LISTVIEW.setAdapter(listAdapter);

            cursor.close();
        }
    }

    @SuppressLint("RestrictedApi")
    public  boolean onCreateOptionsMenu(Menu menu) {

        if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }

        getMenuInflater().inflate(R.menu.menu1, menu);

        return  true;



    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case  R.id.action_add:
                addActivity();
                return true;
            case  R.id.action_setting:
                settingActivity();
                return true;
            case  R.id.action_about:
                Intent intent = new Intent(this,About.class);
                startActivity(intent);
                return true;
            case R.id.action_exit:
                exitActivity();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }


    public void addActivity() {
        Intent intent = new Intent(this,InsertActivity.class);
        startActivity(intent);
    }
    public  void settingActivity(){
        Intent intent = new Intent(this,Setting.class);
        startActivity(intent);
    }

    public  void exitActivity(){
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Message");
        alertDialogBuilder.setMessage("Sure,Do you want to exit!");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(DisplaySQLiteDataActivity.this,"Exit", Toast.LENGTH_LONG).show();
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (event.getDownTime() - lastpressedtime < PERIOD) {
                        finish();
                    } else {
                        String str = "Press on more time to exit";
                        Toast.makeText(DisplaySQLiteDataActivity.this, str, Toast.LENGTH_LONG).show();
                        lastpressedtime = event.getEventTime();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode,event);
    }

}