package Controller.Transitions;

import Application.App;
import Model.*;
import View.GameMenuPageController;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MiniBossAnimation extends Transition {


    private MiniBoss miniBoss;
    private AnchorPane content;

    public MiniBossAnimation(MiniBoss miniBoss, AnchorPane content) {
        this.miniBoss = miniBoss;
        this.setCycleDuration(Duration.millis(300));
        this.setCycleCount(-1);
        this.miniBoss = miniBoss;
        this.content = content;
    }

    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 3) + 1;
        String url = "/images/MiniBossFly/yellow/"+ frame + ".png";
        miniBoss.setImage(new Image(url));
        miniBoss.setX(miniBoss.getX() - miniBoss.getSpeed());
        if(miniBoss.intersects(Game.getInstance().getPlane().getBoundsInParent())){
            content.getChildren().remove(miniBoss);
            Game.getInstance().getPlane().setHp(Game.getInstance().getPlane().getHp() - 1);
            miniBoss.getMiniBossAnimation().stop();
            this.stop();
            content.getChildren().add(new BulletExplosion(content,miniBoss));
        }
        Node object = null;
        for (Node node : content.getChildren()) {
            if((node instanceof Bullet || (node instanceof Bomb)) && miniBoss.intersects(node.getBoundsInParent())){
                object = node;
            }
        }
        if(object != null)
        {
            if(object instanceof Bullet) {
                ((Bullet) object).getAnimation().stop();
                miniBoss.setHp((miniBoss.getHp() - ((Bullet) object).getDamage()));
            }
            if(object instanceof Bomb) {
                ((Bomb) object).getAnimation().stop();
                miniBoss.setHp((miniBoss.getHp() - ((Bomb) object).getDamage()));
            }
            content.getChildren().remove(object);
            content.getChildren().add(new BulletExplosion(content, miniBoss));
            if(miniBoss.getHp() <= 0 || miniBoss.getX() < -miniBoss.getImage().getWidth()) {
                if(miniBoss.getHp() <= 0){
                    Music.getInstance().initSoundEffect("minibossDie.mp3");
                    Music.getInstance().playEffect();
                }
                content.getChildren().remove(miniBoss);
                miniBoss.getMiniBossAnimation().stop();
            }
        }
        if(GameMenuPageController.blackAndWhite){
            ColorAdjust desaturate = new ColorAdjust();
            desaturate.setSaturation(-1);
            miniBoss.setEffect(desaturate);
        }
    }
}
