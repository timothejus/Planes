package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Plane extends HittableMovingObject implements ICanShootBullets {

	private float ax;
	private float ay;
	private float vx;
	private float vy;
	private int rotation;
	private int playerNumber;
	private int score;

	public Plane(){
		super(new Sprite(""));


	}

	public void objectWasHitByBullet(ICanShootBullets shooter) {

	}

	public void shoot() {

	}

	public void keyPressed() {

	}

	public void tileCollisionOccurred() {

	}

	public void update() {

	}

	public void addPoint(){
		score++;
	}

	public void removePoint(){
		score--;
	}

}
