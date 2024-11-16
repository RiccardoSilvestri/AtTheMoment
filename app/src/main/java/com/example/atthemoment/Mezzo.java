package com.example.atthemoment;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Mezzo {

    @SerializedName("Id")
    public String id;

    @SerializedName("Code")
    public Integer code;

    @SerializedName("Direction")
    public Integer direction;

    @SerializedName("Line")
    public Line line;

    @SerializedName("Stops")
    public List<Stop> stops;

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCode() {
        return code == null ? 0 : code;
    }

    public void setCode(Integer code) {
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

    public static class Line {

        @SerializedName("OperatorCode")
        public String operatorCode;

        @SerializedName("LineCode")
        public Integer lineCode;

        @SerializedName("LineDescription")
        public String lineDescription;

        @SerializedName("Suburban")
        public Boolean suburban;

        @SerializedName("TransportMode")
        public Integer transportMode;

        @SerializedName("OtherRoutesAvailable")
        public Boolean otherRoutesAvailable;
    }

    public static class Stop {
        @SerializedName("OperatorCode")
        public String operatorCode;

        @SerializedName("Code")
        public Integer code;

        @SerializedName("Description")
        public String description;

        @SerializedName("Location")
        public Location location;

        @SerializedName("PointType")
        public Integer pointType;

        @SerializedName("StopType")
        public String stopType;
    }

    public static class Location {

        @SerializedName("X")
        public Double x;

        @SerializedName("Y")
        public Double y;
    }
}
