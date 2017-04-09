package com.main.zk.cashbook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ZK on 2017/3/31.
 */

public class test {

    public void instablish(Context context){
        // 创建SQLiteOpenHelper子类对象
        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(context,"test_carson");
        //数据库实际上是没有被创建或者打开的，直到getWritableDatabase() 或者 getReadableDatabase() 方法中的一个被调用时才会进行创建或者打开
        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
        // SQLiteDatabase  sqliteDatabase = dbHelper.getReadbleDatabase();
    }

    public void upgrade(Context context){
        // 创建SQLiteOpenHelper子类对象
        MySQLiteOpenHelper dbHelper_upgrade = new MySQLiteOpenHelper(context,"test_carson",2);
        // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
        SQLiteDatabase  sqliteDatabase_upgrade = dbHelper_upgrade.getWritableDatabase();
        // SQLiteDatabase  sqliteDatabase = dbHelper.getReadbleDatabase();
    }

    public void insert(Context context){
        System.out.println("插入数据");

        // 创建SQLiteOpenHelper子类对象
        ////注意，一定要传入最新的数据库版本号
        MySQLiteOpenHelper dbHelper1 = new MySQLiteOpenHelper(context,"test_carson",2);
        // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
        SQLiteDatabase  sqliteDatabase1 = dbHelper1.getWritableDatabase();

        // 创建ContentValues对象
        ContentValues values1 = new ContentValues();

        // 向该对象中插入键值对
        values1.put("id", 1);
        values1.put("name", "carson");

        // 调用insert()方法将数据插入到数据库当中
        sqliteDatabase1.insert("user", null, values1);

        // sqliteDatabase.execSQL("insert into user (id,name) values (1,'carson')");

        //关闭数据库
        sqliteDatabase1.close();
    }

    public void query(Context context){
        System.out.println("查询数据");

        // 创建DatabaseHelper对象
        MySQLiteOpenHelper dbHelper4 = new MySQLiteOpenHelper(context,"test_carson",2);

        // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
        SQLiteDatabase sqliteDatabase4 = dbHelper4.getReadableDatabase();

        // 调用SQLiteDatabase对象的query方法进行查询
        // 返回一个Cursor对象：由数据库查询返回的结果集对象
        Cursor cursor = sqliteDatabase4.query("user", new String[] { "id",
                "name" }, "id=?", new String[] { "1" }, null, null, null);


        String id = null;
        String name = null;

        //将光标移动到下一行，从而判断该结果集是否还有下一条数据
        //如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            id = cursor.getString(cursor.getColumnIndex("id"));
            name = cursor.getString(cursor.getColumnIndex("name"));
            //输出查询结果
            System.out.println("查询到的数据是:"+"id: "+id+"  "+"name: "+name);

        }
        //关闭数据库
        sqliteDatabase4.close();
    }

    public void modify(Context context){
        System.out.println("修改数据");

        // 创建一个DatabaseHelper对象
        // 将数据库的版本升级为2
        // 传入版本号为2，大于旧版本（1），所以会调用onUpgrade()升级数据库
        MySQLiteOpenHelper dbHelper2 = new MySQLiteOpenHelper(context,"test_carson", 2);


        // 调用getWritableDatabase()得到一个可写的SQLiteDatabase对象
        SQLiteDatabase sqliteDatabase2 = dbHelper2.getWritableDatabase();

        // 创建一个ContentValues对象
        ContentValues values2 = new ContentValues();
        values2.put("name", "zhangsan");

        // 调用update方法修改数据库
        sqliteDatabase2.update("user", values2, "id=?", new String[]{"1"});

        //关闭数据库
        sqliteDatabase2.close();
    }

    public void delete(Context context){
        System.out.println("删除数据");

        // 创建DatabaseHelper对象
        MySQLiteOpenHelper dbHelper3 = new MySQLiteOpenHelper(context,"test_carson",2);

        // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
        SQLiteDatabase sqliteDatabase3 = dbHelper3.getWritableDatabase();

        //删除数据
        sqliteDatabase3.delete("user", "id=?", new String[]{"1"});

        //关闭数据库
        sqliteDatabase3.close();
    }

    public void delete_database(Context context){
        System.out.println("删除数据库");

        MySQLiteOpenHelper dbHelper5 = new MySQLiteOpenHelper(context,
                "test_carson",2);

        // 调用getReadableDatabase()方法创建或打开一个可以读的数据库
        SQLiteDatabase sqliteDatabase5 = dbHelper5.getReadableDatabase();

        //删除名为test.db数据库
//        deleteDatabase("test_carson");
    }


}
