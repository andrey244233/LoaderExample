package com.example.home_pc.loaderexample2;


import android.content.ContentValues;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import static com.example.home_pc.loaderexample2.PetDBHelper.COLUMN_PET_NAME;
import static com.example.home_pc.loaderexample2.PetDBHelper.COLUMN_PET_WEIGHT;
import static com.example.home_pc.loaderexample2.PetDBHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<String>> {


    PetDBHelper petDBHelper;
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        petDBHelper = new PetDBHelper(this);
        sqLiteDatabase = petDBHelper.getReadableDatabase();
        listView = findViewById(R.id.list_view);
        getSupportLoaderManager().initLoader(1, null, this);
    }


    @Override
    public android.support.v4.content.Loader<ArrayList<String>> onCreateLoader(int id, Bundle args) {
        return new MyTask(this, sqLiteDatabase);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<ArrayList<String>> loader, ArrayList<String> data) {
        Log.v("Tag", "deliver result");
        mData = data;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData);
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<ArrayList<String>> loader) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addItem:
                addItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addItem() {
        sqLiteDatabase = petDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PET_NAME, "something");
        contentValues.put(COLUMN_PET_WEIGHT, "22");
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        mData.add("something");
        adapter.notifyDataSetChanged();
    }

}





















