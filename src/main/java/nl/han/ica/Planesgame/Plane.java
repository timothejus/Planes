package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;

/**
 * @author Rogier Grobbee
 *         The plane is being controlled by the player
 */
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
    private boolean rotateRight;
    private boolean rotateLeft;
    private boolean thrustIsOn;
    private boolean canShoot = true;
    private float rotationAngle = 0;
    private boolean destructible = true;
    private Alarm shootTimer;

    /**
     * @param app
     * @param sprite
     * @param playerNumber
     * @param spawnpointX
     * @param spawnpointY
     */
    public Plane(PlanesApp world, String sprite, int playerNumber, float spawnpointX, float spawnpointY) {
        super(new Sprite(sprite), world);
        this.spawnpointX = spawnpointX;
        this.spawnpointY = spawnpointY;
        xSpeed = 0;
        ySpeed = -4;
        this.playerNumber = playerNumber;
        world.addGameObject(this, spawnpointX, spawnpointY);

        shootTimer = new Alarm("shootTimer", 1);
        shootTimer.addTarget(this);
    }

    @Override
    public void draw(PGraphics g) {

        g.pushMatrix();
        g.translate(getCenterX(), getCenterY());

        g.rotate(PApplet.radians(rotationAngle));

        g.image(getImage(), -width / 2, -height / 2);
        g.popMatrix();

    }

    private static float getRotationInRadians(float rotationInDegrees) {
        float rotationInRadians = (float) (PApplet.radians(rotationInDegrees) % Math.PI);
        rotationInRadians = (float) (((rotationInRadians > Math.PI * 0.5 && rotationInRadians < Math.PI * 1) || (rotationInRadians > Math.PI * 1.5 && rotationInRadians < Math.PI * 2)) ? Math.PI - rotationInRadians : rotationInRadians);

        return rotationInRadians;
    }

    /**
     * @return
     */
    @Override
    public float getWidth() {
        // met dank aan
        // http://stackoverflow.com/questions/10392658/calculate-the-bounding-boxs-x-y-height-and-width-of-a-rotated-element-via-jav
        float rotationInRadians = getRotationInRadians(rotationAngle);
        return (float) (Math.sin(rotationInRadians) * height + Math.cos(rotationInRadians) * width);
    }


    /**
     * @return
     */
    @Override
    public float getHeight() {
        float rotationInRadians = getRotationInRadians(rotationAngle);
        return (float) (Math.sin(rotationInRadians) * width + Math.cos(rotationInRadians) * height);
    }

    /**
     * @return
     */
    @Override
    public float getX() {
        return -(getWidth() / 2) + getCenterX();
    }

    /**
     * @return
     */
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

        if (rotationAngle >= 0 && rotationAngle < 90) {
            return (float) (distance * Math.cos(Math.toRadians(rotationAngle))) + getCenterX();
        } else if (rotationAngle >= 90 && rotationAngle < 180) {
            return -(float) (distance * Math.sin(Math.toRadians(rotationAngle - 90))) + getCenterX();
        } else if (rotationAngle >= 180 && rotationAngle < 270) {
            return -(float) (distance * Math.cos(Math.toRadians(rotationAngle - 180))) + getCenterX();
        } else if (rotationAngle >= 270 && rotationAngle <= 359) {
            return (float) (distance * Math.sin(Math.toRadians(rotationAngle - 270))) + getCenterX();
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

        if (rotationAngle >= 0 && rotationAngle < 90) {
            return (float) (distance * Math.sin(Math.toRadians(rotationAngle))) + getCenterY();
        } else if (rotationAngle >= 90 && rotationAngle < 180) {
            return (float) (distance * Math.cos(Math.toRadians(rotationAngle - 90))) + getCenterY();
        } else if (rotationAngle >= 180 && rotationAngle < 270) {
            return -(float) (distance * Math.sin(Math.toRadians(rotationAngle - 180))) + getCenterY();
        } else if (rotationAngle >= 270 && rotationAngle <= 359) {
            return -(float) (distance * Math.cos(Math.toRadians(rotationAngle - 270))) + getCenterY();
        }
        return 0;
    }

    /**
     * Destroy plane and bullet
     *
     * @param bullet
     */
    @Override
    public void objectWasHitByBullet(Bullet bullet) {
        if (bullet.getShooter() != this) {
            world.deleteGameObject(bullet);
            if (destructible) {
                explode();
            }
        }
    }

    private void explode() {
        world.scoreboard.reportDeath(playerNumber);
        world.deleteGameObject(this);
        Alarm respawntimer = new Alarm("respawntimer", 1);
        respawntimer.addTarget(this);
        respawntimer.start();
    }

    @Override
    public void triggerAlarm(String alarmName) {
        if (world.getIsPlaying()) {
            if (alarmName == "respawntimer") {
                x = spawnpointX;
                y = spawnpointY;
                xSpeed = 0;
                ySpeed = -2;
                rotationAngle = 0;
                thrustIsOn = false;
                rotateLeft = false;
                rotateRight = false;
                canShoot = true;
                destructible = true;
                powerups.clear();
                world.addGameObject(this);
            }
            if (alarmName == "shootTimer") {
                canShoot = true;
            }
        }
    }

    /**
     * Makes the plane shoot a bullet
     */
    public void shoot() {
        if (canShoot) {
            if (playerNumber == 1) {
                float bulletRotation;

                if (rotationAngle + 90 >= 360) {
                    bulletRotation = rotationAngle - 270;
                } else {
                    bulletRotation = rotationAngle + 90;
                }

                world.addGameObject(new Bullet(world, this, bulletRotation, 12), getBulletX(), getBulletY());
            } else if (playerNumber == 2) {
                float bulletRotation;

                if (rotationAngle - 90 < 0) {
                    bulletRotation = rotationAngle + 270;
                } else {
                    bulletRotation = rotationAngle - 90;
                }

                world.addGameObject(new Bullet(world, this, bulletRotation, 12), getBulletX(), getBulletY());
            }
            canShoot = false;
            shootTimer.start();
        }
    }

    /**
     * makes plane immortal and mortal
     *
     * @param bool
     */
    public void setDestructible(boolean bool) {
        destructible = bool;
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        if (playerNumber == 1) {
            if (key == 'w') {
                thrustIsOn = true;
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
                thrustIsOn = true;
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
                thrustIsOn = false;
            }
            if (key == 'a') {
                rotateLeft = false;
            }
            if (key == 'd') {
                rotateRight = false;
            }

        } else if (playerNumber == 2) {

            if (keyCode == world.UP) {
                thrustIsOn = false;
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
        if (rotateLeft) {
            if ((rotationAngle - 1) < 0) {
                rotationAngle = 359;
            } else {
                rotationAngle = rotationAngle - 2;
            }
        }
        if (rotateRight) {
            if ((rotationAngle + 1) > 359) {
                rotationAngle = 0;
            } else {
                rotationAngle = rotationAngle + 2;
            }
        }

        if (thrustIsOn) {
            float distance = 0;
            if (playerNumber == 1) {
                distance = 6;
            } else if (playerNumber == 2){
                distance = -6;
            }
            if (rotationAngle >= 0 && rotationAngle < 90) {
                xMaxSpeed = +(float) (distance * Math.cos(Math.toRadians(rotationAngle)));
                yMaxSpeed = +(float) (distance * Math.sin(Math.toRadians(rotationAngle)));
            } else if (rotationAngle >= 90 && rotationAngle < 180) {
                xMaxSpeed = -(float) (distance * Math.sin(Math.toRadians(rotationAngle - 90)));
                yMaxSpeed = +(float) (distance * Math.cos(Math.toRadians(rotationAngle - 90)));
            } else if (rotationAngle >= 180 && rotationAngle < 270) {
                xMaxSpeed = -(float) (distance * Math.cos(Math.toRadians(rotationAngle - 180)));
                yMaxSpeed = -(float) (distance * Math.sin(Math.toRadians(rotationAngle - 180)));
            } else if (rotationAngle >= 270 && rotationAngle <= 359) {
                xMaxSpeed = +(float) (distance * Math.sin(Math.toRadians(rotationAngle - 270)));
                yMaxSpeed = -(float) (distance * Math.cos(Math.toRadians(rotationAngle - 270)));
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

        if (y > world.height - 100) {
            explode();
        }

        for (IPowerUps p : powerups) {
            p.applyPowerUp(this);
        }
    }



    /**
     * adds a point to the player
     */
    public void addPoint() {
        world.scoreboard.scorePoint(playerNumber);
    }

    @Override
    public void objectCollidedWithPowerUp(IPowerUps powerUp) {
        powerups.add(powerUp);
        powerUp.delete();
    }

}
