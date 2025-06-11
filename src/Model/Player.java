package src.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import src.Exceptions.PlaylistNotFoundException;
import src.Exceptions.UnauthorizedAccessException;

public class Player {

	public void playSong(Song song, User user) {
		if (song != null && user != null) {
			if (user.getSubscription() != null) {
				user.getSubscription().calculatePoints(user);
			}

			// Since printing the lyrics is the equivalent of playing the song we left the print here to simulate that 
			System.out.println("Now Playing: " + song.get_name());
			System.out.println("Lyrics:\n" + song.get_lyrics());

			History history = new History(LocalDateTime.now(), song);
			user.getHistory().add(history);
			song.setTotalStreams(song.getTotalStreams() + 1);
		} else {
			throw new IllegalArgumentException("Invalid song or user.");
		}
	}

	public void playAlbum(Album album, User user) {
		if (album == null || user == null) {
			throw new IllegalArgumentException("Invalid album or user.");
		}

		List<Song> songs = album.getSongs();
		if (songs == null || songs.isEmpty()) {
			throw new IllegalArgumentException("The album has no songs.");
		}

		for (Song song : songs) {
			playSong(song, user);
		}
	}

	public void playAlbumRandomly(Album album, User user) {
		if (album == null || user == null) {
			throw new IllegalArgumentException("Invalid album or user.");
		}

		List<Song> songs = album.getSongs();

		if (songs == null || songs.isEmpty()) {
			throw new IllegalArgumentException("The album has no songs.");
		}

		List<Song> shuffledSongs = new ArrayList<>(songs);
		Collections.shuffle(shuffledSongs);

		for (Song song : shuffledSongs) {
			playSong(song, user);
		}
	}

	public void playPlaylist(Playlist playlist, User user) {
		if (playlist == null || user == null) {
			throw new IllegalArgumentException("Invalid playlist or user.");
		}

		if(playlist instanceof UserCreated){
			UserCreated userCreated = (UserCreated) playlist;
			if(user.getId() == userCreated.getUser().getId() || userCreated.isPublic()){
				List<Song> songs = playlist.getSongs();

				for (Song song : songs) {
					playSong(song, user);
				}
			} else {
				throw new UnauthorizedAccessException("You do not have access to this private playlist.");
			}
		} else {
			for (Song song : playlist.getSongs()) {
				playSong(song, user);
			}
		}

	}

	public void playPlaylistRandomly(Playlist playlist, User user) {
		if (playlist == null || user == null) {
			throw new IllegalArgumentException("Invalid playlist or user.");
		}

		if(playlist instanceof UserCreated){
			UserCreated userCreated = (UserCreated) playlist;
			if(user.getId() == userCreated.getUser().getId() || userCreated.isPublic()){
				List<Song> songs = playlist.getSongs();

				List<Song> shuffledSongs = new ArrayList<>(songs);
				Collections.shuffle(shuffledSongs);

				for (Song song : shuffledSongs) {
					playSong(song, user);
				}
			} else {
				throw new UnauthorizedAccessException("You do not have access to this private playlist.");
			}
		} else {

			List<Song> songs = playlist.getSongs();
			List<Song> shuffledSongs = new ArrayList<>(songs);
			Collections.shuffle(shuffledSongs);

			for (Song song : shuffledSongs) {
				playSong(song, user);
			}
		}
	}

	public void playlistWithControls(User user, UserCreated playlist){
		if (!(user.getSubscription() instanceof PremiumBase || user.getSubscription() instanceof PremiumTop)) {
        	throw new UnauthorizedAccessException("Only premium users can control playback.");
    	}

		List<Song> songs = playlist.getSongs();
			if (songs.isEmpty()) {
				throw new PlaylistNotFoundException("Playlist is empty");
			}


		// This should be handled in the UI component but it's here to simulate the interaction the the controls to skip and go back
		Scanner scanner = new Scanner(System.in);
    	int index = 0;

		while (index >= 0 && index < songs.size()) {
			Song currentSong = songs.get(index);
			playSong(currentSong, user);

			System.out.println("[n] Next | [p] Previous | [q] Quit");
			String command = scanner.nextLine().trim().toLowerCase();

			switch (command) {
				case "n":
					if (index >= songs.size() - 1){
                        System.out.println("There's no next song");
						return;
                    } else {
                        index++;
                    }
                    break;
				case "p":
					if (index > 0) index--;
					else System.out.println("Already at the beginning.");
					break;
				case "q":
					return;
				default:
					System.out.println("Invalid command. Try again.");
			}
		}
		scanner.close();
	}

		public void playlistWithControlsAndShuffle(User user, UserCreated playlist){
		if (!(user.getSubscription() instanceof PremiumBase || user.getSubscription() instanceof PremiumTop)) {
        	throw new UnauthorizedAccessException("Only premium users can control playback.");
    	}

		List<Song> songs = playlist.getSongs();
			if (songs.isEmpty()) {
				throw new PlaylistNotFoundException("Playlist is empty");
			}

		List<Song> shuffledSongs = new ArrayList<>(songs);
		Collections.shuffle(shuffledSongs);


		// This should be handled in the UI component but it's here to simulate the interaction the the controls to skip and go back
		Scanner scanner = new Scanner(System.in);
    	int index = 0;

		while (index >= 0 && index < shuffledSongs.size()) {
			Song currentSong = shuffledSongs.get(index);
			playSong(currentSong, user);

			System.out.println("[n] Next | [p] Previous | [q] Quit");
			String command = scanner.nextLine().trim().toLowerCase();

			switch (command) {
				case "n":
                    if (index >= shuffledSongs.size() - 1){
                        System.out.println("There's no next song");
						return;
                    } else {
                        index++;
                    }
                    break;
				case "p":
					if (index > 0) index--;
					else System.out.println("Already at the beginning.");
					break;
				case "q":
					return;
				default:
					System.out.println("Invalid command. Try again.");
			}
		}
		scanner.close();
	}
}