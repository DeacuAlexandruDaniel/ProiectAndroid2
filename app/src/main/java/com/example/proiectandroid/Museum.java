package com.example.proiectandroid;

public class Museum {
    private String museumName;
    private String museumLocation;
    private int museumImageId;

    public Museum(String museumName, String museumLocation, int museumImageId) {
        this.museumName = museumName;
        this.museumLocation = museumLocation;
        this.museumImageId = museumImageId;
    }

    public String getMuseumName() {
        return museumName;
    }

    public String getMuseumLocation() {
        return museumLocation;
    }

    public int getMuseumImageId() {
        return museumImageId;
    }
}
