package src.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable, Comparable<User>{

    private String id;
    private String name;
    private String email;
    private String address;
    private Subscription subscription;
    private double points;
    private boolean isAdmin;
    private List<Playlist> playlists;
    private List<History> history;

    private static final long serialVersionUID = 1L;

    public User() {
        this.id = "";
        this.name = "";
        this.email = "";
        this.address = "";
        this.subscription = new Free();
        this.points = 0.0;
        this.isAdmin = false;
        this.playlists = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    public User(String id, String name, String email, String address, Subscription subscription, double points, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.subscription = subscription;
        this.points = points;
        this.isAdmin = isAdmin;
    }

    public User(User u) {
        this.id = u.id;
        this.name = u.name;
        this.email = u.email;
        this.address = u.address;
        this.subscription = u.subscription;
        this.points = u.points;
        this.isAdmin = u.isAdmin;
        this.playlists = u.playlists;
        this.history = u.history;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String new_id) {
        this.id = new_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String new_name) {
        this.name = new_name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String new_email) {
        this.email = new_email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String new_address) {
        this.address = new_address;
    }

    public Subscription getSubscription() {
        return this.subscription;
    }

    public void setSubscription(Subscription new_subscription) {
        this.subscription = new_subscription;
    }

    public Double getPoints() {
        return this.points;
    }

    public void setPoints(Double new_points) {
        this.points = new_points;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public List<Playlist> getPlaylists(){
       return this.playlists;
    }

    public void setPlaylist(List<Playlist> playlists){
        this.playlists = playlists;
    }

    public void addPlaylist(Playlist playlist){
        this.playlists.add(playlist);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", subscription=" + subscription +
                ", points=" + points +
                ", isAdmin=" + isAdmin +
                ", playlists= " + playlists +
                '}';
    }

    @Override
    public int compareTo(User other) {
        return Double.compare(this.getPoints(), other.getPoints());
    }

    
}