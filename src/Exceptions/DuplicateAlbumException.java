package src.Exceptions;

public class DuplicateAlbumException extends RuntimeException {
    public DuplicateAlbumException(String message) {
        super(message);
    }
}
