package src.Exceptions;

public class DuplicateArtistException extends RuntimeException {
    public DuplicateArtistException(String message) {
        super(message);
    }
}
