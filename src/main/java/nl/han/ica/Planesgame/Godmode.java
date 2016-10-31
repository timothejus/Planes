package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Godmode extends SpriteObject implements IPowerUps {
	private PlanesApp world;

	public Godmode(PlanesApp world){
		super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/godsprite.png"));
		this.world = world;
	}

	public void applyPowerUp(Plane plane) {

	}


	/**
	 * @see IPowerUps#applyPowerUp()
	 */
	public void applyPowerUp() {

	}

	@Override
	public void update() {

	}
}
