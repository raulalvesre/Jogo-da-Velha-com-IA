package org.raul.players;

import org.raul.coordenada.Coordinate;
import org.raul.tictactoe.TicTacToe;

public class MediumBot extends Bot {

    public MediumBot(TicTacToe tictac, String jogandoDe, String jogandoContra) {
        super(tictac, jogandoDe, jogandoContra);
        botLevel = "medium";
    }

    public Coordinate pickCoordinates() {
        if (whoCanWin(me)) {
            System.out.println("Making move level \"medium\"");
            return whoWinnerCoordinate(me);
        } else if (whoCanWin(enemy)) {
            System.out.println("Making move level \"medium\"");
            return whoWinnerCoordinate(enemy);
        } else {
            return super.pickCoordinates();
        }
    }

    private boolean whoCanWin(String who) {
        return checadorDeCoordenadas.whoCoordenadaVencedoraLinha(who) != null ||
                checadorDeCoordenadas.whoCoordenadaVencedoraColuna(who) != null ||
                checadorDeCoordenadas.whoCoordenadaVencedoraDiag(who) != null;
    }

    private Coordinate whoWinnerCoordinate(String who) {
        if (checadorDeCoordenadas.whoCoordenadaVencedoraLinha(who) != null) {
            return checadorDeCoordenadas.whoCoordenadaVencedoraLinha(who);
        } else if (checadorDeCoordenadas.whoCoordenadaVencedoraColuna(who) != null) {
            return checadorDeCoordenadas.whoCoordenadaVencedoraColuna(who);
        } else if (checadorDeCoordenadas.whoCoordenadaVencedoraDiag(who) != null) {
            return checadorDeCoordenadas.whoCoordenadaVencedoraDiag(who);
        }

        return null;
    }

}
