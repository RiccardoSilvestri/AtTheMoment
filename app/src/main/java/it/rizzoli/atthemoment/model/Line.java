package it.rizzoli.atthemoment.model;

import com.google.gson.annotations.SerializedName;

public  class Line {

    @SerializedName("OperatorCode")
    private String operatorCode;

    @SerializedName("LineCode")
    private String lineCode;

    @SerializedName("LineDescription")
    private String lineDescription;

    @SerializedName("Suburban")
    private Boolean suburban;

    @SerializedName("TransportMode")
    private Integer transportMode;

    @SerializedName("OtherRoutesAvailable")
    private Boolean otherRoutesAvailable;

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String  lineCode) {
        this.lineCode = lineCode;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }

    public Boolean getSuburban() {
        return suburban;
    }

    public void setSuburban(Boolean suburban) {
        this.suburban = suburban;
    }

    public Integer getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(Integer transportMode) {
        this.transportMode = transportMode;
    }

    public Boolean getOtherRoutesAvailable() {
        return otherRoutesAvailable;
    }

    public void setOtherRoutesAvailable(Boolean otherRoutesAvailable) {
        this.otherRoutesAvailable = otherRoutesAvailable;
    }

    @Override
    public String toString() {
        return "Line{" +
                "operatorCode='" + operatorCode + '\'' +
                ", lineCode=" + lineCode +
                ", lineDescription='" + lineDescription + '\'' +
                ", suburban=" + suburban +
                ", transportMode=" + transportMode +
                ", otherRoutesAvailable=" + otherRoutesAvailable +
                '}';
    }
}