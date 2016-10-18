package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;

public class Balloon extends HittableMovingObject {

	private int speed;
	private PlanesApp world;

	private HittableMovingObject hittableMovingObject;

	public Balloon(Sprite sprite) {
		super(sprite);
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
		if (getY() <=0) {
			world.deleteGameObject(this);
		}
	}
}
