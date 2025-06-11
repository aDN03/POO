package src.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class History implements Serializable{
	
	private LocalDateTime date;
	public Song song;

	public History() {
		this.date = null;
		this.song = null;
	}

	public History(LocalDateTime date, Song song) {
		this.date = date;
		this.song = song;
	}

	public History(History p) {
		this.date = p.date;
		this.song = p.song;
	}

	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
	@Override
		public String toString() {
			return "History [date=" + date + ", ID=" + song.get_id() + ", song=" + song.get_name() + "]";
		}
}