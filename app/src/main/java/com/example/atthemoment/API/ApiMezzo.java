package com.example.atthemoment.API;
import com.example.atthemoment.junk.Mezzo;
import com.example.atthemoment.junk.Mezzo.Stop;

import java.util.List;

public class ApiMezzo {
    private String code;
    private String lineDescription;
    private Integer direction;
    private List<Mezzo.Stop> stops;

    public ApiMezzo(String code, String lineDescription, Integer direction, List<Stop> stops) {
        this.code = code;
        this.lineDescription = lineDescription;
        this.direction = direction;
        this.stops = stops;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }
}
