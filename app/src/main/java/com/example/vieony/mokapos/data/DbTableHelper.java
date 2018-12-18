package com.example.vieony.mokapos.data;

import com.example.vieony.mokapos.model.Item;

public class DbTableHelper {

    public static final String TABLE_ITEM = "items";

    public static final String ITEM_ID = "id";
    public static final String ITEM_ALBUM_ID = "albumId";
    public static final String ITEM_TITLE = "title";
    public static final String ITEM_URL = "url";
    public static final String ITEM_THUMBNAIL_URL = "thumbnailUrl";


    public static final String ITEM_TABLE_CREATE = "CREATE TABLE " + TABLE_ITEM + "("
            + ITEM_ID + " INTEGER PRIMARY KEY,"
            + ITEM_ALBUM_ID + " INTEGER,"
            + ITEM_TITLE + " TEXT,"
            + ITEM_URL + " TEXT,"
            + ITEM_THUMBNAIL_URL + " TEXT, "
            + " UNIQUE (" + ITEM_ID + ") ON CONFLICT REPLACE"
            + ")";


}
