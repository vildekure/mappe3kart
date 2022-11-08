package com.example.mappe3kart.Models;

public class Severdighet {
    public int id;
    public String latitude;
    public String longitude;
    public String name;
    public String info;
    public String adresse;

    public Severdighet(){
    }

    public Severdighet(String latitude, String longitude, String name, String info, String adresse) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.info = info;
        this.adresse = adresse;
    }

    public Severdighet(int id, String latitude, String longitude, String name, String info, String adresse) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.info = info;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
