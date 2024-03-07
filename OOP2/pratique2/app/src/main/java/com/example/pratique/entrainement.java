package com.example.pratique;

public class entrainement {

    private String type;
    private int heuredep;
    private int heurefin;

    public entrainement(String type, int heuredep, int heurefin) {
        this.type = type;
        this.heuredep = heuredep;
        this.heurefin = heurefin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHeuredep() {
        return heuredep;
    }

    public void setHeuredep(int heuredep) {
        this.heuredep = heuredep;
    }

    public int getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(int heurefin) {
        this.heurefin = heurefin;
    }
}
