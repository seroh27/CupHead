package Controller;

import Controller.Exceptions.EntryNullException;
import Controller.Exceptions.UserAlreadyRegisteredException;
import Controller.Exceptions.UsernameOrPasswordWrongException;
import Model.User;
import Model.UserDataBase;

public class LoginController {
    private static LoginController loginController;
    private LoginController(){}
    public static LoginController getInstance(){
        if(loginController == null)
            loginController = new LoginController();
        return loginController;
    }
    public void register(String username,String password) throws Exception {
        checkRegisterErrors(username,password);
        addUserToDataBase(username,password);
        UserDataBase.getInstance().setLoginUser(UserDataBase.getInstance().getUser(username));
    }

    public void login(String username, String password) throws Exception {
        checkLoginErrors(username,password);
        UserDataBase.getInstance().setLoginUser(UserDataBase.getInstance().getUser(username));
    }
    private void checkRegisterErrors(String username,String password) throws Exception {
        checkUsernameErrors(username);
        checkPasswordErrors(password);
    }
    private void checkLoginErrors(String username, String password) throws Exception {
        isEntryNull(username);
        isEntryNull(password);
        if(UserDataBase.getInstance().getUser(username,password) == null)
            throw new UsernameOrPasswordWrongException();
    }
    private void  checkUsernameErrors(String username) throws Exception {
        isEntryNull(username);
        User exists = UserDataBase.getInstance().getUser(username);
        if((exists != null))
            throw new UserAlreadyRegisteredException();
    }
    private void checkPasswordErrors(String password) throws Exception{
        isEntryNull(password);
    }
    private void isEntryNull(String entry) throws  Exception{
        if(entry.length() == 0)
            throw  new EntryNullException();
    }
    private void addUserToDataBase(String username, String password) {
        UserDataBase.getInstance().addUser(new User(username,password));
        UserDataBase.getInstance().saveUsers();
    }
}
