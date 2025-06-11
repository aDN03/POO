package src.DTO;

import java.util.ArrayList;
import java.util.List;

public class SongTransfer {

    private String name;
    private String lyrics;
    private String genre;

    private int duration;
    private Boolean isExplicit;
    private Boolean hasVideo;
    private String recordLabel;
    private List<String> musicSheet;

    public SongTransfer() {
        this.name = null;
        this.lyrics = null;
        this.genre = null;
        this.duration = 0;
        this.isExplicit = null;
        this.hasVideo = null;
        this.recordLabel = null;
        this.musicSheet = new ArrayList<>();
    }

    public SongTransfer(String name, String lyrics, String genre, int duration, boolean isExplicit, boolean hasVideo, String recordLabel) {
        this.name = name;
        this.lyrics = lyrics;
        this.genre = genre;
        this.duration = duration;
        this.isExplicit = isExplicit;
        this.hasVideo = hasVideo;
        this.recordLabel = recordLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Boolean isExplicit() {
        return isExplicit;
    }

    public void setExplicit(Boolean isExplicit) {
        this.isExplicit = isExplicit;
    }

    public Boolean hasVideo() {
        return hasVideo;
    }

    public void setHasVideo(Boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public void addMusicLine(String musicLine){
        this.musicSheet.add(musicLine);
    }

    public List<String> getMusicSheet() {
        return musicSheet;
    }

    public void setMusicSheet(List<String> musicSheet) {
        this.musicSheet = musicSheet;
    }

}
