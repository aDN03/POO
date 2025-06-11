package src.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import src.Exceptions.*;

public class PlaylistManager implements Serializable{
    private Map<String, Playlist> playlistLibrary;
    private int newPlaylistId = 1;
    private static final long serialVersionUID = 1L;

    public PlaylistManager() {
        playlistLibrary = new HashMap<>();
    }

    public Playlist getPlaylist(String id) {
        return playlistLibrary.get(id);
    }

    public String playlistIdGenerator(){
        return "P" + newPlaylistId++;
    }

    public Collection<Playlist> getAllPlaylists(){
        Collection<Playlist> playlists = playlistLibrary.values();
        return playlists;
    }

    public List<Playlist> publicPlaylists(){
        List<Playlist> publicPlaylists = new ArrayList<>();

        for (Playlist playlist : playlistLibrary.values()) {
            if (playlist instanceof UserCreated) {
                UserCreated userCreatedPlaylist = (UserCreated) playlist;
                if (userCreatedPlaylist.isPublic()) {
                    publicPlaylists.add(userCreatedPlaylist);
                }
            }

            if(playlist instanceof ProgramCreated){
                publicPlaylists.add(playlist);
            }
        }

        if(publicPlaylists.isEmpty()){
            throw new PlaylistNotFoundException("There are no public playlists");
        }

        return publicPlaylists;
    }


    public boolean deletePlaylist(String id) {
        if (!playlistLibrary.containsKey(id)) {
            throw new PlaylistNotFoundException("Playlist with id " + id + " not found.");
        }
        playlistLibrary.remove(id);
        return true;
    }

    public String createUserPlaylist(User user, Boolean isPrivate) {
        if (user == null || user.getSubscription() == null) {
            throw new IllegalArgumentException("User or subscription is not initialized.");
        }

        if (user.getSubscription() instanceof Free) {
            throw new UnauthorizedAccessException("You do not have permissions to create a playlist, please upgrade your subscription");
        }

        String playlistId = playlistIdGenerator();
        UserCreated playlist = new UserCreated(user, isPrivate);
        playlist.setId(playlistId);
        user.addPlaylist(playlist);
        playlistLibrary.put(playlistId, playlist);

        return playlistId;
    }

    public String createProgramPlaylist(Collection<Song> songLibrary) {
        int minSongsRequired = 5;
        int minSongsInPlaylist = 2;

        if (songLibrary.size() < minSongsRequired) {
            throw new InsufficientSongsException("Not enough songs to create a playlist.");
        }

        // Generate a random number with the formula ((max - min + 1) + min) 
        int playlistSize = new Random().nextInt(songLibrary.size() - minSongsInPlaylist + 1) + minSongsInPlaylist;

        // Get all available songs in a list and shuffle it
        List<Song> allSongs = new ArrayList<>(songLibrary);
        Collections.shuffle(allSongs);

        // Get the first X songs (playlist size)
        List<Song> selectedSongs = allSongs.subList(0, playlistSize);

        String playlistId = playlistIdGenerator();
        ProgramCreated newPlaylist = new ProgramCreated();
        newPlaylist.setId(playlistId);
        newPlaylist.setSongs(new ArrayList<>(selectedSongs));
        playlistLibrary.put(playlistId, newPlaylist);
        return playlistId;

    }

    public void addSongsToPlaylist(String playlistId, List<Song> songs) {
        Playlist playlist = playlistLibrary.get(playlistId);
        if (playlist == null) {
            throw new PlaylistNotFoundException("Playlist with id " + playlistId + " not found.");
        }
        if (songs == null || songs.isEmpty()) {
            throw new IllegalArgumentException("No songs provided to add.");
        }
        for (Song song : songs) {
            playlist.addSong(song);
        }
    }

    public void removeSongFromPlaylist(String playlistId, String songToRemove) {
        Playlist playlist = playlistLibrary.get(playlistId);
        if (playlist == null) {
            throw new PlaylistNotFoundException("Playlist with id " + playlistId + " not found.");
        }
        boolean removed = playlist.getSongs().removeIf(song -> song.get_id().equals(songToRemove));
        if (!removed) {
            throw new IllegalArgumentException("Song with id " + songToRemove + " not found in the playlist.");
        }
    }

