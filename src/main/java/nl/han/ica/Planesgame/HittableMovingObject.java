package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

import java.util.List;

public abstract class HittableMovingObject extends SpriteObject implements ICollidableWithGameObjects {

	protected int speed;
	protected PlanesApp world;
	public HittableMovingObject(Sprite sprite, PlanesApp world) {
		super(sprite);
		this.world = world;
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for(GameObject go : collidedGameObjects) {
			if (go instanceof Bullet) {
				world.deleteGameObject(go);
				objectWasHitByBullet(((Bullet)go));
			}
		}
	}

	public abstract void objectWasHitByBullet(Bullet bullet);

}
