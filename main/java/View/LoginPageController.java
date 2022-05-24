package View;

import Application.App;
import Controller.LoginController;
import Enums.Menus;
import Model.Game;
import Model.Music;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginPageController implements Initializable {
    public Pane pane;
    @FXML
    public Label errorMessage;
    @FXML
    private TextField password;
    @FXML
    private TextField username;
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
                "left " + xPosition*1.5 + "px top;";
        region.setStyle(style);
    }
    public void register(ActionEvent mouseEvent) {
        try {
            LoginController.getInstance().register(username.getText(), password.getText());
        } catch (Exception e) {
            this.errorMessage.setVisible(true);
            this.errorMessage.setText(e.getMessage());
            return;
        }
        App.changeMenu(Menus.MAIN_MENU.value);
    }
    public void login(ActionEvent mouseEvent) {
        try {
            LoginController.getInstance().login(username.getText(),password.getText());
        } catch (Exception e) {
            this.errorMessage.setVisible(true);
            this.errorMessage.setText(e.getMessage());
            return;
        }
        App.changeMenu(Menus.MAIN_MENU.value);
    }
    public void skip(ActionEvent mouseEvent) {
        App.changeMenu(Menus.MAIN_MENU.value);
    }

    public void quit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void usernameTyping(ActionEvent actionEvent) {
        this.errorMessage.setVisible(false);
    }

    public void passwordTyping(ActionEvent actionEvent) {
        this.errorMessage.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTimeline().play();
        Music.getInstance().initMusic("LoginMenuMusic.wav");
        Music.getInstance().playSong();
    }
}
