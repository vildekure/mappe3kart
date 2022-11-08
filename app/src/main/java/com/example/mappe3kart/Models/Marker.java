package com.example.mappe3kart.Models;

public class Marker {
    private int ID;
    private String latitude;
    private String longitude;
    private String name;

    public Marker(){
    }

    public Marker(String latitude, String longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public Marker(int ID, String latitude, String longitude, String name) {
        this.ID = ID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
