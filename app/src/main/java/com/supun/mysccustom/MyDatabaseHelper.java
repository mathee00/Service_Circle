package com.supun.mysccustom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

//Created by S.M.Suriyaarachchi
//IT20187514

//This java class represents the SQLite database scheme
public class MyDatabaseHelper extends SQLiteOpenHelper{

    //Declaring variables
    private Context context;
    private static final String DATABASE_NAME="ServiceCircle.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="worker";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME="worker_name";
    private static final String COLUMN_DESCRIPTION="worker_description";
    private static final String COLUMN_SKILL="worker_skill";
    private static final String COLUMN_RATING="worker_rating";

    //Constructor for the MyDatabaseHelper class
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //Implementing the onCreate method
    @Override
    public void onCreate(SQLiteDatabase db) {

        //SQL statement for creating table
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_SKILL + " TEXT, " +
                COLUMN_RATING + " TEXT); ";

        //executing the SQL query
        db.execSQL(query);
    }

    //Implementing the onUpgrade method
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addWorker(String name, String description, String skill, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_SKILL, skill);
        cv.put(COLUMN_RATING, rating);
        long result = db.insert(TABLE_NAME,null,cv);
        if (result == -1){
            Toast.makeText(context,"Oops! something went wrong on DB insert ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Worker added Successfully!!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    Cursor readWorkerData(int workerID){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = " + workerID +" ; ";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateWorker(String workerID, String name, String description, String skill, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_SKILL, skill);
        cv.put(COLUMN_RATING, rating);
        long result = db.update(TABLE_NAME, cv, " _id=? ", new String[]{workerID});
        if (result == -1){
            Toast.makeText(context,"Oops! something went wrong on the DB update. Try again ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Worker updated Successfully!!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteWorker(String workerID){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, " _id=? ", new String[]{workerID});
        if (result == -1){
            Toast.makeText(context,"Oops! something went wrong on the DB update ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Worker deleted Successfully!!", Toast.LENGTH_SHORT).show();
        }
    }


}
