package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Bullet extends SpriteObject implements ICanShootBullets {

	private int speed;

	private int rotation;

	private ICanShootBullets shooter;

	public ICanShootBullets getShooter() {
		return shooter;
	}


	public Bullet(ICanShootBullets shooter) {
		super(new Sprite(""));
		this.shooter = shooter;
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
