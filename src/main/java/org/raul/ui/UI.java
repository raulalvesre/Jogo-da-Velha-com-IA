package org.raul.ui;

import org.raul.coordenada.ChecadorDeCoordenadas;
import org.raul.players.*;
import org.raul.tictactoe.TicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static Scanner scanner = new Scanner(System.in);
    private static TicTacToe tictac;
    private String xPlayerType;
    private String oPlayerType;
    private Player xPlayer;
    private Player oPlayer;
    private ChecadorDeCoordenadas checadorDeCoordenadas;
    private List<String> possibleParameters;

    public UI() {
        scanner = new Scanner(System.in);
        possibleParameters = List.of("easy", "medium", "hard", "user");
        checadorDeCoordenadas = new ChecadorDeCoordenadas(tictac);
    }

    public void  start() {
        while (true) {
            System.out.print("Input command: ");
            String input = scanner.nextLine();
            String[] commandWords = input.split(" ");

            if (commandWords[0].equals("start") && commandWords.length == 3) {
                xPlayerType = commandWords[1];
                oPlayerType = commandWords[2];
                if (possibleParameters.contains(xPlayerType) && possibleParameters.contains(oPlayerType)) {
                    startGame();
                } else {
                    System.out.println("Bad parameters!");
                }
            } else if (commandWords[0].equals("exit")) {
                break;
            } else {
                System.out.println("Bad parameters!");
            }
        }

    }

    private void startGame() {
        tictac = new TicTacToe();
        checadorDeCoordenadas.atualizarJogo(tictac);
        setPlayers();
        printGameField();

        while (true) {
            if (!(tictac.turn % 2 == 0)) {
                tictac.whoseTurn = "X";
                this.xPlayer.makeAMove();
            } else  {
                tictac.whoseTurn = "O";
                this.oPlayer.makeAMove();
            }
            printGameField();
            if (gameIsOver())
                break;
            tictac.turn++;
        }

    }

    private void setPlayers() {
        this.xPlayer = createPlayer(this.xPlayerType, "X", "O");
        this.oPlayer = createPlayer(this.oPlayerType, "O", "X");
    }

    private Player createPlayer(String playerType, String playingAs, String playingAgainst) {
        if (playerType.equals("easy")) {
            return new EasyBot(tictac, playingAs , playingAgainst);
        } else if (playerType.equals("medium")) {
            return new MediumBot(tictac, playingAs, playingAgainst);
        } else if (playerType.equals("user")) {
            return new HumanPlayer(tictac, playingAs);
        } else if (playerType.equals("hard")) {
            return new HardBot(tictac, playingAs, playingAgainst);
        }

        return null;
    }

    public static void printGameField() {
        System.out.println("---------");
        for (int y = 0; y < 3; y++) {
            System.out.print("| ");
            for (int x = 0; x < 3; x++) {
                System.out.print(tictac.getGameField()[y][x] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private boolean gameIsOver() {
        if (tictac.whoWon("X")) {
            System.out.println("X wins");
            tictac.turn = 1;
            return true;
        } else if (tictac.whoWon("O")) {
            System.out.println("O wins");
            tictac.turn = 1;
            return true;
        } else if (tictac.fieldIsFull()) {
            System.out.println("Draw");
            tictac.turn = 1;
            return true;
        }
        return false;
    }

}
