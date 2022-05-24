package Controller.Transitions;

import Model.Bomb;
import Model.Bullet;
import Model.BulletExplosion;
import Model.Game;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BombAnimation extends Transition {
    private Bomb bomb;

    private AnchorPane content;

    public BombAnimation(Bomb bomb, AnchorPane content){
        this.setCycleDuration(Duration.millis(500));
        setCycleCount(-1);
        this.bomb = bomb;
        this.content = content;
    }
    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 6) + 1;
        String url = "/images/Plane/Mini/EX/Bomb Bullet/mm_schmup_bomb_bullet_000"+ frame + ".png";
        bomb.setImage(new Image(url));
        bomb.setY(bomb.getY() + bomb.getSpeed());
        if(bomb.intersects(Game.getInstance().getBoss().getBoundsInParent())) {
            content.getChildren().add(new BulletExplosion(content,bomb));
            this.stop();
            content.getChildren().remove(bomb);
            Game.getInstance().getBoss().changeHp(-bomb.getDamage());
        }
    }
}
