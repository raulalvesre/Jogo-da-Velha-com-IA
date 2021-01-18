package org.raul.players;

import org.raul.coordenada.ChecadorDeCoordenadas;
import org.raul.coordenada.Coordinate;
import org.raul.tictactoe.TicTacToe;

import java.util.Random;

public abstract class Bot implements Player {

    protected TicTacToe tictac;
    protected Random random;
    protected ChecadorDeCoordenadas checadorDeCoordenadas;
    protected String botLevel = "";
    protected String me;
    protected String enemy;

    public Bot(TicTacToe tictac, String jogandoDe, String jogandoContra) {
        this.tictac = tictac;
        this.me = jogandoDe;
        this.enemy = jogandoContra;
        random = new Random();
        checadorDeCoordenadas = new ChecadorDeCoordenadas(tictac);
    }

    @Override
    public void makeAMove() {
        Coordinate choosenCoordinate = pickCoordinates();
        tictac.markWhoInCoordinate(choosenCoordinate, me);
    }

    public Coordinate pickCoordinates () {
        System.out.println("Making move level \"" + botLevel + "\"");
        while (true) {
            int x = random.nextInt(3 - 1 + 1) + 1;
            int y = random.nextInt(3 - 1 + 1) + 1;
            Coordinate coordinate;
            String coordinateBlueprint = x + " " + y;

            if (checadorDeCoordenadas.coordinateBPIsOkay(coordinateBlueprint, true)) {
                coordinate = new Coordinate(coordinateBlueprint);
                return coordinate;
            }
        }
    }

}
