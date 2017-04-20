package com.main.zk.cashbook.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZK on 2017/4/20.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "CashBook";
    public static final String TABLE_EXPENDITURE = "Expenditure";
    public static final Integer Version = 1;

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DBOpenHelper(Context context, String name, int version)
    {
        this(context,name,null,version);
    }


    public DBOpenHelper(Context context, String name)
    {
        this(context, name, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql =
                "CREATE TABLE " + TABLE_EXPENDITURE + " (" +"ID INTEGER PRIMARY KEY," + "Account VARCHAR," +
                        "RecordTime VARCHAR," + "UsePlace VARCHAR," + "UsePlaceImageSrc INTEGER," + "UsePlaceColor INTEGER," +
                        "Money INTEGER," + "CapitalAccount VARCHAR," + "User VARCHAR" + " )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
