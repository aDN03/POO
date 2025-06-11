package src.Model;

import java.util.List;

public class PreferencePlaylist extends Playlist {
    private User user;
	private final boolean isPublic = false;

    public PreferencePlaylist(User user) {
		super();
		this.user = user;
	}
	public PreferencePlaylist(String id, List<Song> songs, User user) {
		super(id, songs);
		this.user = user;
	}
	public PreferencePlaylist(Playlist p, User user) {
		super(p);
		this.user = user;
	}

    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String toString() {
		return super.toString() + ", isPublic=" + isPublic + "]";
	}

    public boolean isPublic() {
        return false;
    }
}
