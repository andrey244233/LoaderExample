package com.example.home_pc.loaderexample2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;

import static com.example.home_pc.loaderexample2.PetDBHelper.COLUMN_PET_NAME;
import static com.example.home_pc.loaderexample2.PetDBHelper.TABLE_NAME;

class MyTask extends AsyncTaskLoader<ArrayList<String>> {

    Context mContext;
    SQLiteDatabase sqLiteDatabase;

    public MyTask(Context context, SQLiteDatabase sqLiteDatabase) {
        super(context);
        mContext = context;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public ArrayList<String> loadInBackground() {
        Log.v("Tag", "Load in backgroud");
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<String> names = new ArrayList<>();
        while (cursor.moveToNext()){
            int nameColumnId = cursor.getColumnIndex(COLUMN_PET_NAME );
            String name = cursor.getString(nameColumnId);
            names.add(name);
        }
        return names;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}