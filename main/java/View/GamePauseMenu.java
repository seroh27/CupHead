package View;

import Enums.Menus;
import Model.Game;
import Model.Music;
import javafx.event.ActionEvent;
import Application.App;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;


public class GamePauseMenu  implements Initializable {

    public Label timeLabel;
    public ImageView deadbossPic;
    public Label quote;
    public Label score;

    public void saveGame(ActionEvent actionEvent) {
        App.changeMenu(Menus.MAIN_MENU.value);
        //Game.getInstance().saveGame();
    }
    public void continueGame(){
        Music.getInstance().getMediaPlayer().stop();
        App.changeMenu(Menus.GAME_MENU.value);
    }
    public void quitGame(ActionEvent actionEvent) {
        App.changeMenu(Menus.MAIN_MENU.value);
    }

    public void restart(ActionEvent actionEvent) {
        Game.getInstance().renewGame();
        App.changeMenu(Menus.GAME_MENU.value);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Game.getInstance().isGameEnded()){
            timeLabel.setVisible(true);
            score.setVisible(true);
            quote.setVisible(true);
            deadbossPic.setVisible(true);
        }
        Music.getInstance().initMusic("LoginMenuMusic.wav");
        Music.getInstance().playSong();
        timeLabel.setText("Time : " + Game.getInstance().getTimeLabel().getText());
        score.setText(Game.getInstance().getScoreLabel().getText());
    }

    public void muteMusic(ActionEvent actionEvent) {
        if(Game.getInstance().musicMuted())
            Music.getInstance().getMediaPlayer().setMute(true);
        else {
            Music.getInstance().getMediaPlayer().setMute(false);
        }
    }
}
