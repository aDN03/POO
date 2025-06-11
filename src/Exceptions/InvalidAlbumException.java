package src.Exceptions;

public class InvalidAlbumException extends RuntimeException {
    public InvalidAlbumException(String message) {
        super(message);
    }
}
