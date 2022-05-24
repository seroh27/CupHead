package Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game game;




    public Label getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(Label scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    private Label scoreLabel;

    public javafx.scene.control.Label getTimeLabel() {
        return timeLabel;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    private boolean gameEnded = false;
    public void setTimeLabel(javafx.scene.control.Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    private javafx.scene.control.Label timeLabel;
    private static boolean musicMuted = false;

    private void setGame(Game game){
        Game.game = game;
    }
    public void renewGame(){
        game = new Game();
    }
    private Game(){
        boss = new Boss();
        plane = new Plane();
    };
    public static Game getInstance(){
        if(game == null) {
            game = new Game();
        }
        return game;
    }
    private Boss boss;
    private Plane plane;

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public boolean musicMuted() {
        return musicMuted;
    }

    public void setMusicMuted(boolean musicMuted) {
        Game.musicMuted = musicMuted;
    }
//    public void loadGame() {
//        try {
//            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/GameDataBase.json")));
//            ArrayList<Game> games;
//            games = new Gson().fromJson(json, new TypeToken<List<Game>>() {}.getType());
//            if (games != null) {
//                setGame(games.get(games.size() - 1));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public void saveGame() {
//        try {
//            FileWriter fileWriter = new FileWriter("./src/main/resources/GameDataBase.json");
//            fileWriter.write(new Gson().toJson(plane.getHp()));
//            fileWriter.write(new Gson().toJson(boss.get));
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
