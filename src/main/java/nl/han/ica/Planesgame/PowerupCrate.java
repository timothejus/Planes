package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class PowerupCrate extends HittableMovingObject {


	public PowerupCrate(PlanesApp world) {
		super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/cratesprite.png"), world);
		this.world = world;
		setySpeed(5 / 10f);
	}

	@Override
	public void objectWasHitByBullet(Bullet bullet) {
		if(bullet.getShooter() instanceof Plane) {
			world.deleteGameObject(this);
			switch ((int) (Math.random() * ((2) + 1))) {
				case 0:
					world.addGameObject(new EMP(world), this.getX(), this.getY());
					break;
				case 1:
					world.addGameObject(new BalloonMagnet(world), this.getX(), this.getY());
					break;
				case 2:
					world.addGameObject(new Godmode(world), this.getX(), this.getY());
					break;
			}
		}

	}

	@Override
	public void update() {
		if (getY() >= 920) {
			world.deleteGameObject(this);
		}
	}
}