    public boolean addPlaylist(Playlist playlist){
        if(playlist != null && playlist.getId() != null && !playlistLibrary.containsKey(playlist.getId())) {
            playlistLibrary.put(playlist.getId(), playlist);
            return true;
        } else {
            return false;
        }
    }

    public String userPreference(User user, Collection<Song> songLibrary){
        if(songLibrary.size() < 5){
            throw new InsufficientSongsException("The song library is too small to create a playlist");
        }
        int minSongsInPlaylist = 2;
        PreferencePlaylist preferencePlaylist = new PreferencePlaylist(user);
        List<Song> songsForPlaylist = new ArrayList<>();
        List<Genre> listenedGenres = new ArrayList<>();
        for(History h : user.getHistory()){
            listenedGenres.add(h.getSong().getGenre());
        }

        Collections.shuffle(listenedGenres);
        List<Song> allSongs = new ArrayList<>(songLibrary);
        Collections.shuffle(allSongs);
        int playlistSize = new Random().nextInt(songLibrary.size() - minSongsInPlaylist + 1) + minSongsInPlaylist;

        for(Genre genre : listenedGenres){
            for(Song song : allSongs){
                if(song.getGenre() == genre && !songsForPlaylist.contains(song)){
                    songsForPlaylist.add(song);
                    break;
                }
            }

            if(songsForPlaylist.size() >= playlistSize){
                break;
            }
        }

        String playlistId = playlistIdGenerator();
        preferencePlaylist.setId(playlistId);
        preferencePlaylist.setSongs(songsForPlaylist);
        playlistLibrary.put(playlistId, preferencePlaylist);

        return playlistId;

    }

    public String userPreferenceAndTime(User user, Collection<Song> songLibrary, int seconds){

        PreferencePlaylist preferencePlaylist = new PreferencePlaylist(user);
        List<Song> songsForPlaylist = new ArrayList<>();
        List<Genre> listenedGenres = new ArrayList<>();
        int totalPlaytime = 0;
        for(History h : user.getHistory()){
            listenedGenres.add(h.getSong().getGenre());
        }

        Collections.shuffle(listenedGenres);
        List<Song> allSongs = new ArrayList<>(songLibrary);
        Collections.shuffle(allSongs);

        for(Genre genre : listenedGenres){
            for(Song song : allSongs){
                if(song.getGenre() == genre && !songsForPlaylist.contains(song) && (totalPlaytime + song.get_duration()) < seconds){
                    songsForPlaylist.add(song);
                    totalPlaytime += song.get_duration();
                    break;
                }
            }
        }

        if(songsForPlaylist.isEmpty()){
            throw new InsufficientSongsException("There are no songs that match the time criteria");
        }

        String playlistId = playlistIdGenerator();
        preferencePlaylist.setId(playlistId);
        preferencePlaylist.setSongs(songsForPlaylist);
        playlistLibrary.put(playlistId, preferencePlaylist);

        return playlistId;
    }

    public String userPreferenceOnlyExplicit(User user, Collection<Song> songLibrary){
        if(songLibrary.size() < 5){
            throw new InsufficientSongsException("The song library is too small to create a playlist");
        }
        int minSongsInPlaylist = 2;
        PreferencePlaylist preferencePlaylist = new PreferencePlaylist(user);
        List<Song> songsForPlaylist = new ArrayList<>();
        List<Genre> listenedGenres = new ArrayList<>();
        for(History h : user.getHistory()){
            listenedGenres.add(h.getSong().getGenre());
        }

        Collections.shuffle(listenedGenres);
        List<Song> allSongs = new ArrayList<>(songLibrary);
        Collections.shuffle(allSongs);
        int playlistSize = new Random().nextInt(songLibrary.size() - minSongsInPlaylist + 1) + minSongsInPlaylist;

        for(Genre genre : listenedGenres){
            for(Song song : allSongs){
                if(song.getGenre() == genre && !songsForPlaylist.contains(song) && song instanceof ExplicitSong){
                    songsForPlaylist.add(song);
                    break;
                }
            }

            if(songsForPlaylist.size() >= playlistSize){
                break;
            }
        }

        String playlistId = playlistIdGenerator();
        preferencePlaylist.setId(playlistId);
        preferencePlaylist.setSongs(songsForPlaylist);
        playlistLibrary.put(playlistId, preferencePlaylist);

        return playlistId;

    }

}
