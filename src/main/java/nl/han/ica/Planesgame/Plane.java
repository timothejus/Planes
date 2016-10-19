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

	public Plane(PlanesApp app){
		super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/planesprite1.png"));
		x = 50;
		y = 50;
		app.addGameObject(this, x,y);

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
