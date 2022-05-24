package Controller.Transitions;

import Application.App;
import Model.Bullet;
import Model.BulletShard;
import View.GameMenuPageController;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BulletShardAnimation extends Transition {
    private BulletShard bulletShard;
    public BulletShardAnimation(BulletShard bulletShard,AnchorPane content) {
        this.bulletShard = bulletShard;
        this.setCycleDuration(Duration.millis(100));
        this.setCycleCount(1);
        setOnFinished(event -> {
            content.getChildren().remove(bulletShard);
            content.getChildren().add(new Bullet(content));
        });
    }

    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 3) + 1;
        String url = "/images/CupheadShoot/"+ frame + ".png";
        bulletShard.setImage(new Image(url));
        bulletShard.setFitWidth(100);bulletShard.setFitHeight(100);
        if(GameMenuPageController.blackAndWhite){
            ColorAdjust desaturate = new ColorAdjust();
            desaturate.setSaturation(-1);
            bulletShard.setEffect(desaturate);
        }
    }

}

