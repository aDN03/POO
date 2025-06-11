package src.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Song implements Serializable, Comparable<Song> {

    private String id;
    private String name;
    private String lyrics;
    private List<MusicSheet> ms;
    private Genre genre;
    private int duration;
    private Boolean hasVideo;
    private String recordLabel;
    private int totalStreams;
    public Album album;
    public List<Artist> artists;
    private static final long serialVersionUID = 1L;

    public Song() {
        this.id = "";
        this.name = "";
        this.lyrics = "";
        this.ms = new ArrayList<>();
        this.genre = Genre.UNKNOWN;
        this.duration = 0;
        this.hasVideo = false;
        this.recordLabel = "";
        this.totalStreams = 0;
        this.album = new Album();
        this.artists = new ArrayList<>();
    }

    public Song(String id, String name, String lyrics, Genre genre, List<MusicSheet> lms, int duration, Boolean hasVideo, String recordLabel, int totalStreams, Album album, List<Artist> artists) {
        this.id = id;
        this.name = name;
        this.lyrics = lyrics;
        for(MusicSheet ms: lms){
            this.ms.add(new MusicSheet(ms));
        }
        this.genre = genre;
        this.duration = duration;
        this.hasVideo = hasVideo;
        this.recordLabel = recordLabel;
        this.totalStreams = totalStreams;
        this.album = album;
        for(Artist artist: artists){
            this.artists.add(artist);
        }
    }

    public Song(Song m) {
        this.id = m.id;
        this.name = m.name;
        this.lyrics = m.lyrics;
        this.ms = new ArrayList<>();
        for(MusicSheet ms: m.ms){
            this.ms.add(new MusicSheet(ms));
        }
        this.genre = m.genre;
        this.duration = m.duration;
        this.hasVideo = m.hasVideo;
        this.recordLabel = m.recordLabel;
        this.totalStreams = m.totalStreams;
        this.album = m.album;
        this.artists = new ArrayList<>();
        for(Artist artist: m.artists){
            this.artists.add(new Artist(artist));
        }
    }

    public String get_id() {
        return this.id;
    }

    public String get_name() {
        return this.name;
    }

    public String get_lyrics() {
        return this.lyrics;
    }

    public Album getAlbum() {
        return album;
    }

    public List<MusicSheet> get_MusicSheet() {
        List<MusicSheet> res = new ArrayList<>();
        for(MusicSheet ms: this.ms){
            res.add(new MusicSheet(ms));
        }
        return res;
    }

    public Genre getGenre() {
        return genre;
    }

    public int get_duration() {
        return this.duration;
    }

    public Boolean get_hasVideo() {
        return this.hasVideo;
    }

    public String get_recordLabel() {
        return this.recordLabel;
    }

    public int getTotalStreams() {
        return totalStreams;
    }

    public void set_id(String id) {
        this.id = id;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public void set_lyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public void set_MusicSheet(List<MusicSheet> lms) {
        for(MusicSheet ms: lms){
            this.ms.add(new MusicSheet(ms));
        }
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void set_duration(int duration) {
        this.duration = duration;
    }

    public void set_hasVideo(Boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public void set_recordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public void setTotalStreams(int totalStreams) {
        this.totalStreams = totalStreams;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void replaceMusicSheet(List<MusicSheet> lms) {
        this.ms = new ArrayList<>();
        for(MusicSheet ms: lms){
            this.ms.add(new MusicSheet(ms));
        }
    }


    public void add_Music_line(String s) {
        MusicSheet ms = new MusicSheet(s);
        this.ms.add(ms);
    }

    public MusicSheet get_Music_line(int i) {
        MusicSheet m = new MusicSheet(this.ms.get(i));
        return m;
    }

    public void addArtist(Artist artist) {
    if (!artists.contains(artist)) {
        artists.add(artist);
        }
    }
    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = new ArrayList<>(artists);
    }

    @Override
    public String toString() {
        String albumInfo = (album != null) ? album.get_id() + " - " + album.get_name() : "";

        String sheet = "";
        for (MusicSheet line : ms){
            sheet += "[" + line.get_line() + "] ";
        }

        return "Song{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", genre=" + genre +
                ", duration=" + duration +
                ", hasVideo=" + hasVideo +
                ", recordLabel='" + recordLabel + '\'' +
                ", totalStreams=" + totalStreams +
                ", album=" + albumInfo +
                ", music sheet=" + sheet +
                ", artists=" + artists +
                "\n" +
                '}';
    }

    @Override
    public int compareTo(Song other) {
        return Integer.compare(this.totalStreams, other.totalStreams);
    }

}