package com.example.tourismapp;

public class Vertical {
    private int id, image;
    private String place_name, place_descrip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getPlace_descrip() {
        return place_descrip;
    }

    public void setPlace_descrip(String place_descrip) {
        this.place_descrip = place_descrip;
    }

    public Vertical(int id, int image, String place_name, String place_descrip) {
        this.id = id;
        this.image = image;
        this.place_name = place_name;
        this.place_descrip = place_descrip;
    }
}
