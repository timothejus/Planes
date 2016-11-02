package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author Tim Hendriksen
 * When collected bij a plane all the balloons are attracted by the plane.
 */
public class BalloonMagnet extends SpriteObject implements IPowerUps {

	private PlanesApp world;


	/**
	 *
	 * @param world
	 */
	public BalloonMagnet(PlanesApp world){
		super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/magnetsprite.png"));
		this.world = world;
	}

	/**
	 * Changes the balloons velocity to go towards the plane
	 * @see IPowerUps#applyPowerUp(Plane)
	 */
	public void applyPowerUp(Plane p) {
		Vector<GameObject> gos = world.getGameObjectItems();
		for(GameObject go : gos){
			if(go instanceof Balloon){
				go.setxSpeed(((p.getX())-(go.getX()))/1000f);
				go.setySpeed((((p.getY())-(go.getY())))/100f);
			}
		}
	}

	@Override
	public void delete() {
		world.deleteGameObject(this);
	}

	@Override
	public void update() {

	}
}

