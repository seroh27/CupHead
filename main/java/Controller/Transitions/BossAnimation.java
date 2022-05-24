package Controller.Transitions;
import Model.Game;
import View.GameMenuPageController;
import javafx.animation.Transition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class BossAnimation extends Transition {

    public BossAnimation() {
        this.setCycleDuration(Duration.millis(750));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 5) + 1;
        String url = "/images/BossFly/"+ (7 - frame) + ".png";
        Game.getInstance().getBoss().setImage(new Image(url));
        if(GameMenuPageController.blackAndWhite){
            ColorAdjust desaturate = new ColorAdjust();
            desaturate.setSaturation(-1);
            Game.getInstance().getBoss().setEffect(desaturate);
        }
    }
}
