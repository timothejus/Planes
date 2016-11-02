package nl.han.ica.waterworld;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * @author Ralph Niels
 * Wordt gebruikt om een tekst te kunnen afbeelden
 */
public class TextObject extends GameObject {

    private String text;
    private int p1;
    private int p2;

    public TextObject(String text) {
        this.text=text;
    }

    public void setText(String text) {
        this.text=text;
    }

    public void updateScore(int score, int playernumber){
        if (playernumber == 1){
            p1 = score;
        }
        else if (playernumber == 2) {
            p2 = score;
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        text = "Scoreboard\nPlayer1: " + p1 + "\nPlayer2: " + p2;
        g.textAlign(g.LEFT,g.TOP);
        g.textSize(50);
        g.text(text,getX(),getY());
    }
}
