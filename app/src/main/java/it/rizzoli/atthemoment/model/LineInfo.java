package it.rizzoli.atthemoment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LineInfo {
    @SerializedName("Lines")
    private Line line;
    @SerializedName("Direction")
    private String direction;
    @SerializedName("BookletUrl")
    private String bookletUrl;
    @SerializedName("BookletUrl2")
    private String bookletUrl2;
    @SerializedName("WaitMessage")
    private String waitMessage;
    @SerializedName("JourneyPatternId")
    private String journeyPatternId;

    private List<TrafficBulletin> trafficBulletins;
    private List<Link> links;

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getBookletUrl() {
        return bookletUrl;
    }

    public void setBookletUrl(String bookletUrl) {
        this.bookletUrl = bookletUrl;
    }

    public String getBookletUrl2() {
        return bookletUrl2;
    }

    public void setBookletUrl2(String bookletUrl2) {
        this.bookletUrl2 = bookletUrl2;
    }

    public String getWaitMessage() {
        return waitMessage;
    }

    public void setWaitMessage(String waitMessage) {
        this.waitMessage = waitMessage;
    }

    public String getJourneyPatternId() {
        return journeyPatternId;
    }

    public void setJourneyPatternId(String journeyPatternId) {
        this.journeyPatternId = journeyPatternId;
    }

    public List<TrafficBulletin> getTrafficBulletins() {
        return trafficBulletins;
    }

    public void setTrafficBulletins(List<TrafficBulletin> trafficBulletins) {
        this.trafficBulletins = trafficBulletins;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}