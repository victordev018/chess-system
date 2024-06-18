package application;

import chess.ChessMatch;

public class Program {

    public static void main (String[] args){

        // Instanciando uma nova partida
        ChessMatch chessMatch = new ChessMatch();
        // exibindo o tabuleiro
        UI.printBoard(chessMatch.getPieces());

    }

}
