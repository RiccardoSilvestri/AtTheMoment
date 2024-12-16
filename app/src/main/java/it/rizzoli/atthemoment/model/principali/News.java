package it.rizzoli.atthemoment.model.principali;

import java.util.List;

public class News {
    private String Publication;
    private String Expiration;
    private String Title;
    private String Body;
    private List<String> Lines;
    private String Guid;

    public String getPublication() {
        return Publication;
    }

    public String getExpiration() {
        return Expiration;
    }

    public String getTitle() {
        return Title;
    }

    public String getBody() {
        return Body;
    }

    public List<String> getLines() {
        return Lines;
    }

    public String getGuid() {
        return Guid;
    }
}
