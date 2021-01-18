package org.raul.coordenada;

public class Coordinate {

    protected int y;
    protected int x;

    public Coordinate(String coordinateBlueprint) {
        String[] coordinate = coordinateBlueprint.split(" ");

        this.y = Integer.parseInt(coordinate[0]) - 1;
        this.x = Integer.parseInt(coordinate[1]) - 1;
    }

    public Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

}
