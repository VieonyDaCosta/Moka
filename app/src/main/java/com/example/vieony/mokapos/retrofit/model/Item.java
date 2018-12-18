package com.example.vieony.mokapos.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("albumId")
    public int albumId;

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("url")
    public String url;

    @SerializedName("thumbnailUrl")
    public String thumbnailUrl;
}
