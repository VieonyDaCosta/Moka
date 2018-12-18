package com.example.vieony.mokapos.model;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.util.Random;

public class Item implements Serializable {

    private int id;
    private int albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
    private int price;

    public Item(int id, int albumId, String title, String url, String thumbnailUrl){
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        Random random = new Random();
        int randomMultiplier = 10+random.nextInt(89);
        price = id * randomMultiplier;

    }

    public String getTitle(){
        return title;
    }

    public String getThumbnailUrl(){
        return thumbnailUrl;
    }

    public double price(){
        return price;
    }

    @SuppressLint("DefaultLocale")
    public String getFormattedPrice(){
        return String.format("$%d", price);
    }
}
