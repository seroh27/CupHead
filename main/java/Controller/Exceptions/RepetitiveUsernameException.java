package Controller.Exceptions;

public class RepetitiveUsernameException extends Exception{
    public RepetitiveUsernameException() {
        super("please enter a new username");
    }
}
