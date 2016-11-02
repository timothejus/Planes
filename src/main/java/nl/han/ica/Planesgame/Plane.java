package nl.han.ica.Planesgame;

import javafx.scene.effect.Light;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;

public class Plane extends HittableMovingObject implements ICanShootBullets, IAlarmListener {

    private ArrayList<IPowerUps> powerups = new ArrayList<>();
    private float xSpeed;
    private float ySpeed;
    private float xMaxSpeed;
    private float yMaxSpeed;
    private float acceleration = (float) 0.1;
    private float spawnpointX;
    private float spawnpointY;
    private int playerNumber;
    private int score;
    private boolean rotateRight;
    private boolean rotateLeft;
    private boolean thrustInOn;
    private boolean canShoot = true;
    private float rotatiehoek = 0;
    private boolean destructible = true;
    private Alarm shootTimer;

    public Plane(PlanesApp app, String sprite, int playerNumber, float spawnpointX, float spawnpointY) {
        super(new Sprite(sprite), app);
        this.spawnpointX = spawnpointX;
        this.spawnpointY = spawnpointY;
        xSpeed = 0;
        ySpeed = -4;
        this.playerNumber = playerNumber;
        app.addGameObject(this, spawnpointX, spawnpointY);

        shootTimer = new Alarm("shootTimer", 1);
        shootTimer.addTarget(this);
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

    private float getBulletX() {
        float distance;
        if (playerNumber == 1) {
            distance = 80;
        } else {
            distance = -80;
        }

        if (rotatiehoek >= 0 && rotatiehoek < 90) {
            return (float) (distance * Math.cos(Math.toRadians(rotatiehoek))) + getCenterX();
        } else if (rotatiehoek >= 90 && rotatiehoek < 180) {
            return -(float) (distance * Math.sin(Math.toRadians(rotatiehoek - 90))) + getCenterX();
        } else if (rotatiehoek >= 180 && rotatiehoek < 270) {
            return -(float) (distance * Math.cos(Math.toRadians(rotatiehoek - 180))) + getCenterX();
        } else if (rotatiehoek >= 270 && rotatiehoek <= 359) {
            return (float) (distance * Math.sin(Math.toRadians(rotatiehoek - 270))) + getCenterX();
        }
        return 0;
    }

    private float getBulletY() {
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
            return -(float) (distance * Math.sin(Math.toRadians(rotatiehoek - 180))) + getCenterY();
        } else if (rotatiehoek >= 270 && rotatiehoek <= 359) {
            return -(float) (distance * Math.cos(Math.toRadians(rotatiehoek - 270))) + getCenterY();
        }
        return 0;
    }

    @Override
    public void objectWasHitByBullet(Bullet bullet) {
        if (bullet.getShooter() != this) {
            world.deleteGameObject(bullet);
            if (destructible) {
                explode();
            }
        }
    }

    public void explode(){
        world.scoreboard.reportDeath(playerNumber);
        world.deleteGameObject(this);
        Alarm respawntimer = new Alarm("respawntimer", 3);
        respawntimer.addTarget(this);
        respawntimer.start();
    }

    @Override
    public void triggerAlarm(String alarmName) {
        if (alarmName == "respawntimer") {
            x = spawnpointX;
            y = spawnpointY;
            xSpeed = 0;
            ySpeed = -2;
            rotatiehoek = 0;
            thrustInOn = false;
            rotateLeft = false;
            rotateRight = false;
            canShoot = true;
            destructible = true;
            world.addGameObject(this);
        }
        if(alarmName == "shootTimer"){
            canShoot = true;
        }
    }

    public void shoot() {
        if (canShoot) {
            if (playerNumber == 1) {
                float bulletRotation;

                if (rotatiehoek + 90 >= 360) {
                    bulletRotation = rotatiehoek - 270;
                } else {
                    bulletRotation = rotatiehoek + 90;
                }

                world.addGameObject(new Bullet(world, this, bulletRotation, 12), getBulletX(), getBulletY());
            } else if (playerNumber == 2) {
                float bulletRotation;

                if (rotatiehoek - 90 < 0) {
                    bulletRotation = rotatiehoek + 270;
                } else {
                    bulletRotation = rotatiehoek - 90;
                }

                world.addGameObject(new Bullet(world, this, bulletRotation, 12), getBulletX(), getBulletY());
            }
            canShoot = false;
            shootTimer.start();
        }
    }

    public void setDestructible(boolean bool){
        destructible = bool;
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

    public void update() {
        if (thrustInOn) {
            float beweeg;
            if (playerNumber == 1) {
                beweeg = 6;
            } else {
                beweeg = -6;
            }
            if (rotatiehoek >= 0 && rotatiehoek < 90) {
                xMaxSpeed = +(float) (beweeg * Math.cos(Math.toRadians(rotatiehoek)));
                yMaxSpeed = +(float) (beweeg * Math.sin(Math.toRadians(rotatiehoek)));
            } else if (rotatiehoek >= 90 && rotatiehoek < 180) {
                xMaxSpeed = -(float) (beweeg * Math.sin(Math.toRadians(rotatiehoek - 90)));
                yMaxSpeed = +(float) (beweeg * Math.cos(Math.toRadians(rotatiehoek - 90)));
            } else if (rotatiehoek >= 180 && rotatiehoek < 270) {
                xMaxSpeed = -(float) (beweeg * Math.cos(Math.toRadians(rotatiehoek - 180)));
                yMaxSpeed = -(float) (beweeg * Math.sin(Math.toRadians(rotatiehoek - 180)));
            } else if (rotatiehoek >= 270 && rotatiehoek <= 359) {
                xMaxSpeed = +(float) (beweeg * Math.sin(Math.toRadians(rotatiehoek - 270)));
                yMaxSpeed = -(float) (beweeg * Math.cos(Math.toRadians(rotatiehoek - 270)));
            }
        } else {
            xMaxSpeed = xSpeed;
            yMaxSpeed = 6;
        }

        if (xSpeed < xMaxSpeed) {
            xSpeed += acceleration;
            if (xSpeed > xMaxSpeed) {
                xSpeed = xMaxSpeed;
            }
        } else if (xSpeed > xMaxSpeed) {
            xSpeed -= acceleration;
            if (xSpeed < xMaxSpeed) {
                xSpeed = xMaxSpeed;
            }
        }

        if (ySpeed < yMaxSpeed) {
            ySpeed += acceleration;
            if (ySpeed > yMaxSpeed) {
                ySpeed = yMaxSpeed;
            }
        } else if (ySpeed > yMaxSpeed) {
            ySpeed -= acceleration;
            if (ySpeed < yMaxSpeed) {
                ySpeed = yMaxSpeed;
            }
        }

        x = x + xSpeed;
        y = y + ySpeed;

        if (y > world.height - 100){
            explode();
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

        for(IPowerUps p : powerups){
            p.applyPowerUp(this);
        }
    }

    public void addPoint() {
        world.scoreboard.scorePoint(playerNumber);
    }


    @Override
    public void objectCollidedWithPowerUp(IPowerUps powerUp){
        powerups.add(powerUp);
        powerUp.delete();
    }

}
