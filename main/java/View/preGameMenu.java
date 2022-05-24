package View;

import Application.App;
import Enums.DifficultyLevels;
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
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class preGameMenu implements Initializable {
    public Button muteButton;
    private DifficultyLevels difficultyLevel;
    public ChoiceBox<String> difficulty;
    public ChoiceBox<String> mode;
    public ChoiceBox<String> gameColor;

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
                "left " + xPosition*2 + "px bottom," +
                "left " + xPosition*2.2 + "px bottom," +
                "left " + xPosition*2.2 + "px bottom," +
                "left " + xPosition*2.2 + "px bottom;";
        region.setStyle(style);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getTimeline().play();
        Platform.runLater(() -> muteButton.setText("mute"));
        String[] diffs = {DifficultyLevels.NORMAL_LEVEL_ONE.message,
                        DifficultyLevels.NORMAL_LEVEL_TWO.message,
                        DifficultyLevels.NORMAL_LEVEL_THREE.message};
        String[] modes = {
                        DifficultyLevels.NORMAL_MODE.message,
                        DifficultyLevels.DEVIL_MODE.message};
        String[] colors = {
                "Normal Colors",
                "Black And White"};
        mode.getItems().addAll(modes);
        gameColor.setValue("Normal Colors");
        mode.setOnAction(actionEvent -> {
            difficultyLevel = DifficultyLevels.getDifficulty(mode.getValue());
            if (mode.getValue().equals(DifficultyLevels.DEVIL_MODE.message))
                difficulty.setDisable(true);
            else
                difficulty.setDisable(false);
        });
        gameColor.getItems().addAll(colors);
        gameColor.setOnAction(actionEvent -> {
            if(gameColor.getValue().equals("Black And White")) {
                GameMenuPageController.blackAndWhite = true;
            }
        });
        difficulty.getItems().addAll(diffs);
    }

    public void mute(ActionEvent actionEvent) {
        Game.getInstance().setMusicMuted(!Game.getInstance().musicMuted());
        if(Game.getInstance().musicMuted())
            muteButton.setText("unmute");
        else
            muteButton.setText("mute");
    }

    public void startGame(ActionEvent actionEvent) {
        Music.getInstance().getMediaPlayer().stop();
        Game.getInstance().renewGame();
        App.changeMenu(Menus.GAME_MENU.value);
    }
}
