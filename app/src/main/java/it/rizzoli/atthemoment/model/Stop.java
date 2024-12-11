package it.rizzoli.atthemoment.model;

import com.google.gson.annotations.SerializedName;

public class Stop {
    @SerializedName("OperatorCode")
    private String operatorCode;
    @SerializedName("Code")
    private String code;
    @SerializedName("Description")
    private String description;
    @SerializedName("Location")
    private Location location;
    @SerializedName("PointType")
    private Integer pointType;
    @SerializedName("StopType")
    private String stopType;

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getPointType() {
        return pointType;
    }

    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }

    public String getStopType() {
        return stopType;
    }

    public void setStopType(String stopType) {
        this.stopType = stopType;
    }
}
