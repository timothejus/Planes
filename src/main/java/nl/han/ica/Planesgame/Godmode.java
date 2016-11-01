package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Godmode extends SpriteObject implements IPowerUps {
	private PlanesApp world;
	Plane p;

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
