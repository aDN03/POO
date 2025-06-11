package src.Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import src.DTO.SongTransfer;
import src.DTO.UserTransfer;

public class SpotifUM implements Serializable {
    private SongManager songManager;
    private AlbumManager albumManager;
    private UserManager userManager;
    private PlaylistManager playlistManager;
    private ArtistManager artistManager;
    private static final long serialVersionUID = 1L;

    public SpotifUM() {
        this.songManager = new SongManager();
        this.albumManager = new AlbumManager();
        this.userManager = new UserManager();
        this.playlistManager = new PlaylistManager();
        this.artistManager = new ArtistManager();
    }

    // Song Manager

    public String generateSongId(){
        return songManager.songIdGenerator();
    }

    public void addSongToLibrary(Song song){
        songManager.addSong(song);
    }

    public void deleteSong(String id){
        songManager.deleteSong(id);
    }

    public Song getSong(String id){
        return songManager.getSong(id);
    }

    public List<Song> getSongsByName(String name){
        return songManager.getSongsByName(name);
    }

    public Collection<Song> getAllSongs(){
        return songManager.getAllSongs();
    }

    public void applyChangesFrom(String sid, SongTransfer st){
        songManager.applyChangesFrom(sid, st);
    }

    // Album Manager

    public String generateAlbumId(){
        return albumManager.albumIdGenerator();
    }

    public void addAlbumToLibrary(Album album){
        albumManager.addAlbum(album);
    }

    public void deleteAlbum(String id){
        albumManager.deleteAlbum(id);
    }

    public Album getAlbum(String id){
        return albumManager.getAlbum(id);
    }

    public List<Album> getAlbumByName(String name){
        return albumManager.getAlbunsByName(name);
    }

    public Collection<Album> getAllAlbums(){
        return albumManager.getAllAlbums();
    }

    public void replaceAlbum(String id, Album album){
        albumManager.replaceAlbum(id, album);
    }

    // User Manager

    public String generateUserId(){
        return userManager.userIdGenerator();
    }

    public void addUserToLibrary(User user){
        userManager.addUser(user);
    }

    public void deleteUser(String id){
        userManager.deleteUser(id);
    }

    public User getUser(String id){
        return userManager.getUser(id);
    }

    public List<User> getUsersByName(String name){
        return userManager.getUsersByName(name);
    }

    public Collection<User> getAllUsers(){
        return userManager.getAllUsers();
    }

    public void applyChangesFrom(String uid, UserTransfer ut){
        userManager.applyChangesFrom(uid, ut);
    }

    // Playlist manager

     public Playlist getPlaylist(String id) {
        return playlistManager.getPlaylist(id);
    }

    public String createUserPlaylist(User user, Boolean isPrivate){
        return playlistManager.createUserPlaylist(user, isPrivate);
    }

    public void addSongsToPlaylist(String playlistId, List<Song> songs){
        playlistManager.addSongsToPlaylist(playlistId, songs);
    }

    public void removeSongFromPlaylist(String playlistId, String songId){
        playlistManager.removeSongFromPlaylist(playlistId, songId);
    }

    public String generatePlaylistId(){
        return playlistManager.playlistIdGenerator();
    }

    public Collection<Playlist> getAllPlaylists(){
        return playlistManager.getAllPlaylists();
    }

    public List<Playlist> publicPlaylists(){
        return playlistManager.publicPlaylists();
    }

    public boolean addPlaylistToLibrary(Playlist playlist){
        return playlistManager.addPlaylist(playlist);
    }

    public void deletePlaylist(String id){
        playlistManager.deletePlaylist(id);
    }

    public void createProgramPlaylist(Collection<Song> songLibrary){
        playlistManager.createProgramPlaylist(songLibrary);
    }

    public String userPreference(User user, Collection<Song> songLibrary){
        return playlistManager.userPreference(user, songLibrary);
    }

    public String userPreferenceAndTime(User user, Collection<Song> songLibrary, int seconds){
        return playlistManager.userPreferenceAndTime(user, songLibrary, seconds);
    }

    public String userPreferenceOnlyExplicit(User user, Collection<Song> songLibrary){
        return playlistManager.userPreferenceOnlyExplicit(user, songLibrary);
    }

    // Artist manager

    public String generateArtistId(){
        return artistManager.artistIdGenerator();
    }

    public void addArtistToLibrary(Artist artist){
        artistManager.addArtist(artist);
    }

    public void deleteArtist(String id){
        artistManager.deleteArtist(id);
    }

    public Artist getArtist(String id){
        return artistManager.getArtist(id);
    }

    public List<Artist> getArtistsByName(String name){
        return artistManager.getArtistsByName(name);
    }

    public Collection<Artist> getAllArtists(){
        return artistManager.getAllArtists();
    }

    public void replaceArtist(String id, Artist artist){
        artistManager.replaceArtist(id, artist);
    }

}
