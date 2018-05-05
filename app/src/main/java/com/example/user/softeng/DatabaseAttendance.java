package com.example.user.softeng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAttendance extends SQLiteOpenHelper {


    public DatabaseAttendance(Context context){
        super(context,"Attendance.db",null, 1);
    }



    public void addUser (int id, String name, String username, String password){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues param = new ContentValues();
        param.put("ID", id);
        param.put("NAME", name);
        param.put("USERNAME", username);
        param.put("PASSWORD", password);
        db.insert("USER", null,param);
    }

    public void addStudent (int id, String name, String username, String password){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues param = new ContentValues();
        param.put("ID", id);
        param.put("NAME", name);
        param.put("USERNAME", username);
        param.put("PASSWORD", password);
        db.insert("USER", null,param);
    }


    public ArrayList<User> selectUsers(){
        ArrayList<User> res = new ArrayList<User>();
        SQLiteDatabase db =getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM USER",null);

        c.moveToFirst();
        while (c.isAfterLast() == false){

            int id =c.getInt(c.getColumnIndex("ID"));
            String name = c.getString(c.getColumnIndex("NAME"));
            String username = c.getString(c.getColumnIndex("USERNAME"));
            String password = c.getString(c.getColumnIndex("PASSWORD"));

            User user = new User(id,name,username,password);
            res.add(user);
            c.moveToNext();
        }
        return res;
    }

    //CHECKING EMAIL
    public Boolean chkUser(String username){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM USER WHERE USERNAME=?", new String[]{username});

        if(c.getCount()>0) return false;
        else return true;
    }

    //CHECKING EMAIL & PASSWORD
    public Boolean emailpass(String user, String pass){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER WHERE USERNAME=? AND PASSWORD=?", new String[]{user,pass});
        if(cursor.getCount()>0) return true;
        else return false;
    }



    public DatabaseAttendance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseAttendance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE USER (ID INTEGER, NAME TEXT, USERNAME TEXT, PASSWORD TEXT)");
        db.execSQL("CREATE TABLE CLASS (ID INTEGER, USER INTEGER ,SUBJECT TEXT, CODE TEXT, TIME TEXT, DRP INTEGER, NUMDAYS INTEGER)");
        db.execSQL("CREATE TABLE STUDENT (ID INTEGER, USER INTEGER,CLASS INTEGER,IDNUM INTEGER, NAME TEXT, PARENTNAME  TEXT, NUMBER TEXT)");
        db.execSQL("CREATE TABLE ATTENDANCE ( USER INTEGER, CLASS INTEGER , STUDENT INTEGER , DATE TEXT , REMARK TEXT, STATUS TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
