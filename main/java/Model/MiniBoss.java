package Model;
import Application.App;
import Controller.Transitions.MiniBossAnimation;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.util.Random;

public class MiniBoss extends ImageView {
    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        Hp = hp;
    }

    private int Hp = 10;
    private double speed = 3;

    public MiniBossAnimation getMiniBossAnimation() {
        return miniBossAnimation;
    }

    public void setMiniBossAnimation(MiniBossAnimation miniBossAnimation) {
        this.miniBossAnimation = miniBossAnimation;
    }

    private MiniBossAnimation miniBossAnimation;
    public MiniBoss(AnchorPane content) {
        this.setX(App.scene.getWidth());
        this.setY(new Random().nextInt((int) App.scene.getHeight() - 50));
        miniBossAnimation = new MiniBossAnimation(this,content);
        miniBossAnimation.play();
    }

    public MiniBoss(AnchorPane content,MiniBoss miniBoss) {
        this.setX(App.scene.getWidth());
        this.setY(miniBoss.getY());
        miniBossAnimation = new MiniBossAnimation(this,content);
        miniBossAnimation.play();
    }

    public double getSpeed() {
        return speed;
    }

    public double getDamage() {
        return damage;
    }

    private double damage;

}
