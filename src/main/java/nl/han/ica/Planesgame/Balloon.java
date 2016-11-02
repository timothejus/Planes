package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;

/**
 * @author Tim Hendriksen
 * The balloon is collectable by the players plane to get a point
 */
public class Balloon extends HittableMovingObject {

	private PlanesApp world;

	/**
	 *
	 * @param world PlanesApp world
	 */
	public Balloon(PlanesApp world) {
		super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/balloonsprite.png"), world);
		this.world = world;
		setySpeed(-2);
	}

	/**
	 *if plane collects balloon get point
	 * if bullet is shot by cannon ignore otherwise destroy
	 * @param collidedGameObjects GameObject List
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if(go instanceof Bullet){
				objectWasHitByBullet(((Bullet) go));
			}
			else if(go instanceof Plane){
				((Plane) go).addPoint();
			}
		}
		world.deleteGameObject(this);
	}

	/**
	 * Delete bullet
	 * @param bullet
	 */
	@Override
	public void objectWasHitByBullet(Bullet bullet) {
		if(bullet.getShooter() instanceof Plane) {
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
