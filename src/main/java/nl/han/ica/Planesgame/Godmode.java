package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

/**
 * @author Tim Hendriksen
 * Makes the plane that collected the poerup indestructible
 */
public class Godmode extends SpriteObject implements IPowerUps {
	private PlanesApp world;
	Plane p;

	/**
	 *
	 * @param world
	 */
	public Godmode(PlanesApp world){
		super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/godsprite.png"));
		this.world = world;
	}

	/**
	 * @see IPowerUps#applyPowerUp(Plane)
	 */
	public void applyPowerUp(Plane p) {
		this.p = p;
		p.setDestructible(false);
	}

	@Override
	public void delete() {
		world.deleteGameObject(this);
	}


	@Override
	public void update() {

	}
}
