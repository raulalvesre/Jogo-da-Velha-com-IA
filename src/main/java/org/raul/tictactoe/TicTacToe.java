package org.raul.tictactoe;

import org.raul.coordenada.Coordinate;

public class TicTacToe {

    private String[][] gameField;
    private String player1;
    private String player2;
    public String whoseTurn;
    public int turn;

    public TicTacToe () {
        this.gameField = new String[3][3];
        turn = 1;
        createField();
    }

    public String[][] getGameField() {
        return gameField;
    }

    private void createField() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                gameField[y][x] = " ";
            }
        }
    }

    public void markWhoInCoordinate (Coordinate coordinate, String  who) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        gameField[y][x] = who;
    }

    public boolean whoWon(String who) {
        return (whoWonLinhas(who) || whoWonColumns(who) || whoWonDiagonais(who));
    }

    private boolean whoWonLinhas(String who) {
        int quantosNaLinha;

        for (int y = 0; y < 3; y++) {
            quantosNaLinha = 0;
            for (int x = 0; x < 3; x++) {
                if (gameField[y][x].equals(who)) {
                    quantosNaLinha++;
                }
            }

            if (quantosNaLinha == 3) {
                return true;
            }
        }

        return false;
    }

    private boolean whoWonColumns(String who) {
        int quantosNaColuna;

        for (int x = 0; x < 3; x++) {
            quantosNaColuna = 0;
            for (int y = 0; y < 3; y++) {
                if (gameField[y][x].equals(who)) {
                    quantosNaColuna++;
                }
            }

            if (quantosNaColuna == 3) {
                return true;
            }
        }

        return false;
    }

    private boolean whoWonDiagonais(String who) {
        int quantosNaDiag;

        for (int i = 0; i < 2; i++) {
            quantosNaDiag = 0;
            for (int j = 0; j < 3; j++) {
                if (gameField[Math.abs((i * 2) - j)][j].equals(who)) {
                    quantosNaDiag++;
                }
            }

            if (quantosNaDiag == 3) {
                return true;
            }

        }
        return false;
    }

    public boolean fieldIsFull() {
        boolean hasEmptyCell = false;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (gameField[y][x].equals(" ")) {
                    hasEmptyCell = true;
                    break;
                }
            }
        }

        return !hasEmptyCell;
    }

}
