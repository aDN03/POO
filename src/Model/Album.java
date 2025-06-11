package src.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private List<Song> songs;

    public Album() {
        this.id = "";
        this.name = "";
        this.songs = new ArrayList<>();
    }

    public Album(String id, String name, List<Song> songs) {
        this.id = id;
        this.name = name;

        for(Song m : songs) {
            Song mu = new Song(m);
            this.songs.add(mu);
        }

    }

    public Album( Album a) {
        this.id = a.id;
        this.name = a.name;

        for(Song m : a.songs) {
            Song mu = new Song(m);
            this.songs.add(mu);
        }
    }

    public String get_id() {
        return this.id;
    }

    public String get_name() {
        return this.name;
    }
    public List<Song> get_music() {
        List<Song> res = new ArrayList<>();

        for( Song m : this.songs) {
            Song mu = new Song(m);
            res.add(mu);
        }

        return res;
    }

    public void set_id(String id) {
        this.id = id;
    }


    public void set_name(String name) {
        this.name = name;
    }

    public void set_music(List<Song> song) {

        for( Song m : song) {
            Song mu = new Song(m);
            this.songs.add(mu);
        }
    }

    public void add_song(Song m) {
        this.songs.add(m);
    }



    public void remove_song(Song m) {
        this.songs.remove(m);
    }

    /*
     * Aceita id ou nome
     * Não faz nada se a musica não existir
     */
    public void remove_song(String s) {
        for( Song m : this.songs) {
            if(s.equals(m.get_id()) || s.equalsIgnoreCase(m.get_name())) this.songs.remove(m);
        }
    }

    public void removeSong(Song song) {
    songs.remove(song);
    }

    /*
     * Aceita id ou nome
     * 
     * Só usar se check_song retornar true
     * Corre-se o risco de retornar musica genérica
     * 
     */
    public Song get_song(String s) {

        for( Song m : this.songs) {
            if(s.equals(m.get_id()) || s.equalsIgnoreCase(m.get_name())) return new Song(m);
        }

        return new Song();
    }

    public Song get_song(int index) {
        Song m = new Song(this.songs.get(index));
        return m;
    }


    /*
     * Verifica se uma musica está numa playlist
     * 
     * Pode-se entregar a id ou o nome
     */
    public boolean check_song(String s) {
        for( Song m : this.songs) {
            if(s.equals(m.get_id()) || s.equalsIgnoreCase(m.get_name())) return true;
        }

        return false;
    }

    public boolean heck_song(Song m) {
        return this.songs.contains(m);
    }

    public int number_of_songs() {
        return this.songs.size();
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String toString() {
        String songsInfo = "";
        for (Song song : songs){
            songsInfo += "" + song.get_id() + " - " + song.get_name() + ", ";
        }

        return "Album{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", songs=[" + songsInfo + "]}";
    }

}