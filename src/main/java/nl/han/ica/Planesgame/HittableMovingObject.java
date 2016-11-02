package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

import java.util.List;

/**
 * @author Tim Hendriksen
 */
public abstract class HittableMovingObject extends SpriteObject implements ICollidableWithGameObjects {

	protected int speed;
	protected PlanesApp world;

	/**
	 *
	 * @param sprite
	 * @param world
	 */
	public HittableMovingObject(Sprite sprite, PlanesApp world) {
		super(sprite);
		this.world = world;
	}

	/**
	 * checks if the object was a bullet or a powerup
	 * @param collidedGameObjects
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for(GameObject go : collidedGameObjects) {
			if (go instanceof Bullet) {
				objectWasHitByBullet(((Bullet)go));
			}
			if(go instanceof IPowerUps){
				objectCollidedWithPowerUp(((IPowerUps)go));
			}
		}
	}

	/**
	 *
	 * @param bullet
	 */
	public abstract void objectWasHitByBullet(Bullet bullet);

	/**
	 *
	 * @param powerUp
	 */
	public void objectCollidedWithPowerUp(IPowerUps powerUp){};

}
