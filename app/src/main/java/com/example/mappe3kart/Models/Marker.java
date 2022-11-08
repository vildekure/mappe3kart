package com.example.mappe3kart.Models;

public class Marker {
    private int ID;
    private String latitude;
    private String longitude;
    private String name;
    private String info;
    private String adresse;

    public Marker(){
    }

    public Marker(String latitude, String longitude, String name, String info, String adresse) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.info = info;
        this.adresse = adresse;
    }

    public Marker(int ID, String latitude, String longitude, String name, String info, String adresse) {
        this.ID = ID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.info = info;
        this.adresse = adresse;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
