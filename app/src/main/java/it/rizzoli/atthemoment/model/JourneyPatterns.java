package it.rizzoli.atthemoment.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class JourneyPatterns {
    @SerializedName("JourneyPatterns")
    private List<ListaMezzi> journeyPatterns;

    public JourneyPatterns() {}

    public List<ListaMezzi> getJourneyPatterns() {
        return journeyPatterns;
    }

    public void setJourneyPatterns(List<ListaMezzi> journeyPatterns) {
        this.journeyPatterns = journeyPatterns;
    }

    @Override
    public String toString() {
        return "JourneyPatterns{" +
                "journeyPatterns=" + journeyPatterns +
                '}';
    }
}
