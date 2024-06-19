package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main (String[] args){

        Scanner in = new Scanner(System.in);

        // Instanciando uma nova partida
        ChessMatch chessMatch = new ChessMatch();
        while (true) {
            try {
                // limpando a tela
                UI.clearScreen();
                // exibindo o tabuleiro
                UI.printBoard(chessMatch.getPieces());
                System.out.print("\nSource pos: ");
                ChessPosition source = UI.readChessPosition(in);

                System.out.print("\nTarget pos: ");
                ChessPosition target = UI.readChessPosition(in);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
            }
            // caso levante uma exceção
            catch (ChessException e){
                // exbição de mensagem de exceção
                System.out.print(e.getMessage());
                in.nextLine();
                in.nextLine();
            }
            catch (InputMismatchException e){
                // exibição de mensagem da exceção
                System.out.print(e.getMessage());
                // aguardando pressionar enter
                in.nextLine();
                in.nextLine();
            }
        }
    }

}
