package nl.han.ica.Planesgame;

import javafx.scene.effect.Light;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Plane extends HittableMovingObject implements ICanShootBullets {


    private float ax;
    private float ay;
    private float vx;
    private float vy;
    private int playerNumber;
    private int score;
    boolean rotateRight;
    boolean rotateLeft;
    boolean thrustInOn;
    private float rotatiehoek = 0;

    public Plane(PlanesApp app, String sprite, int playerNumber) {
        super(new Sprite(sprite), app);
        x = 50;
        y = 50;
        this.playerNumber = playerNumber;
        app.addGameObject(this, x, y);

    }

    @Override
    public void draw(PGraphics g) {

        g.pushMatrix();
        g.translate(getCenterX(), getCenterY());

        g.rotate(PApplet.radians(rotatiehoek));

        g.image(getImage(), -width / 2, -height / 2);
        g.popMatrix();

    }

    public static float getRotationInRadians(float rotationInDegrees) {
        float rotationInRadians = (float) (PApplet.radians(rotationInDegrees) % Math.PI);
        rotationInRadians = (float) (((rotationInRadians > Math.PI * 0.5 && rotationInRadians < Math.PI * 1) || (rotationInRadians > Math.PI * 1.5 && rotationInRadians < Math.PI * 2)) ? Math.PI - rotationInRadians : rotationInRadians);

        return rotationInRadians;
    }

    @Override
    public float getWidth() {
        // met dank aan
        // http://stackoverflow.com/questions/10392658/calculate-the-bounding-boxs-x-y-height-and-width-of-a-rotated-element-via-jav
        float rotationInRadians = getRotationInRadians(rotatiehoek);
        return (float) (Math.sin(rotationInRadians) * height + Math.cos(rotationInRadians) * width);
    }

    @Override
    public float getHeight() {
        float rotationInRadians = getRotationInRadians(rotatiehoek);
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

    public float getBulletX() {
        float distance;
        if (playerNumber == 1) {
            distance = 80;
        } else {
            distance = -80;
        }

        if (rotatiehoek >= 0 && rotatiehoek < 90) {
            return  (float) (distance * Math.cos(Math.toRadians(rotatiehoek))) + getCenterX();
        } else if (rotatiehoek >= 90 && rotatiehoek < 180) {
            return - (float) (distance * Math.sin(Math.toRadians(rotatiehoek - 90))) + getCenterX();
        } else if (rotatiehoek >= 180 && rotatiehoek < 270) {
            return - (float) (distance * Math.cos(Math.toRadians(rotatiehoek - 180))) + getCenterX();
        } else if (rotatiehoek >= 270 && rotatiehoek <= 359) {
            return (float) (distance * Math.sin(Math.toRadians(rotatiehoek - 270))) + getCenterX();
        }
        return 0;
    }

    public float getBulletY() {
        float distance;
        if (playerNumber == 1) {
            distance = 80;
        } else {
            distance = -80;
        }

        if (rotatiehoek >= 0 && rotatiehoek < 90) {
            return (float) (distance * Math.sin(Math.toRadians(rotatiehoek))) + getCenterY();
        } else if (rotatiehoek >= 90 && rotatiehoek < 180) {
            return (float) (distance * Math.cos(Math.toRadians(rotatiehoek - 90))) + getCenterY();
        } else if (rotatiehoek >= 180 && rotatiehoek < 270) {
            return - (float) (distance * Math.sin(Math.toRadians(rotatiehoek - 180))) + getCenterY();
        } else if (rotatiehoek >= 270 && rotatiehoek <= 359) {
            return - (float) (distance * Math.cos(Math.toRadians(rotatiehoek - 270))) + getCenterY();
        }
        return 0;
    }

    @Override
    public void objectWasHitByBullet(Bullet bullet) {

        System.out.println("Outch");
    }

    public void shoot() {
        if (playerNumber == 1) {
            float bulletRotation;

            if (rotatiehoek + 90 >= 360) {
                bulletRotation = rotatiehoek - 270;
            } else {
                bulletRotation = rotatiehoek + 90;
            }

            world.addGameObject(new Bullet(world, this, bulletRotation, 8), getBulletX(), getBulletY());
        } else if (playerNumber == 2) {
            float bulletRotation;

            if (rotatiehoek - 90 < 0) {
                bulletRotation = rotatiehoek + 270;
            } else {
                bulletRotation = rotatiehoek - 90;
            }

            world.addGameObject(new Bullet(world, this, bulletRotation, 8), getBulletX(), getBulletY());
        }

    }

    @Override
    public void keyPressed(int keyCode, char key) {
        if (playerNumber == 1) {
            if (key == 'w') {
                thrustInOn = true;
            }
            if (key == 'a') {
                rotateLeft = true;
            }
            if (key == 'd') {
                rotateRight = true;
            }
            if (key == 'z') {
                shoot();
            }

        } else if (playerNumber == 2) {

            if (keyCode == world.UP) {
                thrustInOn = true;
            }
            if (keyCode == world.LEFT) {
                rotateLeft = true;
            }
            if (keyCode == world.RIGHT) {
                rotateRight = true;
            }
            if (key == '/') {
                shoot();
            }

        }
    }

    @Override
    public void keyReleased(int keyCode, char key) {
        if (playerNumber == 1) {
            if (key == 'w') {
                thrustInOn = false;
            }
            if (key == 'a') {
                rotateLeft = false;
            }
            if (key == 'd') {
                rotateRight = false;
            }

        } else if (playerNumber == 2) {

            if (keyCode == world.UP) {
                thrustInOn = false;
            }
            if (keyCode == world.LEFT) {
                rotateLeft = false;
            }
            if (keyCode == world.RIGHT) {
                rotateRight = false;
            }
        }
    }

    public void tileCollisionOccurred() {

    }

    public void update() {
        if (thrustInOn) {
            float beweeg;
            if (playerNumber == 1) {
                beweeg = 3;
            } else {
                beweeg = -3;
            }
            if (rotatiehoek >= 0 && rotatiehoek < 90) {
                x = x + (float) (beweeg * Math.cos(Math.toRadians(rotatiehoek)));
                y = y + (float) (beweeg * Math.sin(Math.toRadians(rotatiehoek)));
            } else if (rotatiehoek >= 90 && rotatiehoek < 180) {
                x = x - (float) (beweeg * Math.sin(Math.toRadians(rotatiehoek - 90)));
                y = y + (float) (beweeg * Math.cos(Math.toRadians(rotatiehoek - 90)));
            } else if (rotatiehoek >= 180 && rotatiehoek < 270) {
                x = x - (float) (beweeg * Math.cos(Math.toRadians(rotatiehoek - 180)));
                y = y - (float) (beweeg * Math.sin(Math.toRadians(rotatiehoek - 180)));
            } else if (rotatiehoek >= 270 && rotatiehoek <= 359) {
                x = x + (float) (beweeg * Math.sin(Math.toRadians(rotatiehoek - 270)));
                y = y - (float) (beweeg * Math.cos(Math.toRadians(rotatiehoek - 270)));
            }
        }
        if (rotateLeft) {
            if ((rotatiehoek - 1) < 0) {
                rotatiehoek = 359;
            } else {
                rotatiehoek = rotatiehoek - 2;
            }
        }
        if (rotateRight) {
            if ((rotatiehoek + 1) > 359) {
                rotatiehoek = 0;
            } else {
                rotatiehoek = rotatiehoek + 2;
            }
        }
    }

    public void addPoint() {
        score++;
    }

    public void removePoint() {
        score--;
    }

}
