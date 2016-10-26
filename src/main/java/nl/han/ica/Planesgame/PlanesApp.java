package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class PlanesApp extends GameEngine implements IAlarmListener {

	private Plane plane;
	ArrayList<Cannon> cannons = new ArrayList<>();

	public static void main(String[] args) {
		PApplet.main(new String[]{"nl.han.ica.Planesgame.PlanesApp"});
	}

	@Override
	public void setupGame() {

        View view = new View(1366, 920);
		view.setBackground(loadImage("src/main/java/nl/han/ica/Planesgame/resources/background.png"));
        setView(view);
        size(1366, 920);
        Balloon b = new Balloon(this);
        this.addGameObject(b,300,920);
		Plane plane = new Plane(this, "src/main/java/nl/han/ica/Planesgame/resources/planesprite1.png", 1);
		Plane plane2 = new Plane(this, "src/main/java/nl/han/ica/Planesgame/resources/planesprite2.png", 2);
		startAlarm();
	}

	@Override
	public void update() {
		//background(255);
	}

	private void startAlarm() {
		Alarm alarm=new Alarm("New cannon",10 + (int)(Math.random() * ((20 - 10) + 1)));
		//Alarm alarm = new Alarm("New cannon",1 + (int)(Math.random() * ((5 - 1) + 1)));
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		float xPos = 40 + (int)(Math.random() * ((1340 - 40) + 1));
		boolean canPlace = true;

		for(Cannon cannon : cannons){
			if(xPos > cannon.getX() - 60 && xPos < cannon.getX() + 70){
				canPlace = false;
				break;
			}
		}

		if(canPlace){
			Cannon newCannon = new Cannon(this);
			this.addGameObject(newCannon, xPos, 870);
			cannons.add(newCannon);
		}

		if(cannons.size() <= 13){
			startAlarm();
		}

	}
}
