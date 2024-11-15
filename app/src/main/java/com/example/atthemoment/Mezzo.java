package com.example.atthemoment;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Mezzo {

    @SerializedName("Id")
    public int id;

    @SerializedName("Code")
    public int code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
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

    @SerializedName("Direction")
    public int direction;

    @SerializedName("Line")
    public Line line;

    @SerializedName("Stops")
    public List<Stop> stops;

    public static class Line {

        @SerializedName("OperatorCode")
        public int operatorCode;

        @SerializedName("LineCode")
        public int lineCode;

        @SerializedName("LineDescription")
        public String lineDescription;

        @SerializedName("Suburban")
        public boolean suburban;

        @SerializedName("TransportMode")
        public int transportMode;

        @SerializedName("OtherRoutesAvailable")
        public boolean otherRoutesAvailable;

//        @SerializedName("Links")
//        public String Links;
    }
    public static class Stop {
        @SerializedName("OperatorCode")
        public int operatorCode;

        @SerializedName("Code")
        public int code;

        @SerializedName("Description")
        public String description;

        @SerializedName("Location")
        public Location location;

        @SerializedName("PointType")
        public int pointType;

        //@SerializedName("OtherLines")
        //public List<Object> otherLines;

        @SerializedName("StopType")
        public String stopType;

        //@SerializedName("Links")
        //public List<Link> links;
    }
        public static class Location {

            @SerializedName("X")
            public double x;

            @SerializedName("Y")
            public double y;
        }
            /*public static class Link {

        @SerializedName("Rel")
        public String rel;

        @SerializedName("Href")
        public String href;

        @SerializedName("Title")
        public String title;

        // Getters e Setters

    }*/
    }
    




