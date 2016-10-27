package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class PowerupCrate extends HittableMovingObject {

	private int speed;

	private HittableMovingObject shootableObject;

	public PowerupCrate(PlanesApp world) {
		super(new Sprite(""), world);
	}

	public void objectWasHitByBullet() {

	}

	@Override
	public void objectWasHitByBullet(ICanShootBullets shooter) {

	}

	@Override
	public void update() {

	}
}
