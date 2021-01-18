package org.raul.coordenada;

public class CoordinateWithScore extends Coordinate{

    public int score;

    public CoordinateWithScore(int y, int x, int score) {
        super(y, x);
        this.score = score;
    }

}
