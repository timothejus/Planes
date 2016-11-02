package nl.han.ica.Planesgame;

/**
 * @author Tim Hendriksen
 * The interface that difines a powerup
 */
public interface IPowerUps {

	/**
	 * Apply powerup
	 * @param p
	 */
	public abstract void applyPowerUp(Plane p);

	/**
	 * Deletes The object
	 */
	public  abstract void delete();

}
