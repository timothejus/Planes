package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class PlanesApp extends GameEngine {

	private Plane plane;
	private ObjectSpawner objectSpawner;

	public static void main(String[] args) {
		PApplet.main(new String[]{"nl.han.ica.Planesgame.PlanesApp"});
	}

	@Override
	public void setupGame() {

		objectSpawner = new ObjectSpawner(this);
		objectSpawner.start();

        View view = new View(1366, 920);
		view.setBackground(loadImage("src/main/java/nl/han/ica/Planesgame/resources/background.png"));
        setView(view);
        size(1366, 920);

		Plane plane = new Plane(this, "src/main/java/nl/han/ica/Planesgame/resources/planesprite1.png", 1);
		Plane plane2 = new Plane(this, "src/main/java/nl/han/ica/Planesgame/resources/planesprite2.png", 2);

	}

	@Override
	public void update() {
	}
}
