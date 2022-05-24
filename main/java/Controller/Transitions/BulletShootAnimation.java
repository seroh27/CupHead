package Controller.Transitions;

import Model.BossBullet;
import Model.Bullet;
import Model.BulletExplosion;
import Model.Game;
import View.GameMenuPageController;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BulletShootAnimation extends Transition {
    private Bullet bullet;

    private AnchorPane content;

    public BulletShootAnimation(Bullet bullet, AnchorPane content){
        this.setCycleDuration(Duration.millis(500));
        setCycleCount(-1);
        this.bullet = bullet;
        this.content = content;
    }
    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 2) + 1;
        String url = "/images/Plane/Mini/Bullet/"+ frame + ".png";
        bullet.setImage(new Image(url));
        bullet.setX(bullet.getX() + bullet.getSpeed());
        if(bullet.intersects(Game.getInstance().getBoss().getBoundsInParent())) {
            content.getChildren().add(new BulletExplosion(content,bullet));
            this.stop();
            content.getChildren().remove(bullet);
            Game.getInstance().getBoss().changeHp(-bullet.getDamage());
        }
        if(GameMenuPageController.blackAndWhite){
            ColorAdjust desaturate = new ColorAdjust();
            desaturate.setSaturation(-1);
            bullet.setEffect(desaturate);
        }
    }
}
