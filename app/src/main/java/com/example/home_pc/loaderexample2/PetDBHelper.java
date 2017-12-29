package com.example.home_pc.loaderexample2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class PetDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;

    public final static String TABLE_NAME = "pets";
    public final static String _ID = "_ID";
    public final static String COLUMN_PET_NAME = "name";
    public final static String COLUMN_PET_WEIGHT = "weight";

    public PetDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + TABLE_NAME  + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PET_NAME + " TEXT NOT NULL, "
                + COLUMN_PET_WEIGHT + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_PETS_TABLE);

        insertData(db, "Cat1", "20");
        insertData(db, "Cat2", "20");
        insertData(db, "Cat3", "20");
        insertData(db, "Cat4", "20");
        insertData(db, "Cat5", "20");

    }

    public static void insertData(SQLiteDatabase db, String name, String weight) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PET_NAME, name);
        contentValues.put(COLUMN_PET_WEIGHT, weight);
        db.insert(TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
