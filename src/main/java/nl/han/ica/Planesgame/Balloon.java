package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

import java.util.List;

public class Balloon extends HittableMovingObject {

	private int speed;
	private PlanesApp world;

	private HittableMovingObject hittableMovingObject;

	public void objectWasHitByBullet() {
		//delete from world
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if(go instanceof Bullet){
				objectWasHitByBullet();
			}
			else if(go instanceof Plane){
				((Plane) go).addPoint();
				//add point to player
			}
		}
	}

	@Override
	public void objectWasHitByBullet(ICanShootBullets shooter) {

	}

	@Override
	public void update() {

	}
}
