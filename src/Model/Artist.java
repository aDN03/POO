package src.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Artist implements Serializable {

    private String id;
    private String name;
    private List<Song> songs;
    private static final long serialVersionUID = 1L;

    public Artist() {
        this.id = "";
        this.name = "";
        this.songs = new ArrayList<>();
    }

    public Artist(String id, String name, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }

    public Artist(Artist a){
        this.id = a.id;
        this.name = a.name;
        for(Song song: a.songs){
            this.songs.add(new Song(song));
        }
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Song> getSongs() {
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song){
        if(!songs.contains(song)){
            songs.add(song);
        }
    }

    @Override
    public String toString() {
        return "Artist [id=" + id + ", name=" + name + "]";
    }
}
