package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Bullet extends SpriteObject implements ICanShootBullets {

	private int speed;

	private int rotation;

	public void getShooter() {

	}

	public Bullet(ICanShootBullets shooter) {
		super(new Sprite(""));

	}


	/**
	 * @see ICanShootBullets#shoot()
	 */
	public void shoot() {

	}

	@Override
	public void update() {

	}
}
