package View;

import Application.App;
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
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuPageController implements Initializable {

    public Pane pane;
    private Timeline timeline;

    public void newGame(ActionEvent actionEvent) {
        App.changeMenu(Menus.PRE_GAME.value);
    }

    public void profile(ActionEvent actionEvent) {
        App.changeMenu(Menus.PROFILE_MENU.value);
    }

    public void scoreboard(ActionEvent actionEvent) {
        App.changeMenu(Menus.SCORE_BOARD.value);
    }

    public void back(ActionEvent actionEvent) {
        App.changeMenu(Menus.LOGIN_MENU.value);
    }

    public void quitGame(ActionEvent actionEvent) {
        Music.getInstance().getMediaPlayer().stop();
        Platform.exit();}

    public void continueGame(ActionEvent actionEvent) {
        App.changeMenu(Menus.GAME_MENU.value);
    }
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
                "left " + xPosition*2 + "px bottom," +
                "left " + xPosition*2.2 + "px bottom," +
                "left " + xPosition*2.2 + "px bottom," +
                "left " + xPosition*2.2 + "px bottom;";
        region.setStyle(style);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTimeline().play();
    }
}
