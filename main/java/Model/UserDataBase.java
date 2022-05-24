package Model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UserDataBase {
    private static UserDataBase userDataBase;
    private UserDataBase(){}
    public static UserDataBase getInstance(){
        if(userDataBase == null)
            userDataBase = new UserDataBase();
        return userDataBase;
    }
    private User loginUser;

    private ArrayList<User> users = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public User getUser(String username,String password){
        for (User user: users) {
            if(username.equals(user.getUsername()) &&
                password.equals(user.getPassword()))
                return user;
        }
        return null;
    }
    public User getUser(String username){
        for (User user: users) {
            if(username.equals(user.getUsername()))
                return user;
        }
        return null;
    }
    private void setUsers(ArrayList<User> users){
        this.users = users;
    }
    public void loadUsers() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/UserDatabase.json")));
            ArrayList<User> createdUsers;
            createdUsers = new Gson().fromJson(json, new TypeToken<List<User>>() {
            }.getType());
            if (createdUsers != null) {
                setUsers(createdUsers);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveUsers() {
        try {
            FileWriter fileWriter = new FileWriter("./src/main/resources/UserDatabase.json");
            fileWriter.write(new Gson().toJson(users));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
