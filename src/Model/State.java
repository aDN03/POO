package src.Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class State implements Serializable {

    private static final long serialVersionUID = 1L;
    private SpotifUM spotifUM;

    public State() {
        this.spotifUM = new SpotifUM();
    }

    public State(SpotifUM spotifUM) {
        this.spotifUM = spotifUM;
    }

    public SpotifUM getGeneralManager() {
        return this.spotifUM;
    }

    public void setGeneralManager(SpotifUM spotifUM) {
        this.spotifUM = spotifUM;
    }

    public void saveState() throws IOException{
        Files.createDirectories(Paths.get("src/Saves"));
        String path = "src/Saves/savefile.dat";
        FileOutputStream file = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(this);

        out.close();
        file.close();
    }

    public static State loadState() throws IOException, ClassNotFoundException {
        String path = "src/Saves/savefile.dat";
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(file);

        State loadedState = (State) in.readObject();

        in.close();
        file.close();

        return loadedState;
    }

}
