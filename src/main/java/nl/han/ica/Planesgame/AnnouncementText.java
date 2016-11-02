package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * @author Rogier Grobbee
 * The textst that is displayed before the game starts
 */
public class AnnouncementText extends GameObject implements IAlarmListener {

    private String text;
    private int p1 = 0;
    private int p2 = 0;
    private final String startText = "TO START:\n" +
            "PRESS ANY NUMBER TO SET THE WINNING SCORE";
    private boolean startTextIsShowing;
    private PlanesApp world;

    /**
     *
     * @param world
     */
    public AnnouncementText(PlanesApp world) {
        this.world = world;
        world.addGameObject(this);
        x = world.Worldwidth / 2;
        y = world.Worldheight / 2;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        if (!world.getIsPlaying()) {
            if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9') {
                world.startGame((int)key);
            }
        }
    }

    /**
     * Shows the start text.
     */
    public void showStartText() {
        text =  startText;
        startTextIsShowing = true;
        Alarm startTextOff = new Alarm("startTextOff", 2);
        startTextOff.addTarget(this);
        startTextOff.start();
    }

    /**
     * Hides the start text
     */
    public void hideStartText() {
        startTextIsShowing = false;
        text = "";
    }

    @Override
    public void update() {

    }


    @Override
    public void draw(PGraphics g) {

        g.textAlign(g.CENTER,g.CENTER);
        g.textSize(40);
        g.text(text,getX(), getY());
    }

    /**
     * Makes the text blink
     * @param alarmName
     */
    @Override
    public void triggerAlarm(String alarmName) {
        if(alarmName == "startTextOn") {
            if (startTextIsShowing) {
                showStartText();
            }
        }
        if (alarmName == "startTextOff") {
            text = "";
            if (startTextIsShowing){
                Alarm startTextOn = new Alarm("startTextOn", 0.7);
                startTextOn.addTarget(this);
                startTextOn.start();
            }
        }
    }
}
