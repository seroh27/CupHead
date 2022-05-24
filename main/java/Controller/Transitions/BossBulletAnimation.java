package Controller.Transitions;

import Model.BossBullet;
import Model.Bullet;
import Model.BulletExplosion;
import Model.Game;
import View.GameMenuPageController;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class BossBulletAnimation extends Transition {
    private BossBullet bullet;

    private AnchorPane content;

    public BossBulletAnimation(BossBullet bullet, AnchorPane content){
        this.setCycleDuration(Duration.millis(500));
        setCycleCount(-1);
        this.bullet = bullet;
        this.content = content;
        String url = "/images/images/egg.png";
        bullet.setImage(new Image(url));
        RotateTransition rotate = new RotateTransition();
    }
    @Override
    protected void interpolate(double frac) {
        bullet.setRotate(bullet.getRotate() + 5);
        bullet.setX(bullet.getX() - bullet.getSpeed());
        if(bullet.intersects(Game.getInstance().getPlane().getBoundsInParent()) || bullet.getX() == 0) {
            content.getChildren().add(new BulletExplosion(content,bullet));
            content.getChildren().remove(bullet);
            this.stop();
            if(bullet.intersects(Game.getInstance().getPlane().getBoundsInParent())) {
                Game.getInstance().getPlane().setHp(Game.getInstance().getPlane().getHp() - 1);
                Game.getInstance().getPlane().setHitBySomething(true);
            }
        }
        if(GameMenuPageController.blackAndWhite){
            ColorAdjust desaturate = new ColorAdjust();
            desaturate.setSaturation(-1);
            bullet.setEffect(desaturate);
        }
    }
}
