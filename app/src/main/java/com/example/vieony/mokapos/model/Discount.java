package com.example.vieony.mokapos.model;

import java.text.DecimalFormat;

public class Discount {

    private int id;
    private String name;
    private double percentage;
    private DecimalFormat df = new DecimalFormat("#.##");

    public Discount(int id, String name, double percentage){
        this.id = id;
        this.name = name;
        this.percentage = percentage;
    }

    public String getName(){
        return name;
    }

    public double getPercentage() {
        return  percentage;
    }

    public String getFormattedPercentage() {
        return  String.format("%s%%", df.format(percentage));
    }
}
