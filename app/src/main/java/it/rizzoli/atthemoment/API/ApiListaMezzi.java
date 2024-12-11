package it.rizzoli.atthemoment.API;

public class ApiListaMezzi {
    private String code;
    private String direction;
    private String lineDescription;
    private int tipologia;
    public ApiListaMezzi(String code, String direction, String lineDescription, int tipologia) {
        this.code = code;
        this.direction = direction;
        this.lineDescription = lineDescription;
        this.tipologia = tipologia;
    }

    public String getCode() {
        return code;
    }

    public String getDirection() {
        return direction;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public int getTipologia() {
        return tipologia;
    }

    @Override
    public String toString() {
        return "ApiListaMezzi{" +
                "code='" + code + '\'' +
                ", direction='" + direction + '\'' +
                ", lineDescription='" + lineDescription + '\'' +
                ", tipologia=" + tipologia +
                '}';
    }
}
