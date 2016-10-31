package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class BalloonMagnet extends SpriteObject implements IPowerUps {
	private PlanesApp world;

	public BalloonMagnet(PlanesApp world){
		super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/magnetsprite.png"));
		this.world = world;
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

