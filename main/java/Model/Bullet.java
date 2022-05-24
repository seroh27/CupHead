package Model;

import Controller.Transitions.BossAnimation;
import Controller.Transitions.BulletShootAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.Element;

public class Bullet extends ImageView {
    private double speed = 5;


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    private int damage = 5;

    public BulletShootAnimation getAnimation() {
        return animation;
    }

    public void setAnimation(BulletShootAnimation animation) {
        this.animation = animation;
    }

    private BulletShootAnimation animation;
    public Bullet(AnchorPane content) {
        this.setImage(new Image("/images/bullet.gif"));
        this.setX(Game.getInstance().getPlane().getX() + Game.getInstance().getPlane().getImage().getWidth() * 5/6);
        this.setY(Game.getInstance().getPlane().getY() + Game.getInstance().getPlane().getImage().getHeight() / 2);
        animation = new BulletShootAnimation(this,content);
        animation.play();
    }

    public double getSpeed() {
        return speed;
    }
}
