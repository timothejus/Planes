package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

import java.util.Random;

public class Cannon extends SpriteObject implements ICanShootBullets, IAlarmListener{

	PlanesApp world;

	public Cannon(PlanesApp world){
		super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/cannonsprite.png"));
		this.world = world;
		startAlarm();
	}

	public void shoot() {
		world.addGameObject(new Bullet(world, this, 0));
	}

	@Override
	public void update() {

	}
	private void startAlarm() {
		//Alarm alarm=new Alarm("New bullet",3 + (int)(Math.random() * ((7 - 3) + 1)));
		Alarm alarm=new Alarm("New bullet",3 + (Math.random() * ((7 - 3) + 1)));
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		Bullet b=new Bullet(world, this, 0);
		world.addGameObject(b,x + 17,y, -1);
		startAlarm();
	}
}
