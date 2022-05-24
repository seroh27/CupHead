package Model;

import Application.App;
import Enums.ShootingMode;
import View.GameMenuPageController;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class Plane extends ImageView {
    public boolean isHitBySomething() {
        return hitBySomething;
    }

    public void setHitBySomething(boolean hitBySomething) {
        this.hitBySomething = hitBySomething;
    }

    private boolean hitBySomething;
    public ShootingMode getShootingMode() {
        return shootingMode;
    }

    public void setShootingMode(ShootingMode shootingMode) {
        this.shootingMode = shootingMode;
    }

    private ShootingMode shootingMode;
    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;


    public double getHp() {
        return Hp;
    }

    public void setHp(double hp) {
        Hp = hp;
    }

    private double Hp = 4;

    public double getDamage() {
        return Damage;
    }

    public void setDamage(double damage) {
        Damage = damage;
    }

    private double Damage = 2;
    public void changeShootingMode(ImageView shootingModeImage){
        if(shootingMode == ShootingMode.BOMB) {
            shootingMode = ShootingMode.BULLET;
            shootingModeImage.setImage(new Image("/images/bullet.png"));
        }
        else {
            shootingMode = ShootingMode.BOMB;
            shootingModeImage.setImage(new Image("/images/bomb.png"));
        }
    }
    public Plane() {
        shootingMode = ShootingMode.BULLET;
        this.setImage(new Image("/images/Plane/straight.gif"));
        if(GameMenuPageController.blackAndWhite){
            ColorAdjust desaturate = new ColorAdjust();
            desaturate.setSaturation(-1);
            this.setEffect(desaturate);
        }
    }

    public void move() {
        double speed = 5;
        if(up && Game.getInstance().getPlane().getY() > 0)
            setY(getY() - speed);
        if(down && Game.getInstance().getPlane().getY() < App.scene.getWindow().getHeight() - Game.getInstance().getPlane().getImage().getHeight() - 40)
            setY(getY() + speed);
        if(right  && Game.getInstance().getPlane().getX() < App.scene.getWindow().getWidth())
            setX(getX() + speed);
        if(left && Game.getInstance().getPlane().getX() > 0)
            setX(getX() - speed);
    }
}
