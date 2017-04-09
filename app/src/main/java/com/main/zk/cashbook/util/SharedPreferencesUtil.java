package com.main.zk.cashbook.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by ZK on 2017/3/11.
 */

public class SharedPreferencesUtil {
    private SharedPreferences sp;
    private volatile static SharedPreferencesUtil instance;

    public SharedPreferencesUtil(Context context){
        sp = context.getSharedPreferences(Key.CashBook.name(), Context.MODE_PRIVATE);
    }

    public static SharedPreferencesUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (instance == null)
                    instance = new SharedPreferencesUtil(context);
            }
        }
        return instance;
    }

    public boolean getBoolean (String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public int getInt (String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public String getString (String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public void applyValue(Record record) {
        if (record == null) return;
        SharedPreferences.Editor editor = sp.edit();
        dataFilling(editor, record);
        editor.apply();
    }

    public void applyValue(ArrayList<Record> records) {
        if (records == null || records.size() <= 0)
            return;
        SharedPreferences.Editor editor = sp.edit();
        dataFilling(editor, records);
        editor.apply();
    }

    private void dataFilling(SharedPreferences.Editor editor, ArrayList<Record> records) {
        if (editor == null)
            return;
        Record recordTemp;
        for (int i = 0; i < records.size(); i++) {
            recordTemp = records.get(i);
            dataFilling(editor, recordTemp);
        }
    }

    private void dataFilling(SharedPreferences.Editor editor, Record record) {
        if (editor == null) return;
        switch (record.getType()) {
            case type_int:
                editor.putInt(record.key, record.intValue);
                break;
            case type_boolean:
                editor.putBoolean(record.key, record.booleanValue);
                break;
            case type_String:
                editor.putString(record.key, record.stringValue);
                break;
            default:
                break;
        }
    }

    public static class Record {
        private String key;
        private int intValue;
        private boolean booleanValue;
        private String stringValue;
        private Type type;

        public Record(String key){
            this.key = key;
        }

        public Record(String key, int intValue){
            this(key);
            this.intValue = intValue;
            this.type = Type.type_int;
        }

        public Record(String key, boolean booleanValue){
            this(key);
            this.booleanValue = booleanValue;
            this.type = Type.type_boolean;
        }

        public Record(String key, String stringValue){
            this(key);
            this.stringValue = stringValue;
            this.type = Type.type_String;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getIntValue() {
            return intValue;
        }

        public void setIntValue(int intValue) {
            this.intValue = intValue;
        }

        public boolean isBooleanValue() {
            return booleanValue;
        }

        public void setBooleanValue(boolean booleanValue) {
            this.booleanValue = booleanValue;
        }

        public String getStringValue() {
            return stringValue;
        }

        public void setStringValue(String stringValue) {
            this.stringValue = stringValue;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public Type getType(){
            return this.type;
        }

        enum Type {
            type_int,
            type_boolean,
            type_String,
        }
    }

    public enum Key{
        CashBook,
        UserName,
        Password,
        IsRegistered,
        Email,
        Is;

    }

}
