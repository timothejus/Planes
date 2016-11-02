package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

import java.util.ArrayList;
import java.util.Vector;

public class BalloonMagnet extends SpriteObject implements IPowerUps {

	private PlanesApp world;


	public BalloonMagnet(PlanesApp world){
		super(new Sprite("src/main/java/nl/han/ica/Planesgame/resources/magnetsprite.png"));
		this.world = world;
	}

	/**
	 * @see IPowerUps#applyPowerUp(Plane)
	 */

//	Exception in thread "Thread-5" java.util.ConcurrentModificationException
//	at java.util.Vector$Itr.checkForComodification(Vector.java:1184)
//	at java.util.Vector$Itr.next(Vector.java:1137)
//	at nl.han.ica.Planesgame.BalloonMagnet.applyPowerUp(BalloonMagnet.java:25)
//	at nl.han.ica.Planesgame.Plane.update(Plane.java:334)
//	at nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine.updateGameObjects(GameEngine.java:309)
//	at nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine.updateGame(GameEngine.java:269)
//	at nl.han.ica.OOPDProcessingEngineHAN.Engine.GameThread.run(GameThread.java:81)
//	at java.lang.Thread.run(Thread.java:745)
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

