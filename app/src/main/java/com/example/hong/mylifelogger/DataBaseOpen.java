package com.example.hong.mylifelogger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2016-11-26.
 */
public class DataBaseOpen extends SQLiteOpenHelper {
    public DataBaseOpen(Context context) {
        super(context, "t_table", null, 1);
    }

    // 최초 실행시 Data Base 한번만 생성
    @Override


    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE t_table"
                + "(id integer primary key autoincrement, "
                + "date TEXT, "
                + "time TEXT, "
                + "address TEXT, "
                + "latitude REAL, "
                + "longitude REAL, "
                + "type TEXT, "
                + "title TEXT, "
                + "detail TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS t_table");
        onCreate(db);
    }
}
