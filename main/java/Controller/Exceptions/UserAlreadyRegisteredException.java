package Controller.Exceptions;

public class UserAlreadyRegisteredException extends Exception{
    public  UserAlreadyRegisteredException(){
        super("user exists with this name!");
    }
}
