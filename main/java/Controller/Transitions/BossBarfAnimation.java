package Controller.Transitions;

import Model.Boss;
import Model.BossBullet;
import Model.Game;
import Model.Music;
import View.GameMenuPageController;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BossBarfAnimation extends Transition {
    private AnchorPane content;
    private Boss boss;
    public BossBarfAnimation(Boss boss, AnchorPane content) {
        Music.getInstance().initSoundEffect("barfSound.mp3");
        Music.getInstance().playEffect();
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(1);
        this.content = content;
        setOnFinished(event -> {
            boss.idle();
        });
        this.boss = boss;

    }
    private boolean barfed  = false;
    private boolean firstRound = true;
    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 11);
        String url = "/images/BossShoot/"+ (frame) + ".png";
        if(firstRound){
            firstRound = false;
            //boss.setX(boss.getX() - 150);
        }
        Game.getInstance().getBoss().setImage(new Image(url));
        if(frame == 6 && barfed == false) {
            barfed = true;
            content.getChildren().add(new BossBullet(content));
        }
        if(GameMenuPageController.blackAndWhite){
            ColorAdjust desaturate = new ColorAdjust();
            desaturate.setSaturation(-1);
            Game.getInstance().getBoss().setEffect(desaturate);
        }
    }
}
