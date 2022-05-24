package Model;

import Controller.Transitions.BombAnimation;
import Controller.Transitions.BulletShootAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Bomb extends ImageView {
    private double speed = 5;

    public int getDamage() {
        return damage;
    }

    private int damage = 10;

    public BombAnimation getAnimation() {
        return animation;
    }

    public void setAnimation(BombAnimation animation) {
        this.animation = animation;
    }

    private BombAnimation animation;
    public Bomb(AnchorPane content) {
        this.setX(Game.getInstance().getPlane().getX() + Game.getInstance().getPlane().getImage().getWidth() / 2);
        this.setY(Game.getInstance().getPlane().getY() + Game.getInstance().getPlane().getImage().getHeight());
        animation = new BombAnimation(this,content);
        animation.play();
    }
    public double getSpeed() {
        return speed;
    }
}
