package src.Model;

import java.util.List;

public class ProgramCreated extends Playlist {

    public ProgramCreated(){
        super();
    }

    public ProgramCreated(String id, List<Song> songs) {
        super(id, songs);
    }

    @Override
    public String toString() {
        return "ProgramCreated [id=" + getId() + ", songs=" + getSongs() + "]";
    }
}