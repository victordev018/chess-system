package chess;      // xadrez

import boardgame.Board;
import boardgame.Piece;

// classe referente a peça de xadrez
public abstract class ChessPiece extends Piece {

    // atributos
    private Color color;    // cor da peça

    // construtor
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    // get
    public Color getColor(){
        return color;
    }
}
