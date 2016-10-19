package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Bullet extends SpriteObject {

	private int speed;

	private int rotation;
	private PlanesApp world;

	private ICanShootBullets shooter;

	public ICanShootBullets getShooter() {
		return shooter;
	}


	public Bullet(PlanesApp world, ICanShootBullets shooter, int rotation) {
		super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/cannonbulletsprite.png"));
		this.shooter = shooter;
		this.rotation = rotation;
		this.speed = shooter instanceof Plane ? -28 : -10;
		setySpeed(speed);
		this.world = world;
	}

	@Override
	public void update() {
		if (getY() <=0) {
		world.deleteGameObject(this);
	}

	}
}
