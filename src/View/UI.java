package src.View;

import java.util.*;
import src.DTO.SongTransfer;
import src.DTO.UserTransfer;
import src.Model.*;


public class UI {

    public static Scanner scanner = new Scanner(System.in);


    public static void success(Boolean success) {
            if (success){
                System.out.println("Success!");
            }else {
                System.out.println("Error! Please try again:");
            }
        }
    
    public static String login() {
        System.out.println("Welcome to SpotifUM!");
        String uid;
        System.out.println("Insert your User Id");
        uid = getStr();
        return uid;
    }

    public static void logOut() {
        System.out.println("See you next time");
    }

    public static int mainMenu() {
        View.pageName("Main Menu");
        List<String> opcoes = Arrays.asList(
                "1 --- Manage Songs",
                "2 --- Manage Album",
                "3 --- Manage User",
                "4 --- Manage Playlist",
                "5 --- Manage Artist",
                "6 --- Play Content",
                "7 --- Statistics",
                "8 --- LogOut");
        View.header(opcoes);
        return checkOption(1,8);
    }

    public static int songMenu() {
        //UI FOR MUSICS
        //===SPOTIFUM - GESTOES DE MUSICA===
        //NOVA MUSICA
        //LISTAR MUSICA
        //PESQUISAR MUSICA
        //DETALHES DE MUSICA
        //EDITAR MUSICA
        //REMOVER MUSICA
        //SAIR MENU
        View.pageName("SPOTIFUM - Manage Songs");
        List<String> opcoes = Arrays.asList(
                "1 --- Add New Song",
                "2 --- List All Songs",
                "3 --- Search Song",
                "4 --- Song Details",
                "5 --- Edit Song",
                "6 --- Remove Song",
                "7 --- Back");
        View.header(opcoes);
        return checkOption(1,7);
    }

    public static int albumMenu() {
        //UI FOR ALBUMS
        //===SPOTIFUM - GESTOES DE ALBUM===
        //NOVO ALBUM
        //LISTAR ALBUM
        //PESQUISAR ALBUM
        //DETALHES DE ALBUM
        //EDITAR ALBUM
        //REMOVER ALBUM
        //SAIR MENU
        View.pageName("SPOTIFUM - Manage Albums");
        List<String> opcoes = Arrays.asList(
                "1 --- Add New Album",
                "2 --- List All Albums",
                "3 --- Search Album",
                "4 --- Album Details",
                "5 --- Edit Album",
                "6 --- Remove Album",
                "7 --- Back");
        View.header(opcoes);
        return checkOption(1,7);
    }

    public static int userMenu() {
        //UI FOR USERS
        //===SPOTIFUM - GESTOES DE UTILIZADOR===
        //NOVO UTILIZADOR
        //LISTAR UTILIZADOR
        //PESQUISAR UTILIZADOR
        //DETALHES DE UTILIZADOR
        //EDITAR UTILIZADOR
        //REMOVER UTILIZADOR
        //MUDAR PLANO DE SUBSCRICAO
        //SAIR MENU
        View.pageName("SPOTIFUM - Manage Users");
        List<String> opcoes = Arrays.asList(
                "1 --- Add New User",
                "2 --- List All Users",
                "3 --- Search User",
                "4 --- User Details",
                "5 --- Edit User",
                "6 --- Remove User",
                "7 --- Back");
        View.header(opcoes);
        return checkOption(1,7);
    }

