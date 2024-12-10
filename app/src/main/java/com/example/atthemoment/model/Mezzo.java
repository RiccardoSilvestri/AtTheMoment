package com.example.atthemoment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mezzo {

    @SerializedName("Id")
    private String id;

    @SerializedName("Code")
    private String code;

    @SerializedName("Direction")
    private Integer direction;

    @SerializedName("Line")
    private Line line;

    @SerializedName("Stops")
    private List<Stop> stops;

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code == null ? "0" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDirection() {
        return direction == null ? 0 : direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id='" + id + '\'' +
                ", code=" + code +
                ", direction=" + direction +
                ", line=" + line +
                ", stops=" + stops +
                '}';
    }
}

