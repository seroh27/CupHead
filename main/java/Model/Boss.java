package Model;

import Controller.Transitions.BossAnimation;
import Controller.Transitions.BossBarfAnimation;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Boss extends ImageView {
    public double getHp() {
        return Hp;
    }

    public void changeHp(double hp){
        Hp += hp;
    }

    double Hp = 100;
    public Transition getAnimation() {
        return animation;
    }

    private Transition animation;
    public void barf(AnchorPane content){
        animation.stop();
        animation = new BossBarfAnimation(this,content);
        animation.play();
    }
    public void idle(){
        animation.stop();
        animation = new BossAnimation();
        animation.play();
    }

    public Boss() {
        animation = new BossAnimation();
        animation.play();
    }
}
