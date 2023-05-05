package exmp.exceptions;

import java.io.IOException;

public class GameLoadException extends IOException {
    public GameLoadException(String message) {
        super(message);
    }

    public GameLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