    public static int playlistMenu() {
        //UI FOR PLAYLISTS
        //===SPOTIFUM - GESTOES DE PLAYLIST===
        //NOVA PLAYLIST
        //LISTAR PLAYLIST
        //PESQUISAR PLAYLIST
        //DETALHES DE PLAYLIST
        //EDITAR PLAYLIST
        //REMOVER PLAYLIST
        //ADICIONAR MUSICA PARA PLAYLIST
        //REMOVER MUSICA DA PLAYLIST
        //SAIR MENU
        View.pageName("SPOTIFUM - Manage Playlists");
        List<String> opcoes = Arrays.asList(
                "1 --- Add New Playlist",
                "2 --- List All Public Playlists",
                "3 --- Playlist Details",
                "4 --- Remove Playlist",
                "5 --- Add Song to Playlist",
                "6 --- Remove Song from Playlist",
                "7 --- Generate Auto Playlist",
                "8 --- Preference Playlist Menu",
                "9 --- Back");
        View.header(opcoes);
        return checkOption(1,9);
    }

    public static int preferencePlaylistMenu(){
        View.pageName("SPOTIFUM - Manage Playlists");
        List<String> opcoes = Arrays.asList(
                "1 --- Playlist based in Genre",
                "2 --- Playlist based in Genre with max duration (in seconds)",
                "3 --- Playlist based in Genre with only explicit songs",
                "4 --- Back"
                );
        View.header(opcoes);
        return checkOption(1,4);
    }

    public static int playContentMenu() {

        //UI FOR CONTENT PLAYING
        //===SPOTIFUM - PLAY CONTENT===
        //PLAY PLAYLIST (PREMIUM)
        //PLAY RANDOM SONG (PREMIUM E NON-PREMIUM)
        //PLAY MUSICA ESPECIFICA (PREMIUM)
        //PLAY ALBUM ESPECIFICO (PREMIUM)
        //SAIR MENU

        View.pageName("SPOTIFUM - Play Content");
        List<String> opcoes = Arrays.asList(
                "1 --- Play Song",
                "2 --- Play Playlist",
                "3 --- Play Album",
                "4 --- Back");
        View.header(opcoes);
        return checkOption(1,4);
    }

    public static int statisticsMenu() {

        //UI FOR STATISTICS
        //===SPOTIFUM - ESTATISTICAS===
        //MUSICA MAIS TOCADA
        //ARTISTA MAIS TOCADO
        //UTILIZADOR MAIS ATIVO
        //UTILIZADOR COM MAIS PONTOS
        //GENERO MAIS POPULAR
        //NUMERO DE PLAYLISTS PUBLICAS
        //UTILIZADOR COM MAIS PLAYLISTS
        //SAIR MENU

        View.pageName("Statistics");
        List<String> options = Arrays.asList(
                "1 --- Most Played Song",
                "2 --- Most Listened Artist",
                "3 --- Most Active User",
                "4 --- User with Most Points",
                "5 --- Most Popular Genre",
                "6 --- Quantity of Public Playlists",
                "7 --- User with Most Playlists",
                "8 --- Back to Main Menu");
        View.header(options);
        return checkOption(1, 8);
    }

    public static int artistMenu() {
        View.pageName("SPOTIFUM - Manage Artists");
        List<String> opcoes = Arrays.asList(
                "1 --- Add New Artist",
                "2 --- List All Artists",
                "3 --- Search Artist",
                "4 --- Artist Details",
                "5 --- Edit Artist",
                "6 --- Remove Artist",
                "7 --- Back");
        View.header(opcoes);
        return checkOption(1,7);
    }


    //SONGS UI STUFF

    public static String askAlbumID(){
        System.out.println("Please insert the album ID: ");
        String ID = getStr();
        return ID;
    }

    public static SongTransfer addSongUI() {
        System.out.println("=== Add New Song ===");
    
        System.out.print("Enter song name: ");
        String name = getStr();
    
        System.out.print("Enter song lyrics: ");
        String lyrics = getStr();
    
        System.out.print("Enter genre (e.g., POP, ROCK, HIPHOP): ");
        String genre = getStr();
    
        System.out.print("Enter duration (in seconds): ");
        int duration = getNumber();
    
        System.out.print("Is the song explicit? (1 for Yes, 0 for No): ");
        boolean isExplicit = getNumber() == 1;
    
        System.out.print("Does the song have a video? (1 for Yes, 0 for No): ");
        boolean hasVideo = getNumber() == 1;
    
        System.out.print("Enter record label: ");
        String recordLabel = getStr();

        SongTransfer song = new SongTransfer(name, lyrics, genre, duration, isExplicit, hasVideo, recordLabel);
    
        return song;
    }

