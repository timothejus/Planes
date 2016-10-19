package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Plane extends HittableMovingObject implements ICanShootBullets {

    private final PlanesApp app;

    private float ax;
    private float ay;
    private float vx;
    private float vy;
    private int rotation;
    private int playerNumber;
    private int score;
    boolean rotateRight;
    boolean rotateLeft;
    boolean thrustInOn;

    public Plane(PlanesApp app) {
        super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/planesprite1.png"));
        this.app = app;
        x = 50;
        y = 50;
        playerNumber = 1;
        app.addGameObject(this, x, y);

    }

    public void objectWasHitByBullet(ICanShootBullets shooter) {

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
            x = x + 1;
        }
    }

    public void addPoint() {
        score++;
    }

    public void removePoint() {
        score--;
    }

}
