package com.main.zk.cashbook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.main.zk.cashbook.accounting.AccountingListInfo;

import java.util.ArrayList;

/**
 * Created by ZK on 2017/4/19.
 */

public class DBHelper{
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private volatile static DBHelper instance;

    private ArrayList<AccountingListInfo> accountingListInfoArrayList;

    public DBHelper(Context context){
        dbOpenHelper = new DBOpenHelper(context, DBOpenHelper.DB_NAME);
        SQLiteDatabase sqliteDatabase = dbOpenHelper.getWritableDatabase();
    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null)
                    instance = new DBHelper(context);
            }
        }
        return instance;
    }

    private boolean isOpen(){
        return sqLiteDatabase != null && sqLiteDatabase.isOpen();
    }

    private void open(){
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
    }

    private void close(){
        sqLiteDatabase.close();
    }

    private boolean execSQL(String sql){
        boolean result = false;
        if (sql != null && sqLiteDatabase != null
                && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()) {
            sqLiteDatabase.execSQL(sql);
            result = true;
        }
        return result;
    }

    public void insertExpenseRecord(String account, String recordTime, String usePlace, Integer usePlaceImageSrc,
                                       Integer usePlaceColor, Integer money, String capitalAccount, String user){
        if (!isOpen())
            open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Account", account);
        contentValues.put("RecordTime", recordTime);
        contentValues.put("UsePlace", usePlace);
        contentValues.put("UsePlaceImageSrc", usePlaceImageSrc);
        contentValues.put("UsePlaceColor", usePlaceColor);
        contentValues.put("Money", money);
        contentValues.put("CapitalAccount", capitalAccount);
        contentValues.put("User", user);
        sqLiteDatabase.insert(DBOpenHelper.TABLE_EXPENDITURE, null, contentValues);
        close();
    }

    public ArrayList<AccountingListInfo> queryExpenseRecord(){
        if (!isOpen())
            open();
        if (accountingListInfoArrayList == null)
            accountingListInfoArrayList = new ArrayList<>();
        else
            accountingListInfoArrayList.clear();
        Cursor cursor = sqLiteDatabase.query(DBOpenHelper.TABLE_EXPENDITURE, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            accountingListInfoArrayList.add(new AccountingListInfo(false, cursor.getInt(cursor.getColumnIndex("ID")),
                    null, -1, -1,
                    cursor.getInt(cursor.getColumnIndex("Money")), cursor.getString(cursor.getColumnIndex("UsePlace")),
                    cursor.getInt(cursor.getColumnIndex("UsePlaceImageSrc"))));
        }

        return accountingListInfoArrayList;
    }
}
