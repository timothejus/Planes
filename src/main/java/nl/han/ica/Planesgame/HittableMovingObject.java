package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

import java.util.List;

public abstract class HittableMovingObject extends SpriteObject implements ICollidableWithGameObjects {

	protected int speed;

	public HittableMovingObject() {
		super(new Sprite(""));

	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {


	}

	public abstract void objectWasHitByBullet(ICanShootBullets shooter);

}