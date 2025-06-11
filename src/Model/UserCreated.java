package src.Model;

import java.util.List;

public class UserCreated extends Playlist {
	private User user;
	private boolean isPublic;

	public UserCreated(User user, boolean isPublic) {
		super();
		this.user = user;
		this.isPublic = isPublic;
	}
	public UserCreated(String id, List<Song> songs, User user, boolean isPublic) {
		super(id, songs);
		this.user = user;
		this.isPublic = isPublic;
	}
	public UserCreated(Playlist p, User user, boolean isPublic) {
		super(p);
		this.user = user;
		this.isPublic = isPublic;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String toString() {
		return super.toString() + ", isPublic=" + isPublic + "]";
	}
}