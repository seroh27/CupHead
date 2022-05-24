package Model;
import Controller.Transitions.BulletShardAnimation;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class BulletShard extends ImageView {
    BulletShardAnimation animation;
    public BulletShard(AnchorPane content) {
        this.setX(Game.getInstance().getPlane().getX() + Game.getInstance().getPlane().getImage().getWidth() * 5/6);
        this.setY(Game.getInstance().getPlane().getY() + Game.getInstance().getPlane().getImage().getHeight() / 7);
        animation = new BulletShardAnimation(this,content);
        animation.play();
    }
}

