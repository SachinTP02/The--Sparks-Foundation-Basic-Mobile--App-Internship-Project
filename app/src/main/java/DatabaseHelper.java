package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(8585785815,'Sachin',289472.00,'nitesh@gmail.com','XXXXXXXXXXXX1234','SBI0095')");
        db.execSQL("insert into user_table values(9875868521,'Nithesh',582582.67,'nikita@gmail.com','XXXXXXXXXXXXX8956','ICI12678')");
        db.execSQL("insert into user_table values(9782561478,'Pranav',3691359.56,'pranav@gmail.com','XXXXXXXXXXXX9874','CAN8646')");
        db.execSQL("insert into user_table values(9784368521,'Shrada',251500.01,'shristi@gmail.com','XXXXXXXXXXXX6985','SBI0973')");
        db.execSQL("insert into user_table values(9568741238,'Kriti',102603.48,'kriti@gmail.com','XXXXXXXXXXXX1235','ICI12679')");
        db.execSQL("insert into user_table values(9369874512,'Prakash',89945.16,'prakash@gmail.com','XXXXXXXXXXXX4589','SBI9087')");
        db.execSQL("insert into user_table values(9728546821,'Devanshu',145936.00,'devanshu@gmail.com','XXXXXXXXXXXX5369','CAN6745')");
        db.execSQL("insert into user_table values(8795237459,'Ankita',89857.22,'ankita@gmail.com','XXXXXXXXXXXX8957','SBI1298')");
        db.execSQL("insert into user_table values(8971236547,'Shweta',564398.46,'shweta@gmail.com','XXXXXXXXXXXX1397','ICI1279')");
        db.execSQL("insert into user_table values(7845893145,'Priyanka',78273.90,'priyanka@gmail.com','XXXXXXXXXXXX5493','SBI1234')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
