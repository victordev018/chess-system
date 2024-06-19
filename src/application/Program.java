package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Scanner;

public class Program {

    public static void main (String[] args){

        Scanner in = new Scanner(System.in);

        // Instanciando uma nova partida
        ChessMatch chessMatch = new ChessMatch();
        while (true){
            // exibindo o tabuleiro
            UI.printBoard(chessMatch.getPieces());
            System.out.print("\nSource pos: ");
            ChessPosition source  = UI.readChessPosition(in);

            System.out.print("\nTarget pos: ");
            ChessPosition target = UI.readChessPosition(in);

            ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
        }

    }

}
