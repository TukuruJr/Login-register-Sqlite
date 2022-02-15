package com.example.locallogin_registrationandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {
    String create;
    String Drop;
    ContentValues cv;
    Cursor cursor;

    public MyDataBase(Context context) {
        super(context, "Test.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase myDb) {
     myDb = this.getWritableDatabase();
     create = "CREATE TABLE IF NOT EXISTS Users" +
             " (Id integer primary key, Username text, Email text, Number VARCHAR, Password text)";
     myDb.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int i, int i1) {
    myDb = this.getWritableDatabase();
    Drop = "DROP TABLE Users";
    }


    //a method to call when Creating a new user
    public boolean CreateUser(String username,String email,String number,String password) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put("Username", username);
        cv.put("Email", email);
        cv.put("Number", number);
        cv.put("Password", password);

        //insert the values into your table
        Long insert = myDb.insert("Users", null, cv);
        if (insert == -1){
            return false;
        }
      return  true;
    }

  public boolean Login(String user, String pass){
       SQLiteDatabase myDb  = this.getReadableDatabase();
       String sql = "SELECT Username, Password FROM users WHERE Username = ? AND  Password = ?";
       cursor = myDb.rawQuery(sql,new String[]{user,pass});
       if (cursor.getCount()>0){
           return true;
       }else {
           return  false;
       }
  }


  public boolean CheckUser(String username){
        SQLiteDatabase myDb = this.getReadableDatabase();
        Cursor getUser = myDb.rawQuery("select *  from Users where Username = ?",new String[]{username});
        if (getUser.getCount()>0){
            return false;
        }
            return true;
  }
}
