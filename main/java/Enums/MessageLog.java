package Enums;

public enum MessageLog {
    SUCCESSFULLY_CHANGED("username password successfully changed!"),
    EMPTY("empty...");

    public final String message;

    MessageLog(String message) {
        this.message = message;
    }
}
