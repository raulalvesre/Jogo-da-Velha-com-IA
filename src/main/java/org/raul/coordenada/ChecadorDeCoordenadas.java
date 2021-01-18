package org.raul.coordenada;

import org.raul.tictactoe.TicTacToe;

import java.util.ArrayList;

public class ChecadorDeCoordenadas {

    private TicTacToe tictac;

    public ChecadorDeCoordenadas(TicTacToe tictactoe) {
        this.tictac = tictactoe;
    }

    public boolean coordinateBPIsOkay(String coordinateBlueprint, boolean isBot) {
        if (!coordinateBPOnlyNumbers(coordinateBlueprint)) {
            if (!isBot)
                System.out.println("You should enter numbers!");
            return false;
        } else if (!coordinateBPAreInRange(coordinateBlueprint)) {
            if (!isBot)
                System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else if (coordinateBPOccupied(coordinateBlueprint)) {
            if (!isBot) {
                System.out.println("This cell is occupied! Choose another one!");
            }
            return false;
        }

        return true;
    }

    public boolean coordinateBPOnlyNumbers(String coordinateBlueprint) {
        String[] coordinate = coordinateBlueprint.split(" ");
        boolean numeric = true;

        try {
            int x = Integer.valueOf(coordinate[0]);
            int y = Integer.valueOf(coordinate[1]);
        } catch (NumberFormatException e) {
            numeric = false;
        }

        return numeric;
    }

    public boolean coordinateBPAreInRange(String coordinateBlueprint) {
        String[] coordinate = coordinateBlueprint.split(" ");
        int x = Integer.valueOf(coordinate[0]);
        int y = Integer.valueOf(coordinate[1]);

        if ((x > 3 || x < 1) || (y > 3 || y < 1)) {
            return false;
        }

        return true;
    }

    public ArrayList<Coordinate> emptyCoordinates() {
        ArrayList<Coordinate> emptyCoordinates = new ArrayList<>();

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Coordinate coordenadaAtual = new Coordinate(y, x);
                if (CoordinateIsEmpty(coordenadaAtual))
                    emptyCoordinates.add(coordenadaAtual);
            }
        }

        return emptyCoordinates;
    }

    public boolean CoordinateIsEmpty(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return tictac.getGameField()[y][x].equals(" ");
    }

    public boolean coordinateBPOccupied(String coordinateBlueprint) {
        return !CoordinateIsEmpty(new Coordinate(coordinateBlueprint));
    }

    public Coordinate whoCoordenadaVencedoraLinha(String who) {
        int aliadosNaLinha;
        int vaziosNaLinha;
        int xDaCoordenadaVazia;

        for (int y = 0; y < 3; y++) {
            aliadosNaLinha = 0;
            vaziosNaLinha = 0;
            xDaCoordenadaVazia = 0;

            for (int x = 0; x < 3; x++) {
                if (tictac.getGameField()[y][x].equals(who))
                    aliadosNaLinha++;

                if (tictac.getGameField()[y][x].equals(" ")) {
                    xDaCoordenadaVazia = x;
                    vaziosNaLinha++;
                }
            }

            if (aliadosNaLinha == 2 && vaziosNaLinha == 1) {
                return new Coordinate(y, xDaCoordenadaVazia);
            }

        }

        return null;
    }

    public Coordinate whoCoordenadaVencedoraColuna(String who) {
        int aliadosNaColuna;
        int vaziosNaColuna;
        int yDaCoordenadaVazia;

        for (int x = 0; x < 3; x++) {
            aliadosNaColuna = 0;
            vaziosNaColuna = 0;
            yDaCoordenadaVazia = 0;

            for (int y = 0; y < 3; y++) {
                if (tictac.getGameField()[y][x].equals(who))
                    aliadosNaColuna++;

                if (tictac.getGameField()[y][x].equals(" ")) {
                    vaziosNaColuna++;
                    yDaCoordenadaVazia = y;
                }
            }

            if (aliadosNaColuna == 2 && vaziosNaColuna == 1) {
                return new Coordinate(yDaCoordenadaVazia, x);
            }
        }

        return null;
    }

    public Coordinate whoCoordenadaVencedoraDiag(String who) {
        int aliadosNaDiag;
        int vaziosNaDiag;
        int linhaDaCoordenadaVazia;
        int colunaDaCoordenadaVazia;

        for (int i = 0; i < 2; i++) {
            aliadosNaDiag = 0;
            vaziosNaDiag = 0;
            linhaDaCoordenadaVazia = 0;
            colunaDaCoordenadaVazia = 0;
            for (int j = 0; j < 3; j++) {
                if (tictac.getGameField()[Math.abs((i * 2) - j)][j].equals(who)) {
                    aliadosNaDiag++;
                } else if (tictac.getGameField()[Math.abs((i * 2) - j)][j].equals(" ")) {
                    vaziosNaDiag++;
                    linhaDaCoordenadaVazia = Math.abs((i * 2) - j);
                    colunaDaCoordenadaVazia = j;
                }
            }

            if (aliadosNaDiag == 2 && vaziosNaDiag == 1) {
                return new Coordinate(linhaDaCoordenadaVazia, colunaDaCoordenadaVazia);
            }
        }

        return null;
    }

    public void atualizarJogo(TicTacToe tictac) {
        this.tictac = tictac;
    }

}
