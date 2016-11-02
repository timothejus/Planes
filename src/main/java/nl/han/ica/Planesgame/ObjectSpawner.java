package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

import java.util.ArrayList;

/**
 * @author Tim Hendriksen
 * Spawns the objects: Cannons, Balloons and Powerups
 */
public class ObjectSpawner implements IAlarmListener {

    private ArrayList<Cannon> cannons = new ArrayList<>();
    private PlanesApp world;


    /**
     *
     * @param world
     */
    public ObjectSpawner(PlanesApp world){
        this.world = world;
    }

    /**
     * Start the timers for objects
     */
    public void start() {
        Alarm cannonAlarm = new Alarm("cannon", 30);
        cannonAlarm.addTarget(this);
        cannonAlarm.start();

        Alarm balloonAlarm = new Alarm("balloon", 1 + (int)(Math.random() * ((20 - 10) + 1)));
        balloonAlarm.addTarget(this);
        balloonAlarm.start();

        Alarm powerupAlarm = new Alarm("powerup", 40 + (int)(Math.random() * ((20 - 10) + 1)));
        powerupAlarm.addTarget(this);
        powerupAlarm.start();

    }

    @Override
    public void triggerAlarm(String alarmName) {
        switch(alarmName){
            case "cannon":
                spawnCannon();
                break;
            case "balloon":
                spawnBalloon();
                break;
            case "powerup":
                SpawnPowerupcrate();
                break;
        }
    }

    private void SpawnPowerupcrate() {
        PowerupCrate p = new PowerupCrate(world);
        world.addGameObject(p,(int)(Math.random() * ((1340) + 1)),0);
    }

    private void spawnBalloon() {
        Balloon b = new Balloon(world);
        world.addGameObject(b,(int)(Math.random() * ((1340) + 1)),920);
    }

    private void spawnCannon() {
        float xPos = 40 + (int) (Math.random() * ((1340 - 40) + 1));
        boolean canPlace = true;

        for (Cannon cannon : cannons) {
            if (xPos > cannon.getX() - 60 && xPos < cannon.getX() + 70) {
                canPlace = false;
                break;
            }
        }

        if (canPlace) {
            Cannon newCannon = new Cannon(world);
            world.addGameObject(newCannon, xPos, 870);
            cannons.add(newCannon);
        }

        if (cannons.size() <= 13) {
            start();
        }
    }
}
