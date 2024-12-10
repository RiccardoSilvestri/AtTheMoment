package com.example.atthemoment.model;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("X")
    private double x;
    @SerializedName("Y")
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{x=" + x + ", y=" + y + "}";
    }
}
