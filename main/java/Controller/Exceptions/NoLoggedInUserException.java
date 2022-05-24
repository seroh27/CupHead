package Controller.Exceptions;

public class NoLoggedInUserException extends Exception{
    public NoLoggedInUserException() {
        super("please login first");
    }
}
