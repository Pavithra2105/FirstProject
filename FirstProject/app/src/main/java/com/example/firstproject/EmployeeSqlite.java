package com.example.firstproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class EmployeeSqlite extends SQLiteOpenHelper {


    public final static String DATABASE_NAME = "Employee.db";
    public final static String TABLE_NAME = "employee_details";
    public final static String COL_1 = "ID";
    public final static String COL_2 = "NAME";
    public final static String COL_3 = "DEPARTMENT";
    public final static String COL_4 = "SALARY";

    public EmployeeSqlite(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DEPARTMENT TEXT,SALARY TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String depart, String salary) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, name);
        cv.put(COL_3, depart);
        cv.put(COL_4, salary);

        long result = database.insert(TABLE_NAME, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor readData(String empid) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID ='" + empid + "'";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    public boolean updateData(String id, String name, String department, String salary) {
     SQLiteDatabase database = this.getWritableDatabase();
     ContentValues cv= new ContentValues();
     cv.put(COL_1,id);
     cv.put(COL_2,name);
     cv.put(COL_3,department);
     cv.put(COL_4,salary);
     database.update(TABLE_NAME,cv,"ID= ?",new String[]{id});
     return true;
     }


    public Integer deleteData(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME,"ID=?",new String[]{id} );
    }

     public Cursor allData() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery(" SELECT * FROM " +TABLE_NAME,null);
        return res;
    }
    }



