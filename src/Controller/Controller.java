package src.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import src.DTO.SongTransfer;
import src.DTO.UserTransfer;
import src.Exceptions.*;
import src.Model.*;
import src.View.UI;


public class Controller {

    private SpotifUM manager;
    private State stateToSave = new State();
    private Player player = new Player();
    private Stats stats = new Stats();
    private User user;
    //private User admin = new User("U0", "Admin", "admin@spotifum.com", "LEI", null, 0, true); // this is just for log in in case there are no users delete later

    public void runUI(){

        try{
            State state = State.loadState();
            manager = state.getGeneralManager();
            //manager.addUserToLibrary(admin);
        } catch (IOException | ClassNotFoundException e){
            UI.printError("There is no save file, creating a new one");
            manager = new SpotifUM();
            
        }

        String uid = UI.login();
        this.user = manager.getUser(uid);
        
        if (this.user == null) {
            // System.out.println("Invalid user ID. Exiting...");
            return;
        }

            // We need to remake this UI to make it more clean, the main menu leads to sub menus, etc

            while(true){
                int option = UI.mainMenu();

                switch(option){
                    case 1:
                        handleSongMenu();
                        break;

                    case 2:
                        handleAlbumMenu();
                        break;

                    case 3:
                        handleUserMenu();
                        break;

                    case 4:
                        handlePlaylistMenu();
                        break;

                    case 5:
                        handleArtistMenu();
                        break;

                    case 6:
                        handlePlayContentMenu();
                        break;
                    case 7:
                        handleStatisticsMenu();
                        break;
                    // Change number, this should always be the logout
                    case 8:
                        stateToSave.setGeneralManager(manager);
                        try {
                            stateToSave.saveState();
                            UI.printSuccess("State saved with success");
                        } catch (IOException e) {
                            UI.printError("An error occurred while saving the state");
                        }
                        System.exit(0);
                }

            }

        }

