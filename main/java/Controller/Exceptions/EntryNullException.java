package Controller.Exceptions;

public class EntryNullException extends Exception{
    public EntryNullException(){
        super("at least one entry is null!");
    }
}
