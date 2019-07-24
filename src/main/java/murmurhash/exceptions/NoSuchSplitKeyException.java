package murmurhash.exceptions;

/**
 * @author y.glushenkov
 */
public class NoSuchSplitKeyException extends RuntimeException {
    public NoSuchSplitKeyException(String msg) {
        super(msg);
    }
}