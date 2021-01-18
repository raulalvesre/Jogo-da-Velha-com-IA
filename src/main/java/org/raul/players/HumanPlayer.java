package org.raul.players;

import org.raul.coordenada.ChecadorDeCoordenadas;
import org.raul.coordenada.Coordinate;
import org.raul.tictactoe.TicTacToe;
import org.raul.ui.UI;

import java.util.Scanner;

public class HumanPlayer implements Player{

    private TicTacToe tictac;
    private Scanner scanner  = UI.scanner;
    protected ChecadorDeCoordenadas checadorDeCoordenadas;
    protected String me;

    public HumanPlayer(TicTacToe tictac, String jogandoDe) {
        this.tictac = tictac;
        this.me = jogandoDe;
        checadorDeCoordenadas = new ChecadorDeCoordenadas(tictac);
    }

    @Override
    public void makeAMove() {
        Coordinate choosenCoordinate = pickCoordinates();
        tictac.markWhoInCoordinate(choosenCoordinate, me);
    }

    public Coordinate pickCoordinates() {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String coordinateBlueprint = scanner.nextLine();
            Coordinate coordinate;

            if (checadorDeCoordenadas.coordinateBPIsOkay(coordinateBlueprint, false)) {
                coordinate = new Coordinate(coordinateBlueprint);
                return coordinate;
            }
        }
    }

}
