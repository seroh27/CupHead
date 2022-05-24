package Controller.Transitions;

import Model.Bullet;
import Model.BulletExplosion;
import Model.BulletShard;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BulletExplosionAnimation extends Transition {
    private BulletExplosion bulletExplosion;
    private AnchorPane content;
    public BulletExplosionAnimation(BulletExplosion bulletExplosion,AnchorPane content) {
        this.bulletExplosion = bulletExplosion;
        this.setCycleDuration(Duration.millis(300));
        this.setCycleCount(1);
        this.content = content;
        setOnFinished(event -> {
            content.getChildren().remove(bulletExplosion);
        });
    }

    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 11) + 1;
        String url = "/images/Phase 1/Hit Dust/"+ frame + ".png";
        bulletExplosion.setImage(new Image(url));
        bulletExplosion.setFitWidth(150);bulletExplosion.setFitHeight(150);
    }
}
