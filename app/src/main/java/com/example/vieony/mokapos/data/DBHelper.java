package com.example.vieony.mokapos.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.vieony.mokapos.model.Item;
import com.example.vieony.mokapos.di.qualifier.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "moka_db";


    @Inject
    public DBHelper(@ApplicationContext Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create item  table
        db.execSQL("CREATE TABLE " + Item.TABLE_NAME + "("
                + Item.COLUMN_ID + " INTEGER PRIMARY KEY,"
                + Item.COLUMN_ALBUM_ID + " INTEGER,"
                + Item.COLUMN_TITLE + " TEXT,"
                + Item.COLUMN_URL + " TEXT,"
                + Item.COLUMN_THUMBNAIL_URL + " TEXT, "
                + " UNIQUE (" + Item.COLUMN_ID + ") ON CONFLICT REPLACE"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertItem(List<com.example.vieony.mokapos.retrofit.model.Item> list) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        ContentValues values = new ContentValues();
        for (com.example.vieony.mokapos.retrofit.model.Item item : list) {
            values.put(Item.COLUMN_ID, item.id);
            values.put(Item.COLUMN_ALBUM_ID, item.albumId);
            values.put(Item.COLUMN_TITLE, item.title);
            values.put(Item.COLUMN_URL, item.url);
            values.put(Item.COLUMN_THUMBNAIL_URL, item.thumbnailUrl);
            db.insert(Item.TABLE_NAME, null, values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        // close db connection
        db.close();
    }

    public List<Item> getAllItems() {
        List<Item> photos = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(Item.TABLE_NAME, new String[]{Item.COLUMN_ID, Item.COLUMN_ALBUM_ID, Item.COLUMN_TITLE, Item.COLUMN_URL, Item.COLUMN_THUMBNAIL_URL},
                "", null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item photo = new Item(
                        cursor.getInt(cursor.getColumnIndex(Item.COLUMN_ID)),
                        cursor.getInt(cursor.getColumnIndex(Item.COLUMN_ALBUM_ID)),
                        cursor.getString(cursor.getColumnIndex(Item.COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(Item.COLUMN_URL)),
                        cursor.getString(cursor.getColumnIndex(Item.COLUMN_THUMBNAIL_URL)));

                photos.add(photo);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // close db connection
        db.close();

        // return notes list
        return photos;
    }
}
