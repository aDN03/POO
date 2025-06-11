package src.Exceptions;

public class DuplicateSongException extends RuntimeException {
    public DuplicateSongException(String message) {
        super(message);
    }
}
