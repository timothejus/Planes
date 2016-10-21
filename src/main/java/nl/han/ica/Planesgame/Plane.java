package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Plane extends HittableMovingObject implements ICanShootBullets {

    private final PlanesApp app;

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

    public Plane(PlanesApp app) {
        super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/planesprite1.png"));
        this.app = app;
        x = 50;
        y = 50;
        playerNumber = 1;
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

    @Override
    public void objectWasHitByBullet(ICanShootBullets shooter) {
        System.out.println("Outch");
    }

    public void shoot() {

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
            if (keyCode == app.TAB) {
                System.out.print("Pew pew pew");
                //Shoot bullet
            }

        } else if (playerNumber == 2) {

            if (keyCode == app.UP) {
                thrustInOn = true;
            }
            if (keyCode == app.LEFT) {
                rotateLeft = true;
            }
            if (keyCode == app.RIGHT) {
                rotateRight = true;
            }
            if (key == ' ') {
                System.out.print("Pew pew pew");
                //Shoot bullet
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
            if (keyCode == app.SHIFT) {
                //Shoot bullet
            }

        } else if (playerNumber == 2) {

            if (keyCode == app.UP) {
                thrustInOn = false;
            }
            if (keyCode == app.LEFT) {
                rotateLeft = false;
            }
            if (keyCode == app.RIGHT) {
                rotateRight = false;
            }
            if (key == ' ') {
                //Shoot bullet
            }

        }
    }

    public void tileCollisionOccurred() {

    }

    public void update() {
        if (thrustInOn) {

            float beweeg = 3;

            if (rotatiehoek >= 0 && rotatiehoek < 90){
                float gradeY = rotatiehoek;
                float gradeX = Math.abs(rotatiehoek - 90);

                x = x + ((beweeg / 90) * gradeX);
                y = y + ((beweeg / 90) * gradeY);
            }
            else if (rotatiehoek >= 90 && rotatiehoek < 180){
                float rotatie = rotatiehoek - 90;
                float gradeY = rotatie;
                float gradeX = Math.abs(rotatie - 90);

                x = x - ((beweeg / 90) * gradeY);
                y = y + ((beweeg / 90) * gradeX);
            }
            else if (rotatiehoek >= 180 && rotatiehoek < 270){
                float rotatie = rotatiehoek - 180;
                float gradeY = rotatie;
                float gradeX = Math.abs(rotatie - 90);

                x = x - ((beweeg / 90) * gradeX);
                y = y - ((beweeg / 90) * gradeY);
            }
            else if (rotatiehoek >= 270 && rotatiehoek <= 359){
                float rotatie = rotatiehoek - 270;
                float gradeY = rotatie;
                float gradeX = Math.abs(rotatie - 90);

                x = x + ((beweeg / 90) * gradeY);
                y = y - ((beweeg / 90) * gradeX);
            }
        }
        if (rotateLeft) {
            if ((rotatiehoek - 1) < 0){
                rotatiehoek = 359;
            }
            else {
                rotatiehoek = rotatiehoek - 2;
            }
        }
        if (rotateRight) {
            if ((rotatiehoek + 1) > 359){
                rotatiehoek = 0;
            }
            else {
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
