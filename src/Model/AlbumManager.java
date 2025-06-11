package src.Model;

import java.io.Serializable;
import java.util.*;
import src.Exceptions.*;

public class AlbumManager implements Serializable {
    private Map<String, Album> albumLibrary;
    private int newAlbumId = 1;
    private static final long serialVersionUID = 1L;

    public AlbumManager() {
        albumLibrary = new HashMap<>();
    }

    public Album getAlbum(String id) {
        Album album = albumLibrary.get(id);
        if (album == null) {
            throw new AlbumNotFoundException("Album with ID " + id + " not found.");
        }
        return album;
    }

    public Collection<Album> getAllAlbums(){
        return albumLibrary.values();
    }

    public void replaceAlbum(String id, Album album){
        if (album == null || id == null) {
            throw new InvalidAlbumException("Album or ID cannot be null.");
        }
        albumLibrary.put(id, album);
    }

    public String albumIdGenerator(){
        return "A" + newAlbumId++;
    }

    public void addAlbum(Album album){
        if (album == null || album.get_id() == null) {
            throw new InvalidAlbumException("Album or album ID cannot be null.");
        }
        if (albumLibrary.containsKey(album.get_id())) {
            throw new DuplicateAlbumException("Album with ID " + album.get_id() + " already exists.");
        }
        albumLibrary.put(album.get_id(), album);
    }

    public void deleteAlbum(String id){
        if (!albumLibrary.containsKey(id)) {
            throw new AlbumNotFoundException("Cannot delete. Album with ID " + id + " does not exist.");
        }
        albumLibrary.remove(id);
    }

    public List<Album> getAlbunsByName (String name){
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidAlbumException("Album name cannot be null or empty.");
        }

        List<Album> albuns = new ArrayList<>();
        for (Album album : albumLibrary.values()){
            if(album.get_name().equalsIgnoreCase(name)){
                albuns.add(album);
            }
        }

        if (albuns.isEmpty()) {
            throw new AlbumNotFoundException("No albums found with name: " + name);
        }

        return albuns;
    }

}
