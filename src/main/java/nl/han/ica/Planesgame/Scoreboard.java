package nl.han.ica.Planesgame;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * @author Rogier Grobbee
 */
public class Scoreboard extends GameObject {

    String text;
    private int p1 = 0;
    private int p2 = 0;

    /**
     *
     * @param world
     * @param winningScore
     */
    public Scoreboard(PlanesApp world, int winningScore) {

    }

    public void scorePoint(int playernumber){
        if (playernumber == 1){
            p1++;
        }
        else if (playernumber == 2) {
            p2++;
        }
    }

    public void reportDeath(int playernumber){
        if (playernumber == 1){
            p2++;
        }
        else if (playernumber == 2) {
            p1++;
        }
    }

    @Override
    public void update() {

    }


    @Override
    public void draw(PGraphics g) {
        text = "Scoreboard\nPlayer1: " + p1 + "\nPlayer2: " + p2;
        g.textAlign(g.LEFT,g.TOP);
        g.textSize(20);
        g.text(text,getX(),getY());
    }
}
