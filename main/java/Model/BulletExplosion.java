package Model;

import Controller.Transitions.BulletExplosionAnimation;
import Controller.Transitions.BulletShootAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BulletExplosion extends ImageView {
    BulletExplosionAnimation animation;
    public BulletExplosion(AnchorPane content,Bullet bullet) {
        this.setX(bullet.getX());
        this.setY(bullet.getY() - 75);
        animation = new BulletExplosionAnimation(this,content);
        animation.play();
    }
    public BulletExplosion(AnchorPane content,MiniBoss miniBoss) {
        this.setX(miniBoss.getX());
        this.setY(miniBoss.getY() - 75);
        animation = new BulletExplosionAnimation(this,content);
        animation.play();
    }
    public BulletExplosion(AnchorPane content,Bomb bomb) {
        this.setX(bomb.getX());
        this.setY(bomb.getY() - 75);
        animation = new BulletExplosionAnimation(this,content);
        animation.play();
    }
    public BulletExplosion(AnchorPane content,BossBullet bullet) {
        this.setX(bullet.getX());
        this.setY(bullet.getY() - 75);
        animation = new BulletExplosionAnimation(this,content);
        animation.play();
    }
}