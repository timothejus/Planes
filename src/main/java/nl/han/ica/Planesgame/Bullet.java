package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Bullet extends SpriteObject {

    private int speed;

    private float rotation;
    private PlanesApp world;

    private ICanShootBullets shooter;


    public Bullet(PlanesApp world, ICanShootBullets shooter, float rotation, int speed) {
        super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/cannonbulletsprite.png"));
        this.shooter = shooter;
        this.rotation = rotation;
        this.speed = speed;
        setySpeed(speed);
        this.world = world;

        if (rotation >= 0 && rotation < 90) {
            setxSpeed((float) (speed * Math.sin(Math.toRadians(rotation))));
            setySpeed(-(float) (speed * Math.cos(Math.toRadians(rotation))));
        } else if (rotation >= 90 && rotation < 180) {
            setxSpeed((float) (speed * Math.cos(Math.toRadians(rotation - 90))));
            setySpeed((float) (speed * Math.sin(Math.toRadians(rotation - 90))));
        } else if (rotation >= 180 && rotation < 270) {
            setxSpeed(-(float) (speed * Math.sin(Math.toRadians(rotation - 180))));
            setySpeed((float) (speed * Math.cos(Math.toRadians(rotation - 180))));
        } else if (rotation >= 270 && rotation <= 359) {
            setxSpeed(-(float) (speed * Math.cos(Math.toRadians(rotation - 270))));
            setySpeed(-(float) (speed * Math.sin(Math.toRadians(rotation - 270))));
        }

    }

    @Override
    public void draw(PGraphics g) {
        g.pushMatrix();
        g.translate(getCenterX(), getCenterY());

        g.rotate(PApplet.radians(rotation));

        g.image(getImage(), -width / 2, -height / 2);
        g.popMatrix();
    }

    public static float getRotationInRadians(float rotationInDegrees) {
        float rotationInRadians = (float) (PApplet.radians(rotationInDegrees) % Math.PI);

        rotationInRadians = (float) (((rotationInRadians > Math.PI * 0.5 && rotationInRadians < Math.PI * 1)
                || (rotationInRadians > Math.PI * 1.5 && rotationInRadians < Math.PI * 2)) ? Math.PI - rotationInRadians
                : rotationInRadians);
        return rotationInRadians;
    }

    @Override
    public float getWidth() {
        // met dank aan
        // http://stackoverflow.com/questions/10392658/calculate-the-bounding-boxs-x-y-height-and-width-of-a-rotated-element-via-jav
        float rotationInRadians = getRotationInRadians(rotation);
        return (float) (Math.sin(rotationInRadians) * height +
                Math.cos(rotationInRadians) * width);
    }

    @Override
    public float getHeight() {
        float rotationInRadians = getRotationInRadians(rotation);
        return (float) (Math.sin(rotationInRadians) * width + Math.cos(rotationInRadians) * height);
    }

    @Override
    public float getX() {
        return -(getWidth() / 2) + getCenterX();
    }

    @Override
    public float getY() {
        return -(getHeight() / 2) + getCenterY();
    }


    public ICanShootBullets getShooter() {
        return shooter;
    }

    @Override
    public void update() {
        if (getY() <= 0 || getX() <= 0 || getY() >= world.height || getX() >= world.width) {
            world.deleteGameObject(this);
        }

    }
}
