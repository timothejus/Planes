package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * @author Rogier Grobbee
 */
public class PlanesApp extends GameEngine {

    private ObjectSpawner objectSpawner;
    public Scoreboard scoreboard;
    public final int Worldwidth = 1366;
    public final int Worldheight = 920;
    private AnnouncementText annoucementText;
    private View view;
    private Boolean isPlaying = false;

	/**
	 *
	 * @param args
	 */
    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.Planesgame.PlanesApp"});
    }

    @Override
    public void setupGame() {
        view = new View(Worldwidth, Worldheight);
        setView(view);
        size(Worldwidth, Worldheight);
        annoucementText = new AnnouncementText(this);
        annoucementText.showStartText();
    }

    public void gameOver(int winnerNumber) {
        deleteAllGameOBjects();
        isPlaying = false;
        addGameObject(annoucementText);
        annoucementText.announceWinner(winnerNumber);
    }

	/**
	 *
	 * @return boolean
	 */
	public boolean getIsPlaying(){
        return isPlaying;
    }

	/**
	 * starts the game
	 * @param winningScore
	 */
	public void startGame(int winningScore) {
        isPlaying = true;
        annoucementText.hideStartText();
        objectSpawner = new ObjectSpawner(this);
        objectSpawner.start();
        scoreboard = new Scoreboard(this, winningScore);
        addGameObject(scoreboard);

        view.setBackground(loadImage("src/main/java/nl/han/ica/Planesgame/resources/background.png"));

        Plane plane = new Plane(this, "src/main/java/nl/han/ica/Planesgame/resources/planesprite1.png", 1, 50, 200);
        Plane plane2 = new Plane(this, "src/main/java/nl/han/ica/Planesgame/resources/planesprite2.png", 2, 1200, 200);
    }

    @Override
    public void update() {
    }
}
