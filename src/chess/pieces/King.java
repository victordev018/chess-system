package chess.pieces;       // peças de xadrez

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

// classe referente a peça Rei
public class King extends ChessPiece {

    // construtor
    public King(Board board, Color color) {
        super(board, color);
    }

    // sobreposição do método toString()
    @Override
    public String toString(){
        return "K";     // indentificador da peça Rei (king)
    }

    // implementação da movientação da peça do Rei
    @Override
    public boolean[][] possibleMove() {
        // criando matriz boolena de mesma dimensão que o tabuleiro, por padrão inicia-se todos falsos
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat;
    }
}
