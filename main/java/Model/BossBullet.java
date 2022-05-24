package Model;

import Controller.Transitions.BossBulletAnimation;
import Controller.Transitions.BulletShootAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BossBullet extends ImageView {
    private double speed = 5;

    private BossBulletAnimation animation;
    public BossBullet(AnchorPane content) {
        this.setImage(new Image("/images/bullet.gif"));
        this.setX(Game.getInstance().getBoss().getX());
        this.setY(Game.getInstance().getBoss().getY() + Game.getInstance().getBoss().getImage().getHeight() / 2);
        animation = new BossBulletAnimation(this,content);
        animation.play();
    }

    public double getSpeed() {
        return speed;
    }
}
