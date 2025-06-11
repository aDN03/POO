package src.Model;

import java.io.Serializable;
import java.util.*;

import src.Exceptions.*;

public class ArtistManager implements Serializable {
    private Map<String, Artist> artistLibrary;
    private int newArtistId = 1;
    private static final long serialVersionUID = 1L;

    public ArtistManager() {
        artistLibrary = new HashMap<>();
    }

    public Artist getArtist(String id) {
        if (!artistLibrary.containsKey(id)) {
            throw new ArtistNotFoundException("Artist with ID " + id + " not found.");
        }
        return artistLibrary.get(id);
    }

    public String artistIdGenerator(){
        return "Ar" + newArtistId++;
    }

    public void addArtist(Artist artist) {
        if (artist == null || artist.getId() == null) {
            throw new IllegalArgumentException("Artist or Artist ID cannot be null.");
        }
        if (artistLibrary.containsKey(artist.getId())) {
            throw new DuplicateArtistException("Artist with ID " + artist.getId() + " already exists.");
        }

        artistLibrary.put(artist.getId(), artist);
    }

    public void deleteArtist(String id){
        if (!artistLibrary.containsKey(id)){
            throw new ArtistNotFoundException("Cannot delete. Artist with ID " + id + " does not exist.");
        } 
        artistLibrary.remove(id);    
    }

    public List<Artist> getArtistsByName (String name){
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidArtistException("Artist name cannot be null or empty.");
        }
        List<Artist> artists = new ArrayList<>();
        for (Artist artist : artistLibrary.values()){
            if(artist.getName().equalsIgnoreCase(name)){
                artists.add(artist);
            }
        }

        if (artists.isEmpty()) {
            throw new ArtistNotFoundException("No albums found with name: " + name);
        }
        return artists;
    }

    public Collection<Artist> getAllArtists(){
        Collection<Artist> artists = artistLibrary.values();
        return artists;
    }

    public void replaceArtist(String id, Artist artist){
        if (id == null || artist == null) {
            throw new IllegalArgumentException("Artist ID and artist object cannot be null.");
        }
        artistLibrary.put(id, artist);
    }

}
