package it.rizzoli.atthemoment.API;
import java.util.List;

import it.rizzoli.atthemoment.model.principali.Mezzo;
import it.rizzoli.atthemoment.model.Stop;

public class ApiMezzo {
    private String code;
    private String lineDescription;
    private Integer direction;
    private List<Stop> stops;

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
