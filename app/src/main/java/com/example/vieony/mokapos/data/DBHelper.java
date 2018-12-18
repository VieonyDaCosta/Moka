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
        db.execSQL(DbTableHelper.ITEM_TABLE_CREATE);
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
            values.put(DbTableHelper.ITEM_ID, item.id);
            values.put(DbTableHelper.ITEM_ALBUM_ID, item.albumId);
            values.put(DbTableHelper.ITEM_TITLE, item.title);
            values.put(DbTableHelper.ITEM_URL, item.url);
            values.put(DbTableHelper.ITEM_THUMBNAIL_URL, item.thumbnailUrl);
            db.insertWithOnConflict(DbTableHelper.TABLE_ITEM, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        // close db connection
        db.close();
    }

    public List<Item> getAllItems() {
        List<Item> photos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                DbTableHelper.TABLE_ITEM,
                new String[]{DbTableHelper.ITEM_ID,
                        DbTableHelper.ITEM_ALBUM_ID,
                        DbTableHelper.ITEM_TITLE,
                        DbTableHelper.ITEM_URL,
                        DbTableHelper.ITEM_THUMBNAIL_URL},
                "", null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item photo = new Item(
                        cursor.getInt(cursor.getColumnIndex(DbTableHelper.ITEM_ID)),
                        cursor.getInt(cursor.getColumnIndex(DbTableHelper.ITEM_ALBUM_ID)),
                        cursor.getString(cursor.getColumnIndex(DbTableHelper.ITEM_TITLE)),
                        cursor.getString(cursor.getColumnIndex(DbTableHelper.ITEM_URL)),
                        cursor.getString(cursor.getColumnIndex(DbTableHelper.ITEM_THUMBNAIL_URL)));

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
