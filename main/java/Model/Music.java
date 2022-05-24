package Model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Music {
    private static Music music;
    private Music(){}
    public static Music getInstance(){
        if(music == null)
            music = new Music();
        return music;
    }
    private ArrayList<File> songs;
    private File directory;
    private File[] files;
    private javafx.scene.media.Media media;
    private javafx.scene.media.Media mediaForEffect;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayerEffect;

    public void initMusic(String songName){
        songs = new ArrayList<File>();

        directory = new File("src/main/resources/music/");

        files = directory.listFiles();

        if(files != null) {

            songs.addAll(Arrays.asList(files));
        }
        for (File file:files) {
            if(file.getName().equals(songName) ){
                media = new Media(file.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
            }
        }
    }
    public void initSoundEffect(String songName){
        songs = new ArrayList<File>();

        directory = new File("src/main/resources/music/");

        files = directory.listFiles();

        if(files != null) {

            songs.addAll(Arrays.asList(files));
        }
        for (File file:files) {
            if(file.getName().equals(songName) ){
                mediaForEffect = new Media(file.toURI().toString());
                mediaPlayerEffect = new MediaPlayer(mediaForEffect);
            }
        }
    }

    public void playSong(){
        mediaPlayer.setVolume(0.02);
        mediaPlayer.play();
    }
    public void playEffect(){
        mediaPlayerEffect.setVolume(0.1);
        mediaPlayerEffect.play();
    }
}
