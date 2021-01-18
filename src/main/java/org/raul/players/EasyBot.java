package org.raul.players;

import org.raul.tictactoe.TicTacToe;

public class EasyBot extends Bot {

    public EasyBot(TicTacToe tictac, String jogandoDe, String jogandoContra) {
        super(tictac, jogandoDe, jogandoContra);
        botLevel = "easy";
    }

}
