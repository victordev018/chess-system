package application;    // aplicação

import chess.ChessPiece;
import chess.Color;

// classe responsável pela interface de usuário
public class UI {
    // fonte
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    // cores para texto
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // cores para o fundo
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

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
            // aplicando cores de acordo com a cor da peça
            if (piece.getColor() == Color.WHITE){
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else{
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        // espaçamento entre peças
        System.out.print(" ");
    }

}
