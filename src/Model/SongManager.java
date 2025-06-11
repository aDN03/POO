package src.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import src.DTO.SongTransfer;
import src.Exceptions.*;

public class SongManager implements Serializable {
    private Map<String, Song> songLibrary;
    private int newSongId = 1;
    private static final long serialVersionUID = 1L;

    public SongManager() {
        songLibrary = new HashMap<>();
    }

    public Song getSong(String id) {
        if(!songLibrary.containsKey(id)){
            throw new SongNotFoundException("Song with Id " + id + " not Found");
        }
        return songLibrary.get(id);
    }

    public String songIdGenerator(){
        return "S" + newSongId++;
    }

    public Collection<Song> getAllSongs(){
        Collection<Song> songs = songLibrary.values();
        return songs;
    }

    public void addSong(Song song){
        if (song == null || song.get_id() == null) {
            throw new InvalidSongException("Song or song ID cannot be null.");
        }
        if (songLibrary.containsKey(song.get_id())) {
            throw new DuplicateSongException("Song with ID " + song.get_id() + " already exists.");
        }
        songLibrary.put(song.get_id(), song);
    }

    public void deleteSong(String id){
        Song song = songLibrary.get(id);
        if (song != null) {
        // Remove the song from the overall song list
            songLibrary.remove(id);

        // Remove the song from the album's song list
            Album album = song.getAlbum();
            if (album != null) {
                album.removeSong(song);
        }

            System.out.println("Song deleted successfully!");
        } else {
            throw new SongNotFoundException("Song with ID " + id + " not found");
    }
    }

    public List<Song> getSongsByName (String name){
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidSongException("Song name cannot be null or empty.");
        }

        List<Song> songs = new ArrayList<>();
        for (Song song : songLibrary.values()){
            if(song.get_name().equalsIgnoreCase(name)){
                songs.add(song);
            }
        }

        if (songs.isEmpty()) {
            throw new SongNotFoundException("No songs found with name: " + name);
        }

        return songs;
    }

    public void applyChangesFrom(String sid, SongTransfer st) {
        Song song = songLibrary.get(sid);

        if (song == null) {
            throw new SongNotFoundException("Song with id " + sid + " not found");
        }

        if (st.getName() != null) song.set_name(st.getName());
        if (st.getLyrics() != null) song.set_lyrics(st.getLyrics());
        if (st.getGenre() != null) {
            try {
                Genre genre = Genre.valueOf(st.getGenre().toUpperCase());
                song.setGenre(genre);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid genre: " + st.getGenre());
            }
        }
        if (st.getDuration() != 0) song.set_duration(st.getDuration());
        if (st.hasVideo() != null) song.set_hasVideo(st.hasVideo());
        if (st.getRecordLabel() != null) song.set_recordLabel(st.getRecordLabel());
        if (st.getMusicSheet() != null && !st.getMusicSheet().isEmpty()) {
            List<MusicSheet> ms = new ArrayList<>();
            for (String s : st.getMusicSheet()) {
                MusicSheet ml = new MusicSheet();
                ml.set_line(s);
                ms.add(ml);
            }
            song.replaceMusicSheet(ms);
        }
    }
}