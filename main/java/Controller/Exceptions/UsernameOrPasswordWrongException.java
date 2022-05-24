package Controller.Exceptions;

public class UsernameOrPasswordWrongException extends  Exception{
    public UsernameOrPasswordWrongException(){
        super("username or password was wrong");
    }
}