    private void handleSongMenu() {
        int option = UI.songMenu();
        switch (option) {
            case 1: // Add song
                try {
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can add songs.");
                        break;
                    }
                    String albumId = UI.askAlbumID();
                    Album album = manager.getAlbum(albumId);

                    SongTransfer songT = UI.addSongUI();

                    Genre genre = Genre.valueOf(songT.getGenre().toUpperCase());
                    Song song = songT.isExplicit() ? new ExplicitSong() : new Song();
                    song.set_id(manager.generateSongId());
                    song.set_name(songT.getName());
                    song.set_lyrics(songT.getLyrics());
                    song.setGenre(genre);
                    song.set_duration(songT.getDuration());
                    song.set_hasVideo(songT.hasVideo());
                    song.set_recordLabel(songT.getRecordLabel());
                    song.set_MusicSheet(new ArrayList<>());
                    song.setAlbum(album);
                    song.setTotalStreams(0);
                    List<String> artistIds = UI.addArtistsUI();
                    List<Artist> artists = new ArrayList<>();
                    for (String artistId : artistIds) {
                        Artist artist = manager.getArtist(artistId);
                        artists.add(artist);
                    }
                    song.setArtists(artists);

                    for (Artist artist : artists) {
                        artist.addSong(song);
                    }

                    album.add_song(song);
                    UI.musicSheetUI(song);

                    manager.addSongToLibrary(song);

                } catch (IllegalArgumentException e) {
                    UI.printError("Invalid genre entered.");
                } catch (DuplicateSongException | SongNotFoundException | AlbumNotFoundException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 2: // List all Songs
                UI.listSongUI();
                Collection<Song> songs = manager.getAllSongs();
                UI.printSongs(songs);
                break;
            case 3: // Search song
                try {
                    String songName = UI.searchSongUI();
                    List<Song> songsByName = manager.getSongsByName(songName);
                    UI.printSearchedSongs(songsByName);
                    break;
                } catch(InvalidSongException | SongNotFoundException e){
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }

            case 4: // Song details
                try {
                    String songID = UI.songDetailsUI();
                    Song songById = manager.getSong(songID);
                    UI.printSongDetails(songById);
                    break;
                } catch(SongNotFoundException e){
                    UI.printError(e.getMessage());
                }catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
            case 5: // Edit song
                try {
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can edit songs.");
                        break;
                    }
                    String songIDtoEdit = UI.editSongUI();

                    SongTransfer st = UI.startEditSongUI();
                    manager.applyChangesFrom(songIDtoEdit, st);

                } catch(SongNotFoundException e){
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }

                break;
            case 6: // Remove song
                try {
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can remove songs.");
                        break;
                    }
                    String removeSongID = UI.removeSongUI();
                    manager.deleteSong(removeSongID);
                } catch (SongNotFoundException e){
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            default:
                return;
        }
    }

    private void handleAlbumMenu() {
        int option = UI.albumMenu();
        switch (option) {
            case 1: // Add album
                try {
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can add albums.");
                        break;
                    }
                    Album album = new Album();
                    String name = UI.addAlbumUI();

                    album.set_name(name);
                    album.set_id(manager.generateAlbumId());

                    manager.addAlbumToLibrary(album);
                } catch (InvalidAlbumException | AlbumNotFoundException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;

            case 2: // List all albums
                try {
                    Collection<Album> albums = manager.getAllAlbums();
                    UI.listAlbumUI(albums);
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;

            case 3: // Search album
                try {
                    String albumSearchByName = UI.searchAlbumUI();
                    List<Album> albumSearchResult = manager.getAlbumByName(albumSearchByName);
                    UI.printSearchedAlbums(albumSearchResult);
                }  catch (InvalidAlbumException | AlbumNotFoundException e) {
                    UI.printError(e.getMessage());
                }catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }

                break;

            case 4: // Album details
                try {
                    String albumById = UI.albumDetailsUI();
                    Album albumByIdResult = manager.getAlbum(albumById);
                    UI.printAlbumDetails(albumByIdResult);
                } catch (AlbumNotFoundException e){
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }

                break;

            case 5: // Edit album
                try {
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can edit albums songs.");
                        break;
                    }
                    String editAlbumId = UI.editAlbumUI();
                    Album albumToEdit = manager.getAlbum(editAlbumId);
                    String newName = UI.startEditAlbumUI();
                    if(newName != ""){
                        albumToEdit.set_name(newName);
                    }
                } catch (InvalidAlbumException e){
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }

                break;

            case 6: // Remove album
                try {
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can remove albums.");
                        break;
                    }
                    String removeAlbumId = UI.removeAlbumUI();
                    manager.deleteAlbum(removeAlbumId);
                } catch (AlbumNotFoundException e){
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;

            default:
                return;
        }
    }

    private void handleUserMenu() {
        int option = UI.userMenu();
        switch (option) {
            case 1: // Add user
                try {
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can add users.");
                        break;
                    }
                    UserTransfer usert = UI.addUserUI();
                    User user = new User();
                    user.setId(manager.generateUserId());
                    user.setName(usert.getName());
                    user.setAddress(usert.getAddress());
                    user.setEmail(usert.getEmail());

                    switch (usert.getSubscription()) {
                        case "Free":
                            user.setSubscription(new Free());
                            break;
                        case "PremiumBase":
                            user.setSubscription(new PremiumBase());
                            break;
                        case "PremiumTop":
                            PremiumTop premiumTop = new PremiumTop();
                            premiumTop.giveInitialPoints(user);
                            user.setSubscription(premiumTop);
                            break;
                        default:
                            UI.printError("Invalid subscription type");
                            return;
                    }

                    manager.addUserToLibrary(user);
                    UI.userCreatedID(user);
                } catch (IllegalArgumentException | DuplicateUserException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;

            case 2: // List all users
                try {
                    UI.listUserUI();
                    Collection<User> users = manager.getAllUsers();
                    UI.printUsers(users);
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;

            case 3: // Search user
                try {
                    String userSearchByName = UI.searchUserUI();
                    List<User> userSearchResult = manager.getUsersByName(userSearchByName);
                    UI.printSearchedUsers(userSearchResult);
                } catch (InvalidUserException | UserNotFoundException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;

            case 4: // User details
                try {
                    String userSearchById = UI.userDetailsUI();
                    User userById = manager.getUser(userSearchById);
                    UI.printUserDetails(userById);
                } catch (UserNotFoundException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                   UI.printError("Unexpected error occurred");
                }
                break;

            case 5: // Edit user
                try {
                    if(user.isAdmin() == false){
                         UI.printError("Only admin users can edit users.");
                        break;
                    }
                    String editUserById = UI.editUserUI();
                    UserTransfer userT = UI.startEditUserUI();
                    manager.applyChangesFrom(editUserById, userT);
                } catch (UserNotFoundException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;

            case 6: // Remove user
                try {
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can remove users.");
                        break;
                    }
                    String removeUser = UI.removeUserUI();
                    manager.deleteUser(removeUser);
                } catch (UserNotFoundException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;

            default:
                return;
        }
    }

    private void handlePlaylistMenu() {
        int option = UI.playlistMenu();
        switch (option) {
            case 1: // Create playlist for premium users
                try{
                    UI.addPlaylistUI();
                    boolean isPublic = UI.isPlaylistPublic();
                    String playlistId = manager.createUserPlaylist(user, isPublic);
                    UI.createdPlaylistUI();
                    UI.printSuccess("Playlist Added With Success");
                } catch (UnauthorizedAccessException | IllegalArgumentException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 2: // List all public playlists
                try{
                    UI.listPlaylistUI();
                    Collection<Playlist> playlists = manager.publicPlaylists();
                    UI.printPlaylistUI(playlists);
                    UI.printSuccess("Public Playlists Printed With Success");
                } catch (PlaylistNotFoundException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 3: // Playlist details
                try{
                    String playlistById = UI.playlistDetailsUI();
                    Playlist playlistByIdResult = manager.getPlaylist(playlistById);
                    
                    if(playlistByIdResult instanceof UserCreated){
                        UserCreated playlistUser = (UserCreated) playlistByIdResult;
                        if(user != playlistUser.getUser()) {
                            UI.printError("Only the creator of the playlist can see its details.");
                            break;
                        }
                        UI.printUserLessDetails(playlistUser.getUser());
                        UI.isPublicUI(playlistUser);
                    }
                    UI.searchPlaylistUI(playlistByIdResult);
                    UI.printSuccess("Playlist Found and detailed");
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 4: // Remove playlist
                try{
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can remove playlists.");
                        break;
                    }
                    String removePlaylist = UI.removePlaylistUI();
                    manager.deletePlaylist(removePlaylist);
                    UI.printSuccess("Playlist Removed With Success");
                } catch (PlaylistNotFoundException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 5: // Add song to playlist
                try{
                    String playlistByID = UI.addSongToPlaylistUI();

                    UserCreated playlistUser = (UserCreated) manager.getPlaylist(playlistByID);
                    if(user != playlistUser.getUser()) {
                        throw new UnauthorizedAccessException("Only the creator of the playlist can add songs to it.");
                    }
                    if(manager.getPlaylist(playlistByID) instanceof PreferencePlaylist){
                        throw new UnauthorizedAccessException("Preference playlists can't be edited.");
                    }
                    
                    Integer numOfSongs = UI.numberOfSongsToPlaylist();
                    List<Song> songs = new ArrayList<>();
                    for (int i = 0; i < numOfSongs; i++) {
                        String songById = UI.songToAddPlaylistID();
                        Song song = manager.getSong(songById);
                        songs.add(song);
                    }
                    manager.addSongsToPlaylist(playlistByID, songs);
                    UI.printSuccess("Song Added With Success");
                } catch (IllegalArgumentException | PlaylistNotFoundException | UnauthorizedAccessException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 6: // Remove song from playlist
                try{
                    String playlistByID = UI.removeSongFromPlaylistUI();
                    UserCreated playlistUser = (UserCreated) manager.getPlaylist(playlistByID);
                    if(user != playlistUser.getUser()) {
                        throw new UnauthorizedAccessException("Only the creator of the playlist can remove songs from it.");
                    }
                    if(manager.getPlaylist(playlistByID) instanceof PreferencePlaylist){
                        throw new UnauthorizedAccessException("Preference playlists can't be edited.");
                    }
                    String songByID = UI.removeSongPlayListUI();
                    manager.removeSongFromPlaylist(playlistByID, songByID);
                    UI.printSuccess("Song Removed With Success");
                } catch (IllegalArgumentException | PlaylistNotFoundException | UnauthorizedAccessException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 7: // Create automatic generated playlist
                try{
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can create this kind of playlists.");
                        break;
                    }
                    UI.addAutoPlaylistUI();
                    Collection<Song> allSongs = manager.getAllSongs();
                    manager.createProgramPlaylist(allSongs);
                    UI.createdPlaylistUI();
                    UI.printSuccess("Playlist Created With Success");
                } catch (PlaylistNotFoundException e) {
                    UI.printError(e.getMessage());
                } catch (IllegalArgumentException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 8:
                handlePreferencePlaylistMenu();
                break;
            default:
                return;
        }
    }

    private void handlePreferencePlaylistMenu(){
        int option = UI.preferencePlaylistMenu();
        switch (option) {
            case 1:
                try {
                    Collection<Song> songs = manager.getAllSongs();
                    String id = manager.userPreference(user, songs);
                    UI.printSuccess("Playlist created with the id: " + id);
                    break;
                } catch (InsufficientSongsException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }

            case 2: 
                try{
                    int time = UI.askForDuration();
                    int timeInSeconds = time * 60;
                    Collection<Song> songs = manager.getAllSongs();
                    String id = manager.userPreferenceAndTime(user, songs, timeInSeconds);
                    UI.printSuccess("Playlist created with the id: " + id);
                    break;
                }  catch (InsufficientSongsException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
            case 3:
                try {
                    Collection<Song> songs = manager.getAllSongs();
                    String id = manager.userPreferenceOnlyExplicit(user, songs);
                    UI.printSuccess("Playlist created with the id: " + id);
                    break;
                } catch (InsufficientSongsException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
            default:
                return;
        }
    }

    private void handleArtistMenu(){
        int option = UI.artistMenu();
        switch (option){
            case 1://Add artist
                try{
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can add artists.");
                        break;
                    }
                    Artist artist = UI.addArtistUI();
                    artist.setId(manager.generateArtistId());
                        manager.addArtistToLibrary(artist);
                    } catch (InvalidAlbumException | DuplicateArtistException e) {
                        UI.printError(e.getMessage());
                    } catch (Exception e) {
                        UI.printError("Unexpected error occurred");
                    }
                break;
            case 2://List all artist
                try {
                    UI.listArtistUI();
                    Collection<Artist> artists = manager.getAllArtists();
                    UI.printArtists(artists);
                } catch (ArtistNotFoundException e){
                    UI.printError(e.getMessage());
                }
                catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 3://Search artist
                try {
                    String artistSearchByName = UI.searchArtistUI();
                    List<Artist> artistSearchResult = manager.getArtistsByName(artistSearchByName);
                    UI.printArtists(artistSearchResult);
                } catch (InvalidArtistException | ArtistNotFoundException e){
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 4://Artist details
                try {
                    String artistSearchByID = UI.artistDetailsUI();
                    Artist artistByID = manager.getArtist(artistSearchByID);
                    UI.printArtistDetails(artistByID);
                } catch (ArtistNotFoundException e){
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }

                break;
            case 5://Edit artist
                try {
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can edit artists.");
                        break;
                    }
                    String editArtistByID = UI.editArtistUI();
                    Artist artistToEdit = manager.getArtist(editArtistByID);
                    Artist artistClone = new Artist(artistToEdit);
                    UI.startEditArtistUI(artistClone);
                    manager.replaceArtist(editArtistByID, artistClone);
                } catch (IllegalArgumentException e){
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 6://Remove artist
                try {
                    if(user.isAdmin() == false){
                        UI.printError("Only admin users can remove artists.");
                        break;
                    }
                    String removeArtistByID = UI.removeArtistUI();
                    manager.deleteArtist(removeArtistByID);
                } catch (IllegalArgumentException e){
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 7://Back to menu
                break;
        }

    }

    private void handlePlayContentMenu() {
        int option = UI.playContentMenu();
        switch (option) {
            case 1: // Play song
                try{
                    //Verificar a subscricao do user, se for free nao pode tocar a musica
                    if(user.getSubscription() instanceof Free){
                        UI.printError("Free users cannot play songs");
                        return;
                    } else {
                        String songByID = UI.playSongUI();
                        Song song = manager.getSong(songByID);
                        //UI.printLyrics(song.get_lyrics());
                        player.playSong(song, user);
                    }
                    UI.printSuccess("Song Played With Success");
                } catch (IllegalArgumentException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 2: // Play playlist
                try{
                    String playlistByID = UI.playPlaylistUI();
                    Playlist playlist = manager.getPlaylist(playlistByID);
                    int optionForPlaylist = UI.whatModeToPlay();

                    if(optionForPlaylist == 0){
                        if(user.getSubscription() instanceof Free){
                        UI.printError("Free users can only play randomly");
                        return;
                    }
                        if(playlist instanceof UserCreated){
                            UserCreated uplaylist = (UserCreated) playlist;
                            player.playlistWithControls(user, uplaylist);
                        } else {
                            player.playPlaylist(playlist, user);
                        }
                        
                    } else if(optionForPlaylist == 1){

                        if(playlist instanceof UserCreated){
                            if(user.getSubscription() instanceof Free){
                                player.playPlaylistRandomly(playlist, user);
                                break;
                            }
                            UserCreated uplaylist = (UserCreated) playlist;
                            player.playlistWithControlsAndShuffle(user, uplaylist);
                        } else {
                            player.playPlaylistRandomly(playlist, user);
                        }
                        
                    } else {
                        return;
                    }
                    UI.printSuccess("Playlist Played With Success");
                } catch (IllegalArgumentException | UnauthorizedAccessException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            case 3: // Play album
                try{
                    String albumByID = UI.playAlbumUI();
                    Album album = manager.getAlbum(albumByID);
                    int optionForAlbum = UI.whatModeToPlayAlbum();

                    if(optionForAlbum == 0){
                        if(user.getSubscription() instanceof Free){
                        UI.printError("Free users can only play randomly");
                        return;
                    }
                        player.playAlbum(album, user);
                    } else if(optionForAlbum == 1){
                        player.playAlbumRandomly(album, user);
                    } else {
                        return;
                    }
                    UI.printSuccess("Album Played With Success");
                } catch (IllegalArgumentException e) {
                    UI.printError(e.getMessage());
                } catch (Exception e) {
                    UI.printError("Unexpected error occurred");
                }
                break;
            default:
                return;
        }
    }

    private void handleStatisticsMenu() {
        int option = UI.statisticsMenu();
        Collection<User> users = manager.getAllUsers();
        Collection<Song> songs = manager.getAllSongs();
        Collection<Artist> artists = manager.getAllArtists();
        switch (option) {
            case 1: // Most played song
                Song mostPlayedSong = stats.getMostPlayedSong(songs);
                if(mostPlayedSong != null){
                    UI.printMostPlayedSong(mostPlayedSong);
                } else {
                    UI.printError("No song played yet");
                }
                break;
            case 2: // Most listened artist
                Artist mostListenedArtist = stats.getMostListenedArtist(artists);
                if(mostListenedArtist != null){
                    UI.printMostListenedArtist(mostListenedArtist);
                } else {
                    UI.printError("No artist played yet");
                }
                List<Song> songsMostListenedArtist = mostListenedArtist.getSongs();
                int totalStreams = 0;
                for(Song song : songsMostListenedArtist){
                    totalStreams += song.getTotalStreams();
                }
                UI.printMostListenedArtistCount(totalStreams);
                break;
            case 3: // Most active user
                User mostActiveUser = stats.getMostActiveUser(users);
                if(mostActiveUser != null){
                    UI.printMostActiveUser(mostActiveUser);
                    List<History> history = mostActiveUser.getHistory();
                    int totalDuration = 0;
                    for(History entries : history){
                        Song song = entries.getSong();
                        totalDuration += song.get_duration();
                    }
                    UI.printMostActiveUserTime(totalDuration);
                } else {
                    UI.printError("No user found with most activity");
                }
                break;
            case 4: // User with most points
                User userWithMostPoints = stats.getUserWithMostPoints(users);
                if(userWithMostPoints != null){
                    UI.printUserWithMostPoints(userWithMostPoints);
                } else {
                    UI.printError("No user with most points");
                }
                break;
            case 5: // Most popular genre
                Genre mostPopularGenre = stats.getMostPopularGenre(songs);
                if(mostPopularGenre != null){
                    UI.printMostPopularGenre(mostPopularGenre);
                } else {
                    UI.printError("No genre played yet");
                }
                break;
            case 6: // Quantity of public playlists
                List<Playlist> publicPlaylists = manager.publicPlaylists();
                int numOfPublicPlaylists = stats.getQuantityOfPublicPlaylists(publicPlaylists);
                UI.getQuantityOfPublicPlaylists(numOfPublicPlaylists);
                break;
            case 7: // User with most playlists
                User userWithMostPlaylists = stats.getUserWithMostPlaylists(users);
                if(userWithMostPlaylists != null){
                    UI.printUserWithMostPlaylists(userWithMostPlaylists);
                    int numOfPlaylists = userWithMostPlaylists.getPlaylists().size();
                    UI.getQuantityOfPlaylists(numOfPlaylists);
                } else {
                    UI.printError("No user with most playlists");
                }
                break;
            default:
                return;
        }
    }

        
}
