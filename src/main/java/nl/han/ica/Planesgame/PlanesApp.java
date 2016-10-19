package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

public class PlanesApp extends GameEngine {

	private Plane plane;

	public static void main(String[] args) {
		PApplet.main(new String[]{"nl.han.ica.Planesgame.PlanesApp"});
	}

	@Override
	public void setupGame() {

        View view = new View(1280, 720);
        setView(view);
        size(1366, 920);
        Balloon b = new Balloon(this);
        this.addGameObject(b);
		Plane plane = new Plane(this);
	}

	@Override
	public void update() {
		background(255);
	}
}
