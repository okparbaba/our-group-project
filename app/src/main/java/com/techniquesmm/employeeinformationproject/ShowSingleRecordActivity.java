package com.techniquesmm.employeeinformationproject;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ShowSingleRecordActivity extends AppCompatActivity {

    String IDholder;
    TextView id, name,fathername,dob,age,addr, phone_number,salary,depart,quli,position,email;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    // Button Delete, Edit;
    SQLiteDatabase sqLiteDatabaseObj;
    AlertDialog.Builder alertDialogBuilder;
    String SQLiteDataBaseQueryHolder ;
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_record);

        id = (TextView) findViewById(R.id.textViewID);
        name = (TextView) findViewById(R.id.textViewName);
        fathername= (TextView) findViewById(R.id.textViewFName);
        dob = (TextView) findViewById(R.id.textViewDob);
        age = (TextView) findViewById(R.id.textViewAge);
        addr = (TextView) findViewById(R.id.textViewAddr);
        phone_number = (TextView) findViewById(R.id.textViewPhone);
        salary = (TextView) findViewById(R.id.textViewSalary);
        depart = (TextView) findViewById(R.id.textViewDepart);
        quli = (TextView) findViewById(R.id.textViewQuli);
        position = (TextView) findViewById(R.id.textViewCountry);
        email = (TextView) findViewById(R.id.textViewEmergency);

        sqLiteHelper = new SQLiteHelper(this);

        phone_number.setAutoLinkMask(Linkify.PHONE_NUMBERS);
        phone_number.setMovementMethod(LinkMovementMethod.getInstance());
        ShowSingleRecordInTextView();

    }

    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu2, menu);

        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case  R.id.phone:
                if(checkPermission(Manifest.permission.CALL_PHONE)){
                    String number =phone_number.getText().toString();
                    Uri call = Uri.parse("tel:" + number);
                    Intent surf = new Intent(Intent.ACTION_CALL,call);
                    Toast.makeText(ShowSingleRecordActivity.this,"direct call", Toast.LENGTH_SHORT).show();
                    startActivity(surf);
                }else{
                    Toast.makeText(ShowSingleRecordActivity.this,"Permission Call Phone denied",
                            Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(ShowSingleRecordActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},MAKE_CALL_PERMISSION_REQUEST_CODE);
                }

                return true;
            case  R.id.mail:

                String strphone = phone_number.getText().toString();
                Toast.makeText(ShowSingleRecordActivity.this,strphone, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShowSingleRecordActivity.this,SMS.class);
                intent.putExtra("SMSPHONE",strphone);
                startActivity(intent);
                return true;
            case  R.id.edit:
                editActivity();
                return true;
            case  R.id.delete:
                deleteActivity();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    public  void editActivity(){
        Intent intent = new Intent(getApplicationContext(),EditSingleRecordActivity.class);

        intent.putExtra("EditID", IDholder);

        startActivity(intent);
    }
    public  void deleteActivity(){

        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Message");
        alertDialogBuilder.setMessage("Sure,Do you want to Delete Data!");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(ShowSingleRecordActivity.this,"Delete Data", Toast.LENGTH_LONG).show();

                        OpenSQLiteDataBase();

                        SQLiteDataBaseQueryHolder = "DELETE FROM "+ SQLiteHelper.TABLE_NAME+" WHERE id = "+IDholder+"";

                        sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);

                        sqLiteDatabase.close();

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




    @Override
    protected void onResume() {

        ShowSingleRecordInTextView();

        super.onResume();
    }

    public void ShowSingleRecordInTextView() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        IDholder = getIntent().getStringExtra("ListViewClickedItemValue");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                id.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_id)));
                name.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_1)));
                fathername.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_2)));
                dob.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_3)));
                age.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_4)));
                addr.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_5)));
                phone_number.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_6)));
                //sms_phnum = cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_6));
                salary.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_7)));
                depart.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_8)));
                quli.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_9)));
                position.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_10)));
                email.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_11)));
                // Toast.makeText(ShowSingleRecordActivity.this,cursor.getString(cursor.getColumnIndex(SQLiteHelper.col_3)),Toast.LENGTH_SHORT).show();

            }
            while (cursor.moveToNext());

            cursor.close();

        }
    }

    public void OpenSQLiteDataBase(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    private boolean checkPermission(String permission){
        return ContextCompat.checkSelfPermission(this,permission)== PackageManager.PERMISSION_GRANTED;
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
            case MAKE_CALL_PERMISSION_REQUEST_CODE:
                if(grantResults.length>0 && (grantResults[0]== PackageManager.PERMISSION_GRANTED)){
                    Toast.makeText(this,"You can call the number by clicking on button", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

}