    public static List<String> addArtistsUI() {
        List<String> artists = new ArrayList<>();
        while(true) {
            System.out.print("Enter artist ID (0 to finish): ");
            String artistId = getStr();
            if (artistId.equals("0")) {
                break;
            }
            artists.add(artistId);
        }
        return artists;
    }

    public static void listSongUI() {
        System.out.println("=== Songs List ===");
    }

    public static void printSongs(Collection<Song> songs) {
        for (Song song :songs){
            List<Artist> artists = song.getArtists();
            if (!artists.isEmpty()) {
                        //Artist artist = artists.get(0);
                        System.out.print("ID: " + song.get_id());
                        System.out.print(", Name: " + song.get_name());
                        System.out.print(", Album Id: " + song.getAlbum().get_id());
                        System.out.print(", Album Name: " + song.getAlbum().get_name());
                        System.out.print(", Stream Count: " + song.getTotalStreams());
                        for (Artist artist : artists) {
                            System.out.print(", Artist ID: " + artist.getId());
                            System.out.print(", Artist Name: " + artist.getName() + " // \n");
                        }
                    } else {
                        System.out.println("ID: " + song.get_id() + ", Name: " + song.get_name() + ", Album Id: " + song.getAlbum().get_id() + ", Album Name: " + song.getAlbum().get_name() + ", No artist associated");
                    }
        }
    }

    public static String searchSongUI() {
        System.out.println("=== Search Song ===");
        System.out.println("Insert the song name: ");
        String songName = getStr();
        return songName;
    }

    public static void printSearchedSongs(List<Song> songsByName) {
        //Usar o nome para dar search e o song details usa o ID

        if (songsByName.isEmpty()) {
            System.out.println("No songs found.");
        } else {
            System.out.println("Songs found: ");
            for (Song song :songsByName){
                List<Artist> artists = song.getArtists();
            if (!artists.isEmpty()) {
                        //Artist artist = artists.get(0);
                        System.out.print("ID: " + song.get_id() + ", Name: " + song.get_name() + ", Album Id: " + song.getAlbum().get_id() + ", Album Name: " + song.getAlbum().get_name() + ", Duration: " + song.get_duration() + "s" + ", Stream Count: " + song.getTotalStreams());
                        for (Artist artist : artists) {
                            System.out.print(", Artist ID: " + artist.getId());
                            System.out.print(", Artist Name: " + artist.getName() + " // \n");
                        }
                    } else {
                        System.out.println("ID: " + song.get_id() + ", Name: " + song.get_name() + ", Album Id: " + song.getAlbum().get_id() + ", Album Name: " + song.getAlbum().get_name() +  song.get_duration() + "s" +", No artist associated");
                    }
            }
        }

    }

    public static String songDetailsUI() {
        System.out.println("=== Song Details ===");
        System.out.println("Insert the song ID: ");
        String songById = getStr();
        return songById;
    }

    public static void printSongDetails(Song song) {
        if (song == null) {
            System.out.println("Song not found.");
        } else {
            if(song instanceof ExplicitSong){
                System.out.println(song + " | Explicit: Yes");
            } else {
                System.out.println(song + " | Explicit: No");
            }
        }
    }

    public static String editSongUI() {
        System.out.println("=== Edit Song ===");
        System.out.println("Insert the song ID: ");
        String songById = getStr();
        return songById;
    }

