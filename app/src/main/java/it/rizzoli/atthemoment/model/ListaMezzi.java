package it.rizzoli.atthemoment.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ListaMezzi {
    @SerializedName("Id")
    private String id;

    @SerializedName("Code")
    private String code;

    @SerializedName("Direction")
    private String direction;

    @SerializedName("Line")
    private Line line;

    @SerializedName("Stops")
    private Stop stops;

    @SerializedName("Geometry")
    private Object geometry;

    @SerializedName("Links")
    private List<Link> links;

    public ListaMezzi() {}

    @Override
    public String toString() {
        return "ListaMezzi{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", direction='" + direction + '\'' +
                ", line=" + line +
                ", stops=" + stops +
                ", geometry=" + geometry +
                ", links=" + links +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDirection() {
        return direction;
    }

    public Line getLine() {
        return line;
    }

    public Stop getStops() {
        return stops;
    }

    public Object getGeometry() {
        return geometry;
    }

    public List<Link> getLinks() {
        return links;
    }
}
