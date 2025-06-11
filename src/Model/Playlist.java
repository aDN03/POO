package src.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Playlist implements Serializable {
	private String id;
	public List<Song> songs;

	public Playlist() {
		this.id = "";
		this.songs = new ArrayList<>();
	}

	public Playlist(String id, List<Song> songs) {
		this.id = id;
		this.songs = songs;
	}

	public Playlist(Playlist p) {
		this.id = p.id;
		this.songs = p.songs;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public void addSong(Song song){
		this.songs.add(song);
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", songs=" + songs + "]" + "\n";
	}

}