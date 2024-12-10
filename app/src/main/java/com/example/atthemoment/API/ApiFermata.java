package com.example.atthemoment.API;

public class ApiFermata {
    private String description;
    private String bookInfo;
    private String waitingMessage;
    private double y;
    private double x;

    public ApiFermata(String description, String bookInfo, String waitingMessage, double y, double x) {
        this.description = description;
        this.bookInfo = bookInfo;
        this.waitingMessage = waitingMessage;
        this.y = y;
        this.x = x;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public String getWaitingMessage() {
        return waitingMessage;
    }

    public void setWaitingMessage(String waitingMessage) {
        this.waitingMessage = waitingMessage;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
}
