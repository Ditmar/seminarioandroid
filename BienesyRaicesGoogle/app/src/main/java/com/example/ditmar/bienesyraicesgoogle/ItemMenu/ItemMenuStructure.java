package com.example.ditmar.bienesyraicesgoogle.ItemMenu;

import android.graphics.Bitmap;

public class ItemMenuStructure {
    private String street;
    private int price;
    private double lat;
    private double lon;
    private String contact;
    private String neighborhood;
    private String description;
    private String city;
    private String url;
    private Bitmap img;
    private String id;
    public ItemMenuStructure (String street, String urlimg, int price, double lat, double lon, String contact, String neighborhood, String city,String id, String description) {
        this.street = street;
        this.url = urlimg;
        this.price = price;
        this.lat = lat;
        this.lon = lon;
        this.contact = contact;
        this.neighborhood = neighborhood;
        this.city = city;
        this.id = id;
        this.description = description;
    }
    public void setImg(Bitmap img) {
        this.img = img;
    }
    public void setPrice (int p) {
        this.price = price;
    }

    public Bitmap getImg() {
        return this.img;
    }
    public int getPrice () {
        return this.price;
    }
    public String getStreet() {
        return  this.street;
    }
    public String getUrlimg() {
        return this.url;
    }
    public String getId() {
        return this.id;
    }
    public Bitmap getBitmap() {
        return this.img;
    }
    public String getDescription() {
        return this.description;
    }
    public double getLon() {
        return this.lon;
    }
    public double getLat() {
        return this.lat;
    }
    public String getContact() {
        return this.contact;
    }
}
