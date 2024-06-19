package chess;      // xadrez

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

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

    // método para verificar se existe um opnente em uma determinada posição
    protected boolean isThereOpponentPiece(Position position){
        // gaurando a peça que existe na posição informada em uma variável
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        // vereificação se a peça da posição existe e se é uma peça oponente
        return p != null && p.getColor() != color;
    }
}
