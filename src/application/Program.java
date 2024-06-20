package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main (String[] args){

        Scanner in = new Scanner(System.in);
        // lista de peças capturadas
        List<ChessPiece> captured = new ArrayList<ChessPiece>();
        // Instanciando uma nova partida
        ChessMatch chessMatch = new ChessMatch();
        while (true) {
            try {
                // limpando a tela
                UI.clearScreen();
                // exibindo o tabuleiro
                UI.printMatch(chessMatch, captured);
                System.out.print("\nSource pos: ");
                ChessPosition source = UI.readChessPosition(in);

                // matriz de possiveis movimentações para esta peça
                boolean[][] possibleMoves = chessMatch.possibleMover(source);
                // limpando tela
                UI.clearScreen();
                // imprimindo o tabuleiro, porém colorindo as posições possíveis
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.print("\n\n" +
                        "Target pos: ");
                ChessPosition target = UI.readChessPosition(in);
                // guarda possivel peça capturada
                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                // verificando se alguma peça foi de fato capturada
                if (capturedPiece != null) {
                    // adiciona a peça na lista das peças capturadas
                    captured.add(capturedPiece);
                }
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