    public static SongTransfer startEditSongUI() {
            SongTransfer song = new SongTransfer();

            System.out.println("What do you want to edit?");
            System.out.println("1 --- Name");
            System.out.println("2 --- Lyrics");
            System.out.println("3 --- Genre");
            System.out.println("4 --- Duration");
            System.out.println("5 --- Explicit");
            System.out.println("6 --- Video");
            System.out.println("7 --- Record Label");
            System.out.println("8 --- Music Sheet");
            System.out.println("9 --- Back to Main Menu");

            int option = getNumber();
            switch (option) {
                case 1:
                    System.out.println("Enter new name: ");
                    String newName = getStr();
                    song.setName(newName);
                    break;
                case 2:
                    System.out.println("Enter new lyrics: ");
                    String newLyrics = getStr();
                    song.setLyrics(newLyrics);
                    break;
                case 3:
                    System.out.println("Enter new genre (e.g., POP, ROCK, HIPHOP): ");
                    String genre = getStr();
                    song.setGenre(genre);
                    break;
                case 4:
                    System.out.println("Enter new duration (in seconds): ");
                    int newDuration = getNumber();
                    song.setDuration(newDuration);
                    break;
                case 5:
                    System.out.println("Enter new explicit (1=yes or 0=no): ");
                    boolean newExplicit = getNumber() == 1;
                    song.setExplicit(newExplicit);
                    break;
                case 6:
                    System.out.println("Enter new video (1=yes or 0=no): ");
                    boolean newVideo = getNumber() == 1;
                    song.setHasVideo(newVideo);
                    break;
                case 7:
                    System.out.println("Enter new record label: ");
                    String newRecordLabel = getStr();
                    song.setRecordLabel(newRecordLabel);
                    break;
                case 8:
                    System.out.println("Enter the number of lines in the music sheet: ");
                    int numLines = getNumber();

                    if (numLines <= 0) {
                        System.out.println("Invalid number of lines. Please try again.");
                        break;
                    }

                    System.out.println("Enter the music sheet line by line:");

                    for (int i = 1; i <= numLines; i++) {
                        System.out.print("Enter line " + i + ": ");
                        String notes = getStr();
                        song.addMusicLine(notes);
                    }

                    break;

                case 9:
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
            return song;
        }

    public static String removeSongUI() {
        System.out.println("=== Remove Song ===");
        System.out.println("Insert the song ID: ");
        String songById = getStr();
        return songById;
    }

    //ALBUMS UI STUFF

    public static String addAlbumUI() {
        System.out.println("=== Add New Album ===");
        System.out.println("Enter album name: ");
        String name = getStr();
        return name;
    }

    public static void listAlbumUI(Collection<Album> album) {
        System.out.println("=== Albums List ===");
        for (Album albums : album){

            List<String> songs = new ArrayList<>();
            for (Song song : albums.getSongs()){
                String info = "Song Id: " + song.get_id() + ", Song Name: " + song.get_name() + "|";
                songs.add(info);
            }
            System.out.println("ID: " + albums.get_id() + ", Name: " + albums.get_name() + ", Songs: " + songs);

        }
    }

    public static String searchAlbumUI() {
        System.out.println("=== Search Album ===");
        System.out.println("Insert the album name: ");
        String albumName = getStr();
        return albumName;
    }

    public static void printSearchedAlbums(List<Album> albumsByName) {
        //Usar o nome para dar search e o song details usa o ID

        if (albumsByName.isEmpty()) {
            System.out.println("No albums found.");
        } else {
            System.out.println("Albums found: ");
            for (Album album :albumsByName){
                System.out.println("ID: " + album.get_id() + ", Name: " + album.get_name() + ", Songs: " + album.getSongs());
            }
        }

    }

    public static String albumDetailsUI() {
        System.out.println("=== Album Details ===");
        System.out.println("Insert the album ID: ");
        String albumById = getStr();
        return albumById;
    }

    public static void printAlbumDetails(Album album) {
        if (album == null) {
            System.out.println("Album not found.");
        } else {
            System.out.println(album);
        }
    }

    public static String editAlbumUI() {
        System.out.println("=== Edit Album ===");
        System.out.println("Insert the Album ID: ");
        String albumIdToEdit = getStr();
        return albumIdToEdit;
    }

    public static String startEditAlbumUI() {
            System.out.println("1 --- Edit Album Name");
            System.out.println("2 --- Back to Main Menu");

            int option = getNumber();
            String newName = "";

            switch (option) {
                case 1:
                    System.out.println("Enter new album name: ");
                    newName = getStr();
                    break;
                case 2:
                    System.out.println("Back to Main Menu");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
            return newName;
    }

    public static String removeAlbumUI() {
        System.out.println("=== Remove Album ===");
        System.out.println("Insert the album ID: ");
        String albumIdForRemoval = getStr();
        return albumIdForRemoval;
    }

    //USERS UI STUFF

    public static UserTransfer addUserUI() {
        System.out.println("=== Add New User ===");

        System.out.println("Enter your name: ");
        String name = getStr();

        System.out.println("Enter your email: ");
        String email = getStr();

        System.out.println("Enter your address: ");
        String address = getStr();

        System.out.println("Select your subscription: ");
        System.out.println("1 --- Free");
        System.out.println("2 --- Premium Base");
        System.out.println("3 --- Premium Top");

        int subscriptonOption = checkOption(1,3);

        UserTransfer user = new UserTransfer();

        switch (subscriptonOption) {
            case 1:
                user = new UserTransfer(name, email, address, "Free");
                break;
            case 2:
                user = new UserTransfer(name, email, address, "PremiumBase");
                break;
            case 3:
                user = new UserTransfer(name, email, address, "PremiumTop");
        }

        return user;
    }

    public static void userCreatedID(User user) {
        System.out.println("User created: " + user.getName() + " with ID: " + user.getId());
    }

    public static void listUserUI() {
        System.out.println("=== Users List ===");
    }

    public static void printUsers(Collection<User> users) {
        for(User user : users){
            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail() + ", Subscription Type: " + user.getSubscription());
        }
    }

    public static String searchUserUI() {
        System.out.println("=== Search User ===");
        System.out.println("Insert the user name: ");
        String userName = getStr();
        return userName;
    }

    public static void printSearchedUsers(List<User> usersByName){
        if (usersByName.isEmpty()){
            System.out.println("No users found.");
        } else {
            System.out.println("Users found: ");
            for (User user :usersByName){
                System.out.println(user);
            }
        }
    }

    public static String userDetailsUI() {
        System.out.println("=== User Details ===");
        System.out.println("Insert the user ID: ");
        String userByID = getStr();
        return userByID;
    }

    public static void printHistory(List<History> history) {
        if (history.isEmpty()){
            System.out.println("No history found.");
        } else {
            System.out.println("History found: ");
            for (History history1 :history){
                System.out.println(history1);
            }
        }
    }

    public static void printUserDetails(User user) {
        if (user == null) {
            System.out.println("User not found.");
        } else {
            List<Playlist> playlists = user.getPlaylists();
            System.out.println("User Name: " + user.getName() + ", User ID: " + user.getId() + ", User Email: " + user.getEmail() + ", User Address: " + user.getAddress() + ", User Subscription: " + user.getSubscription() + ", User Points: " + user.getPoints() + ", Playlists: ");
            for(Playlist playlist : playlists){
                System.out.print(playlist.getId() + " | ");
            }
        }
    }

    public static void printUserLessDetails(User user) {
        if (user == null) {
            System.out.println("User not found.");
        } else {
            System.out.println("User Name: " + user.getName() + ", User ID: " + user.getId());
        }
    }

    public static String editUserUI() {
        System.out.println("=== Edit User ===");
        System.out.println("Insert the user ID: ");
        String userById = getStr();
        return userById;
    }

    public static UserTransfer startEditUserUI(){
        UserTransfer user = new UserTransfer();
            System.out.println("What do you want to edit?");
            System.out.println("1 --- Name");
            System.out.println("2 --- Email");
            System.out.println("3 --- Address");
            System.out.println("4 --- Subscription");
            System.out.println("5 --- Back to Main Menu");

            int option = getNumber();

            switch (option) {
                case 1:
                    System.out.println("Enter new name: ");
                    String newName = getStr();
                    user.setName(newName);
                    break;
                case 2:
                    System.out.println("Enter new email: ");
                    String newEmail = getStr();
                    user.setEmail(newEmail);
                    break;
                case 3:
                    System.out.println("Enter new address: ");
                    String newAddress = getStr();
                    user.setAddress(newAddress);
                    break;
                case 4:
                    System.out.println("Select your subscription: ");
                    System.out.println("1 --- Free");
                    System.out.println("2 --- Premium Base");
                    System.out.println("3 --- Premium Top");

                    int subscriptonOption = checkOption(1, 3);

                    switch (subscriptonOption) {
                        case 1:
                            user.setSubscription("Free");
                            break;
                        case 2:
                            user.setSubscription("PremiumBase");
                            break;
                        case 3:
                            user.setSubscription("PremiumTop");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("Back to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        
        return user;
    }

    public static String removeUserUI() {
        System.out.println("=== Remove User ===");
        System.out.println("Insert the user ID: ");
        String userById = getStr();
        return userById;
    }

    public static String changeSubscriptionPlanUI() {
        System.out.println("=== Change Subscription Plan ===");
        System.out.println("Insert the user ID: ");
        String userById = getStr();
        return userById;
    }

    //PLAYLISTS UI STUFF

    public static void addPlaylistUI() {
        System.out.println("=== Add New Playlist ===");
    }

    public static void isPublicUI(UserCreated playlist){
        System.out.println("Playlist public: " + playlist.isPublic());
    }

    public static void addAutoPlaylistUI() {
        System.out.println("=== Add New Auto Generated Playlist ===");
    }

    public static void preferencePlaylistUI() {
        System.out.println("=== Add New Auto Generated Playlist Based On Preferences ===");
    }

    public static int askForDuration(){
        System.out.println("Insert the total duration of the playlist in minutes");
        int time = getNumber();
        return time;
    }

    public static boolean isPlaylistPublic(){
        System.out.println("Is the playlist public? (1 - YES / 0 - NO): ");
        Integer isPublic = getNumber();
        return isPublic == 1;
    }

    public static void createdPlaylistUI() {
        System.out.println("Playlist created.");
    }

    public static void listPlaylistUI() {
        System.out.println("=== Public Playlists List ===");
    }

    public static void printPlaylistUI(Collection<Playlist> playlists) {
        if (playlists.isEmpty()) {
            System.out.println("No playlists found.");
        } else {
            System.out.println("Playlists found: ");
            System.out.println("");
            for (Playlist playlist : playlists) {
                System.out.println("Playlist ID: " + playlist.getId());
                for (Song song : playlist.getSongs()) {
                    System.out.println("  Song ID: " + song.get_id() + ", Name: " + song.get_name());
            }
            System.out.println("---//---");
        }
    }
    }

    public static String playlistDetailsUI() {
        System.out.println("=== Playlist Details ===");
        System.out.println("Insert the playlist ID: ");
        String playlistByID = getStr();
        return playlistByID;
    }

    public static void searchPlaylistUI(Playlist playlist){
        if (playlist == null) {
            System.out.println("Playlist not found.");
        } else {
            List<Song> songs = playlist.getSongs();
            for (Song song : songs) {
                System.out.println("Song ID: " + song.get_id() + ", Name: " + song.get_name());
            }
        }
    }

    public static String removePlaylistUI() {
        System.out.println("=== Remove Playlist ===");
        System.out.println("Insert the playlist ID: ");
        String playlistById = getStr();
        return playlistById;
    }

    public static String addSongToPlaylistUI() {
        System.out.println("=== Add Song to Playlist ===");
        System.out.println("Insert the playlist ID: ");
        String playlistByID = getStr();
        return playlistByID;
    }

    public static Integer numberOfSongsToPlaylist(){
        System.out.println("Insert the number of songs: ");
        Integer numberOfSongs = getNumber();
        return numberOfSongs;
    }

    public static String songToAddPlaylistID(){
        System.out.println("Insert the song ID: ");
        String songByID = getStr();
        return songByID;
    }

    public static String removeSongFromPlaylistUI() {
        System.out.println("=== Remove Song from Playlist ===");
        System.out.println("Insert the playlist ID: ");
        String playlistByID = getStr();
        return playlistByID;
    }

    public static String removeSongPlayListUI(){
        System.out.println("Insert the song ID: ");
        String songByID = getStr();
        return songByID;
    }

    //PLAY ARTIST UI STUFF

    public static Artist addArtistUI() {
        System.out.println("=== Add New Artist ===");
        System.out.println("Enter the artist name: ");
        String name = getStr();

        Artist artist = new Artist();
        artist.setName(name);

        return artist;
    }

    public static void listArtistUI(){
        System.out.println("=== Artist List ===");
    }

    public static void printArtists(Collection<Artist> artists){
        for (Artist artist : artists) {
            System.out.println("Artist ID: " + artist.getId() + ", Name: " + artist.getName());
            List<Song> songs = artist.getSongs();
            for (Song song : songs) {
                System.out.print(" Song ID: " + song.get_id() + ", Name: " + song.get_name() + " // ");
            }
            System.out.println("\n");
        }
    }

    public static String searchArtistUI() {
        System.out.println("=== Search Artist ===");
        System.out.println("Insert the artist name: ");
        String artistName = getStr();
        return artistName;
    }

    public static void printSearchedArtists(List<Artist> artistsByName){
        if (artistsByName.isEmpty()){
            System.out.println("No artists found.");
        } else {
            System.out.println("Artists found: ");
            for (Artist artist : artistsByName){
                System.out.println(artist);
            }
        }
    }

    public static String artistDetailsUI() {
        System.out.println("=== Artist Details ===");
        System.out.println("Insert the artist ID: ");
        String artistByID = getStr();
        return artistByID;
    }

    public static void printArtistDetails(Artist artist) {
        if (artist == null) {
            System.out.println("Artist not found.");
        } else {
            System.out.println(artist);
        }
    }

    public static String editArtistUI() {
        System.out.println("=== Edit Artist ===");
        System.out.println("Insert the artist ID: ");
        String artistById = getStr();
        return artistById;
    }

    public static Artist startEditArtistUI(Artist artist){
        if (artist == null){
            System.out.println("Artist not found.");
        } else {
            System.out.println("Artist found: " + artist.getName());
            System.out.println("What do you want to edit?");
            System.out.println("1 --- Name");
            System.out.println("2 --- Back to Main Menu");

            int option = getNumber();

            switch (option) {
                case 1:
                    System.out.println("Enter new name: ");
                    String newName = getStr();
                    artist.setName(newName);
                    break;
                case 2:
                    System.out.println("Back to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
        return artist;
    }

    public static String removeArtistUI() {
        System.out.println("=== Remove Artist ===");
        System.out.println("Insert the artist ID: ");
        String artistById = getStr();
        return artistById;
    }

    //PLAY CONTENT UI STUFF

    

    public static String playSongUI() {
        System.out.println("=== Play Song ===");
        System.out.println("Insert the song ID: ");
        String songById = getStr();
        return songById;
    }

    public static void printLyrics(String lyrics){
        System.out.println(lyrics);
    }

    public static String playPlaylistUI() {
        System.out.println("=== Play Playlist ===");
        System.out.println("Insert the playlist ID: ");
        String playlistById = getStr();
        return playlistById;
    }


    public static int whatModeToPlay(){
        System.out.println("Select the playlist mode:");
        System.out.println("0 --- Normal");
        System.out.println("1 --- Random");
        System.out.println("2 --- Back to Menu");
        
        int option = getNumber();

        if (option >= 0 && option <= 2) {
            return option;
        } else {
            option = 2;
            System.out.println("Invalid option. Please try again.");
        }
        return option;
    }

    public static String playAlbumUI() {
        System.out.println("=== Play Album ===");
        System.out.println("Insert the album ID: ");
        String albumById = getStr();
        return albumById;
    }


    public static int whatModeToPlayAlbum(){
        System.out.println("Select the album mode:");
        System.out.println("0 --- Normal");
        System.out.println("1 --- Random");
        System.out.println("2 --- Back to Menu");
        
        int option = getNumber();

        if (option >= 0 && option <= 2) {
            return option;
        } else {
            option = 2;
            System.out.println("Invalid option. Please try again.");
        }
        return option;
    }

    // STATISTICS UI STUFF

    public static void printMostPlayedSong(Song song){
        System.out.println("=== Most Played Song ===");
        System.out.println("Song ID: " + song.get_id() + ", Song Name: " + song.get_name() + ", Total Streams: " + song.getTotalStreams());
    }

    public static void getQuantityOfPublicPlaylists(int numOfPlaylist){
        System.out.println("=== Number Of Public Playlists ===");
        System.out.println("Number of public playlists: " + numOfPlaylist);
    }

    public static void printUserWithMostPoints(User user){
        System.out.println("=== User With Most Points ===");
        System.out.println("User ID: " + user.getId() + ", Username: " + user.getName() + ", Total Points: " + user.getPoints());
    }

    public static void printMostActiveUser(User user){
        System.out.println("=== User With Most Points ===");
        System.out.println("User ID: " + user.getId() + ", Username: " + user.getName());
    }

    public static void printMostActiveUserTime(int totalDuration){
        System.out.print(", Total Duration: " + totalDuration + "s");
    }

    public static void printMostListenedArtist(Artist artist){
        System.out.println("=== Most Listened Artist ===");
        System.out.println("Artist ID: " + artist.getId() + ", Artist Name: " + artist.getName() + ", ");
    }

    public static void printMostListenedArtistCount(int totalStreams){
        System.out.print("Total Streams: " + totalStreams);
    }

    public static void printMostPopularGenre(Genre genre){
        System.out.println("=== Most Popular Genre ===");
        System.out.println("Genre Name: " + genre);
    }

    public static void printUserWithMostPlaylists(User user){
        System.out.println("=== User With Most Playlists ===");
        System.out.println("User ID: " + user.getId() + ", Username: " + user.getName());
    }

    public static void getQuantityOfPlaylists(int numOfPlaylist){
        System.out.println("Number of playlists: " + numOfPlaylist);
    }

    //TEMOS DE CHECAR ESTA PARTE DEPOIS

    public static void musicSheetUI(Song song) {

        System.out.print("How many lines does the music sheet have? ");
        int numLines = getNumber();

        for (int i = 1; i <= numLines; i++) {
            System.out.print("Enter line " + i + ": ");
            String notes = getStr();
            song.add_Music_line(notes);
        }

    }

    public static String getStr() {
        try {
            String str = scanner.nextLine();
            return str;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static int getNumber() {
        int number;
        try {
            number = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
        return number;
    }

    public static int checkOption(int a, int b) {
        int option = getNumber();
        while (option < a || option > b) {
            System.out.println("Invalid option, please try again");
            option = getNumber();
        }
        return option;
    }

    public static void printError(String message) {
        System.out.println("ERROR: " + message);
    }

    public static void printSuccess(String message) {
        System.out.println("Success: " + message);
    }

}
