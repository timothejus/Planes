package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class EMP extends SpriteObject implements IPowerUps {
	private PlanesApp world;

	public EMP(PlanesApp world){
		super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/empsprite.png"));
		this.world = world;
	}

	/**
	 * @see IPowerUps#applyPowerUp(Plane)
	 */
	public void applyPowerUp(Plane p) {
		System.out.println("EMP");
	}

	@Override
	public void delete() {
		world.deleteGameObject(this);
	}

	@Override
	public void update() {

	}
}
