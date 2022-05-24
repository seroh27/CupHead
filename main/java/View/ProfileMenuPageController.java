package View;

import Application.App;
import Enums.Menus;
import Model.UserDataBase;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileMenuPageController implements Initializable {
    @FXML
    public Button logout;
    @FXML
    public Button removeAccount;
    @FXML
    public Button chooseAvatar;
    public AnchorPane pane;

    private Timeline timeline;
    public Timeline getTimeline(){
        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        xPosition.addListener((observable, oldValue, newValue) -> setBackgroundPositions(pane, xPosition.get()));
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(200), new KeyValue(xPosition, -15000)));

        return timeline;
    }
    void setBackgroundPositions(Region region, double xPosition) {
        String style = "-fx-background-position: " +
                "left " + xPosition*1 + "px top," +
                "left " + xPosition*1.5 + "px top," +
                "left " + xPosition*1.8 + "px bottom," +
                "left " + xPosition*1.6 + "px bottom," +
                "left " + xPosition*2. + "px bottom," +
                "left " + xPosition*2.3 + "px bottom;" ;
        region.setStyle(style);
    }

    public void changeUserPass(){
        App.changeMenu(Menus.CHANGE_USERNAME_PASSWORD_MENU.value);
    }

    public void back() {
        App.changeMenu(Menus.MAIN_MENU.value);
    }

    public void removeAccount() {
        UserDataBase.getInstance().getUsers().remove(UserDataBase.getInstance().getLoginUser());
        UserDataBase.getInstance().setLoginUser(null);
        App.changeMenu(Menus.LOGIN_MENU.value);
    }

    public void chooseAvatar() {
        App.changeMenu(Menus.CHOOSE_AVATAR.value);
    }

    public void logout() {
        UserDataBase.getInstance().setLoginUser(null);
        App.changeMenu(Menus.LOGIN_MENU.value);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTimeline().play();
    }
}
