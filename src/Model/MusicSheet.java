package src.Model;

import java.io.Serializable;

public class MusicSheet implements Serializable {

    private static final long serialVersionUID = 1L;
    private String line;

    public MusicSheet() {
        this.line = "";
    }
    public MusicSheet(String s) {
        this.line = s;
    }
    public MusicSheet(MusicSheet ms) {
        this.line = ms.line;
    }

    public String get_line() {
        return this.line;
    }

    public void set_line(String s) {
        this.line = s;
    }

    @Override
    public String toString() {

        return "MusicSheet{" +
                "line='" + line + '\'' +
                '}';
    }
}
