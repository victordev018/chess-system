package application;    // aplicação

import chess.ChessPiece;

// classe responsável pela interface de usuário
public class UI {

    // método para fazer a impressão do tabuleiro
    public static void printBoard(ChessPiece[][] pieces){
        // para cada linha
        for (int r = 0; r < pieces.length; r++){
            System.out.print((8-r) + " ");  // imprime o número de cada linha
            for (int c = 0; c < pieces.length; c++){
                printPiece(pieces[r][c]);
            }
            // quando terminar de imprimir a linha taual, quebra a linha
            System.out.println();
        }
        // quando terminar todas as linhas
        System.out.print("  a b c d e f g h");
    }

    // método para fazer a impressão de uma única peça
    public static void printPiece(ChessPiece piece){
        // se não existir peça
        if (piece == null){
            System.out.print("-");
        }
        // se eixistir a peça
        else{
            System.out.print(piece);
        }
        // espaçamento entre peças
        System.out.print(" ");
    }

}
