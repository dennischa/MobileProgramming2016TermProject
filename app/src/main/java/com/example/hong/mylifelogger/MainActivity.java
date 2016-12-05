package com.example.hong.mylifelogger;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    String dateString;
    ArrayList<WalkData> walks;
    static WalkDataBaseOpen walkDataDataBaseOpen;
    static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        walkDataDataBaseOpen = new WalkDataBaseOpen(this);
        db = walkDataDataBaseOpen.getWritableDatabase();
        Log.d("메세지", "MainCreate()");
        Intent intent = new Intent(this, WalKService.class);
        startService(intent);

    }
    public void onClickWriteDaily(View v){
        Intent intent = new Intent(this, WriteDaily.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onPostResume();
        dateString = getDateString();
        readWalkTable();
        if(walks.size() == 0)
            insertWalkData(dateString, WalKService.walk);
        else{
            if (walks.get(walks.size() -1).getDateString() != dateString) {
                WalKService.walk = 0;
                insertWalkData(dateString, WalKService.walk);
            }
        }


    }

    protected String getDateString(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        return CurDateFormat.format(date);
    }

    public void readWalkTable() {
        walks = new ArrayList<WalkData>();
        String sql = "select * from walk_table";
        Cursor results = db.rawQuery(sql, null);
        results.moveToFirst();

        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String date = results.getString(1);
            double count = results.getDouble(2);
            walks.add(new WalkData(date,count));
            results.moveToNext();
        }
        results.close();
    }

    public void insertWalkData(String date, double count) {
        db.execSQL("INSERT INTO walk_table "
                + "VALUES(NULL, '" + date
                + "', '" + count
                + "');");
    }
}
