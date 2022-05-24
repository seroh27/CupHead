package View;

import Application.App;
import Enums.DifficultyLevels;
import Enums.Menus;
import Enums.ShootingMode;
import Model.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class GameMenuPageController implements Initializable {
    public ImageView shootingModeImage;
    public Label hpLabel;
    public Label scoreLabel;
    public  Label timeLabel;
    public static boolean blackAndWhite = false;
    @FXML
    private Label bossHpLabel;
    @FXML
    private AnchorPane content;
    @FXML
    private ProgressBar bossProgressBar;
    private Timeline timeline;
    private Duration miniBossLastSpawnTime;
    private Duration miniBossSpawnTime;
    private Duration bossShootTime;
    private Duration bossShootLastTime;
    private MiniBoss lastMiniBoss;
    private int numberOfMinibossesSpawned = 3;

    private Duration hitTimer;
    private Duration gameStartTime;
    private Duration initialHitTimer;
    private double opacity = 1;


    public void addImages(){
        Boss boss = Game.getInstance().getBoss();
        Plane plane = Game.getInstance().getPlane();
        boss.setX(900);plane.setX(100);
        boss.setY(100);plane.setY(200);
        content.getChildren().add(boss);
        content.getChildren().add(plane);
    }
    public Timeline getTimeline(){
        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        xPosition.addListener((observable, oldValue, newValue) -> setBackgroundPositions(content, xPosition.get()));
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(200), new KeyValue(xPosition, -15000)));

        return timeline;
    }
    private double changeOpacity(){
        if(opacity == 0.3)
            opacity = 1;
        else
            opacity = 0.3;
        return opacity;
    }
    void setBackgroundPositions(Region region, double xPosition) {
        double amount = timeline.getCurrentTime().subtract(gameStartTime).toSeconds();
        timeLabel.setText((int)timeline.getCurrentTime().subtract(gameStartTime).toMinutes() + " : " + (int)(timeline.getCurrentTime().subtract(gameStartTime).toSeconds() % 60) );
        if(Game.getInstance().getPlane().isHitBySomething() && initialHitTimer == null) {
            Game.getInstance().getPlane().setOpacity(changeOpacity());
            hitTimer = timeline.getCurrentTime();
            initialHitTimer = timeline.getCurrentTime();
        }
        if(Game.getInstance().getPlane().isHitBySomething() && timeline.getCurrentTime().subtract(hitTimer).greaterThan(new Duration(100))) {
            Game.getInstance().getPlane().setOpacity(changeOpacity());
            hitTimer = timeline.getCurrentTime();
        }
        if(Game.getInstance().getPlane().isHitBySomething()  && timeline.getCurrentTime().subtract(initialHitTimer).greaterThan(new Duration(1000))) {
            initialHitTimer = null;
            Game.getInstance().getPlane().setHitBySomething(false);
        }
        if(Game.getInstance().getBoss().getHp() <= 0 || Game.getInstance().getPlane().getHp() <= 0) {
            endGame();
        }
        hpLabel.setText("HP : " + Game.getInstance().getPlane().getHp());
        scoreLabel.setText("Score : " + (int)(amount / 2));
        Game.getInstance().setScoreLabel(scoreLabel);
        spawnMiniBoss();
        spawnBossBullet();
        Game.getInstance().getPlane().requestFocus();
        bossProgressBar.setProgress(Game.getInstance().getBoss().getHp() / 100);
        bossHpLabel.setText(Integer.toString((int)Game.getInstance().getBoss().getHp()));
        Game.getInstance().getPlane().move();
        String style = "-fx-background-position: " +
                "left " + xPosition*1 + "px top," +
                "left " + xPosition*1.5 + "px top," +
                "left " + xPosition*2 + "px bottom," +
                "left " + xPosition*2.2 + "px bottom," +
                "left " + xPosition*2.5 + "px bottom," +
                "left " + xPosition*2.6 + "px bottom," +
                "left " + xPosition*4 + "px bottom;";
        region.setStyle(style);
        if(GameMenuPageController.blackAndWhite){
            ColorAdjust desaturate = new ColorAdjust();
            desaturate.setSaturation(-1);
            content.setEffect(desaturate);
        }
    }

    private void spawnBossBullet() {
        if(timeline.getCurrentTime().subtract(bossShootLastTime).greaterThanOrEqualTo(bossShootTime) ) {
            Game.getInstance().getBoss().barf(content);
            bossShootLastTime = timeline.getCurrentTime();
        }
    }

    private void spawnMiniBoss() {
        if(timeline.getCurrentTime().subtract(miniBossLastSpawnTime).greaterThanOrEqualTo(miniBossSpawnTime) ) {
            miniBossLastSpawnTime = timeline.getCurrentTime();
            if (numberOfMinibossesSpawned == 2) {
                miniBossSpawnTime = new Duration(15000);
            }
            if (numberOfMinibossesSpawned < 3) {
                numberOfMinibossesSpawned++;
                content.getChildren().add(new MiniBoss(content, lastMiniBoss));
            } else {
                numberOfMinibossesSpawned = 0;
                miniBossSpawnTime = new Duration(500);
                content.getChildren().add((lastMiniBoss = new MiniBoss(content)));
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Music.getInstance().getMediaPlayer().stop();
        if(!Game.getInstance().musicMuted()) {
            Music.getInstance().initSoundEffect("firstAudio.mp3");
            Music.getInstance().playEffect();
            Music.getInstance().initMusic("gameMusic.mp3");
            Music.getInstance().playSong();
        }
        addImages();
        getTimeline().play();
        gameStartTime = timeline.getCurrentTime();
        miniBossSpawnTime = new Duration(2000);
        bossShootTime = new Duration(5000);
        bossShootLastTime = new Duration(0);
        miniBossLastSpawnTime = new Duration(0);
        bossProgressBar.setLayoutX(Game.getInstance().getBoss().getX() + 200);
        bossProgressBar.setLayoutY(Game.getInstance().getBoss().getY() + 30);
        bossHpLabel.setLayoutX(Game.getInstance().getBoss().getX() + 290);
        bossHpLabel.setLayoutY(Game.getInstance().getBoss().getY() + 10);
        Platform.runLater(() -> App.scene.setOnKeyReleased(event -> {
            switch (event.getCode()){
                case UP:
                    Game.getInstance().getPlane().setUp(false);
                    Game.getInstance().getPlane().setImage(new Image("/images/Plane/straight.gif"));
                    break;
                case DOWN:
                    Game.getInstance().getPlane().setDown(false);
                    Game.getInstance().getPlane().setImage(new Image("/images/Plane/straight.gif"));
                    break;
                case RIGHT:
                    Game.getInstance().getPlane().setRight(false);
                    break;
                case LEFT:
                    Game.getInstance().getPlane().setLeft(false);
                    break;
                case SPACE:

            }
        }));
        Platform.runLater(() -> App.scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case UP:
                    Game.getInstance().getPlane().setUp(true);
                    Game.getInstance().getPlane().setImage(new Image("/images/Plane/up.gif"));
                    break;
                case DOWN:
                    Game.getInstance().getPlane().setDown(true);
                    Game.getInstance().getPlane().setImage(new Image("/images/Plane/down.gif"));
                    break;
                case RIGHT:
                    Game.getInstance().getPlane().setRight(true);
                    break;
                case LEFT:
                    Game.getInstance().getPlane().setLeft(true);
                    break;
                case SPACE:
                    if(Game.getInstance().getPlane().getShootingMode() == ShootingMode.BOMB)
                        content.getChildren().add(new Bomb(content));
                    else
                        content.getChildren().add(new Bullet(content));
                    Music.getInstance().initSoundEffect("bulletSound.mp3");
                    Music.getInstance().playEffect();
                    break;
                case TAB:
                    Game.getInstance().getPlane().changeShootingMode(shootingModeImage);
                    break;
            }
        }));
    }
    public void back() {
        finished();
        Game.getInstance().setTimeLabel(timeLabel);
        App.changeMenu(Menus.GAME_PAUSE_MENU.value);
    }
    public void endGame(){
        Game.getInstance().setTimeLabel(timeLabel);
        Game.getInstance().setGameEnded(true);
        if(UserDataBase.getInstance().getLoginUser() != null) {
            double amount = timeline.getCurrentTime().subtract(gameStartTime).toSeconds();
            if (Game.getInstance().getPlane().getHp() < 0) {
                UserDataBase.getInstance().getLoginUser().setScore(DifficultyLevels.NORMAL_LEVEL_TWO, UserDataBase.getInstance().getLoginUser().getScore(DifficultyLevels.NORMAL_LEVEL_TWO) - 10);
            }else
                UserDataBase.getInstance().getLoginUser().setScore(DifficultyLevels.NORMAL_LEVEL_TWO,UserDataBase.getInstance().getLoginUser().getScore(DifficultyLevels.NORMAL_LEVEL_TWO) + (int)amount);
            UserDataBase.getInstance().getLoginUser().setGameTimeEnded(UserDataBase.getInstance().getLoginUser().getGameTimeEnded() + amount);
        }
        timeline.stop();
        Music.getInstance().getMediaPlayer().stop();
        Music.getInstance().initMusic("LoginMenuMusic.wav");
        Music.getInstance().playSong();
        App.changeMenu(Menus.GAME_PAUSE_MENU.value);
    }
    public void finished(){
        timeline.stop();
        Music.getInstance().getMediaPlayer().stop();
    }

}
