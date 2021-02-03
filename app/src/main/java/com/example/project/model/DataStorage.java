package com.example.project.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DataStorage extends SQLiteOpenHelper {
    private static final String databaseName="DataBaseName.dp";
    public DataStorage( Context context) {
        super(context, databaseName, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table appData(imgUrl text,title text,author text ,state number,description text,primary key(imgUrl))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS appData ");
        onCreate(db);
    }
    public boolean insert(String imgUrl,String title,String author,int state,String description)
    {
        SQLiteDatabase dp=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("imgUrl",imgUrl);
        contentValues.put("title",title);
        contentValues.put("author",author);
        contentValues.put("state",state);
        contentValues.put("description",description);
        int val=(int)dp.insert("appData",null,contentValues);
        if(val==-1)
        {
            return false;
        }
        return true;
    }
    public ArrayList<Film> getData()
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from appData",null);
        ArrayList<Film>array=new ArrayList<>();
        while (cursor.moveToNext())
        {
            String imgUrl=cursor.getString(0);
            String title=cursor.getString(1);
            String author=cursor.getString(2);
            int state=cursor.getInt(3);
            String description=cursor.getString(4);
            array.add(new Film(imgUrl,title,author,description,true,6));
        }
        return array;
    }
    public boolean getData(String imgUri)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from appData",null);
        ArrayList<Film>array=new ArrayList<>();
        while (cursor.moveToNext())
        {

            if(imgUri.equals(cursor.getString(0)))
            {
                return true;
            }

        }
        return false;
    }
    public Integer remove(String imgUrl)
    {
        SQLiteDatabase db =this.getWritableDatabase() ;
        return db.delete("appData" , "imgUrl= ?" ,new String[]{imgUrl}) ;
    }

}

