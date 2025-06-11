package src.Model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Stats {

    public Song getMostPlayedSong(Collection<Song> songs) {
        return songs.isEmpty() ? null : Collections.max(songs);
    }

    public int getQuantityOfPublicPlaylists(List<Playlist> publicPlaylists){
        return publicPlaylists.size();
    }

    public User getUserWithMostPoints(Collection<User> users){
        return users.isEmpty() ? null : Collections.max(users);
    }

    public User getMostActiveUser(Collection<User> users){

        if(users.isEmpty()){
            return null;
        }

        User mostActiveUser = null;
        int maxDuration = -1;

        for(User user: users){
            List<History> history = user.getHistory();
            int totalDuration = 0;

            if(history != null){

                for(History entries : history){
                    Song song = entries.getSong();
                    totalDuration += song.get_duration();
                }

                if(totalDuration > maxDuration){
                    mostActiveUser = user;
                    maxDuration = totalDuration;
                }
            }
        }
        return mostActiveUser;
    }

    public Artist getMostListenedArtist(Collection<Artist> artists){

        if(artists.isEmpty()){
            return null;
        }

        Artist mostListenedArtist = null;
        int maxStreams = -1;

        for(Artist artist : artists){
            List<Song> songs = artist.getSongs();
            int totalStreams = 0;

            if(songs != null){
                for(Song song : songs){
                    totalStreams += song.getTotalStreams();
                }

                if(totalStreams > maxStreams){
                    mostListenedArtist = artist;
                    maxStreams = totalStreams;
                }
            }
        }
        return mostListenedArtist;

    }

    public Genre getMostPopularGenre(Collection<Song> songs){

        if(songs.isEmpty()){
            return null;
        }

        Genre mostPopularGenre = null;
        int maxStreams = -1;

        for(Genre genre : Genre.values()){
            int totalStreams = 0;
            for(Song song : songs){
                if(song.getGenre() == genre){
                    totalStreams += song.getTotalStreams();
                }
            }
            if(totalStreams > maxStreams){
                mostPopularGenre = genre;
                maxStreams = totalStreams;
            }
        }
        return mostPopularGenre;
    }

    public User getUserWithMostPlaylists(Collection<User> users){
        if(users.isEmpty()){
            return null;
        }

        User userWithMostPlaylists = null;
        int maxPlaylists = 0;

        for(User user : users){
            List<Playlist> playlists = user.getPlaylists();
            if(playlists != null){
                if(playlists.size() > maxPlaylists){
                    maxPlaylists = playlists.size();
                    userWithMostPlaylists = user;
                }
            }
        }
        return userWithMostPlaylists;

    }
}
