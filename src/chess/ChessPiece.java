package chess;      // xadrez

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

// classe referente a peça de xadrez
public abstract class ChessPiece extends Piece {

    // atributos
    private Color color;    // cor da peça
    private int moveCount;  // contador de movimentos

    // construtor
    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    // get
    public Color getColor(){
        return color;
    }

    public int getMoveCount(){
        return moveCount;
    }

    // método para incrementar o o contador de movimento
    public void increaseMoveCount(){
        this.moveCount++;
    }

    // método para decrementar o contador de movimento
    public void decreaseMoveCount(){
        this.moveCount--;
    }

    // método que retorna a posição da peça como uma posição de xadrez (ChessPosition)
    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);
    }

    // método para verificar se existe um opnente em uma determinada posição
    protected boolean isThereOpponentPiece(Position position){
        // gaurando a peça que existe na posição informada em uma variável
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        // vereificação se a peça da posição existe e se é uma peça oponente
        return p != null && p.getColor() != color;
    }
}
