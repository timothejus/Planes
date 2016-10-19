package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;

public class Balloon extends HittableMovingObject {

	private PlanesApp world;

	public Balloon(PlanesApp world) {
		super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/balloonsprite.png"));
		this.world = world;
		setySpeed(-10 / 10f);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if(go instanceof Bullet){
				objectWasHitByBullet(((Bullet) go).getShooter());
			}
			else if(go instanceof Plane){
				((Plane) go).addPoint();
			}
		}
	}

	@Override
	public void objectWasHitByBullet(ICanShootBullets shooter) {
		if(shooter instanceof Plane) {
			world.deleteGameObject(this);
		}
	}

	@Override
	public void update() {
		if (getY() <=-50) {
			world.deleteGameObject(this);
		}
	}
}
