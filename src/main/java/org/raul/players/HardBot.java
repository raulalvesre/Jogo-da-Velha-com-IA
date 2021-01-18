package org.raul.players;

import org.raul.coordenada.Coordinate;
import org.raul.coordenada.CoordinateWithScore;
import org.raul.tictactoe.TicTacToe;

import java.util.ArrayList;

public class HardBot extends Bot {

    public HardBot(TicTacToe tictac, String jogandoDe, String jogandoContra) {
        super(tictac, jogandoDe, jogandoContra);
        botLevel = "hard";
    }

    @Override
    public Coordinate pickCoordinates() {
        System.out.println("Making move level \"" + botLevel + "\"");
        return minimax(tictac, me);
    }

    private CoordinateWithScore minimax(TicTacToe newTTT, String player) {

        ArrayList<Coordinate> emptySpots = checadorDeCoordenadas.emptyCoordinates();

        if (newTTT.whoWon(me)) {
            return new CoordinateWithScore(0, 0, 10);
        } else if (newTTT.whoWon(enemy)) {
            return new CoordinateWithScore(0, 0, -10);
        } else if (emptySpots.size() == 0) {
            return new CoordinateWithScore(0, 0, 0);
        }

        ArrayList<CoordinateWithScore> moves = new ArrayList<>();

        int coordinateScore;
        for (Coordinate c : emptySpots) {
            newTTT.markWhoInCoordinate(c, player);

            if (player.equals(me)) {
                coordinateScore = minimax(newTTT, enemy).score;
            } else {
                coordinateScore = minimax(newTTT, me).score;
            }

            newTTT.markWhoInCoordinate(c, " ");
            moves.add(new CoordinateWithScore(c.getY(), c.getX(), coordinateScore));
        }

        CoordinateWithScore bestCoordinate = null;
        if (player.equals(me)) {
            int bestValue = -99999;
            for (CoordinateWithScore c : moves) {
                if (c.score > bestValue) {
                    bestValue = c.score;
                    bestCoordinate = c;
                }
            }
        } else {
            int bestValue = 99999;
            for (CoordinateWithScore c : moves) {
                if (c.score < bestValue) {
                    bestValue = c.score;
                    bestCoordinate = c;
                }
            }
        }

        return bestCoordinate;
    }
}