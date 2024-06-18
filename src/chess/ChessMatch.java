package chess;      // xadrez

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

// classe referente a Partida de xadrez
public class ChessMatch {

    // atributos
    private Board board;    // tabuleiro

    // construtor
    public ChessMatch(){
        // a partida sabe a dimensão do tabuleiro
        board = new Board(8, 8);
        initialSetup();
    }

    // método para retornar uma matriz de CheesPiece (Peças de xadrez)
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

        // atribuindo as peças para a matriz de peça de xadrez
        for (int row = 0; row < board.getRows(); row++){   // para cada linha
            for (int column = 0; column < board.getColumns(); column++){    // para cada coluna
                mat[row][column] = (ChessPiece) board.piece(row, column);   // upcasting para ChessPiece
            }
        }
        return mat;
    }

    // método para fazer a configuração inicial do tebauleiro
    private void initialSetup(){
        board.placePiece(new King(board, Color.BLACK), new Position(0, 5));
        board.placePiece(new Rook(board, Color.WHITE), new Position(7, 2));
    }

}
