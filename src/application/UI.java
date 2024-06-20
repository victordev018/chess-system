package application;    // aplicação

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    // método para limpar tela do terminal
    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // método que faz a leitura da coordenada e retorna um ChessPosition
    public static ChessPosition readChessPosition(Scanner in){
        try {
            String s = in.next();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        }
        // caso haja um problema na leitura
        catch (RuntimeException e){
            throw new InputMismatchException("Error reading ChessPosition. valid value are from a1 to h8.");
        }
    }

    // método para fazer a impressão do tabuleiro
    public static void printBoard(ChessPiece[][] pieces){
        // para cada linha
        for (int r = 0; r < pieces.length; r++){
            System.out.print((8-r) + " ");  // imprime o número de cada linha
            for (int c = 0; c < pieces.length; c++){
                printPiece(pieces[r][c], false);    // não pode imprimir a cor do background
            }
            // quando terminar de imprimir a linha taual, quebra a linha
            System.out.println();
        }
        // quando terminar todas as linhas
        System.out.print("  a b c d e f g h");
    }

    // método para imprimir a partida
    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured){
        printBoard(chessMatch.getPieces());     // imprime o tabuleiro
        printCapturedPiece(captured);           // imprime as peças capturadas
        System.out.println("\nTurn: " + chessMatch.getTurn());        // imprime turno
        // verificando se a partida não esta em cheque mate
        if (!chessMatch.getCheckMate()){
            System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());     // imprime player atual

            // verificando se a partida está em cheque
            if (chessMatch.getCheck()){
                System.out.println("\nCHECK");
            }
        }
        else{   // se estiver em check mate
            System.out.println("CHECKMATE");
            System.out.println("Winner: " + chessMatch.getCurrentPlayer());
        }
    }

    // sobrecarga do método para fazer a impressão do tabuleiro, agora imprimindo posições possiveis
    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves){
        // para cada linha
        for (int r = 0; r < pieces.length; r++){
            System.out.print((8-r) + " ");  // imprime o número de cada linha
            for (int c = 0; c < pieces.length; c++){
                printPiece(pieces[r][c], possibleMoves[r][c]);     // pode imprimir a cor do background, se for true
            }
            // quando terminar de imprimir a linha taual, quebra a linha
            System.out.println();
        }
        // quando terminar todas as linhas
        System.out.print("  a b c d e f g h");
    }

    // método para fazer a impressão de uma única peça
    public static void printPiece(ChessPiece piece, boolean background){
        // verificando se pode pintar o fundo da peça
        if (background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        // se não existir peça
        if (piece == null){
            System.out.print("-" + ANSI_RESET);
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

    // método para imprimir as peças capturadas
    private static void printCapturedPiece(List<ChessPiece> captured){
        // filtrando a peças brancas em uma lista
        List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
        // filtrando as peças pretas em uma lista
        List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());

        // exibindo peças cappturadas
        System.out.println("\n\nCaptured pieces:");
        // peças brancas
        System.out.print("White: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);

        // peças pretas
        System.out.print("Black: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }

}
