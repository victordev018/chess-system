package chess.pieces;       // peças de xadrez

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

// classe referente a peça Torre
public class Rook extends ChessPiece {

    // construtor
    public Rook(Board board, Color color){
        super(board, color);
    }

    // sobreposição do método toSTring()
    @Override
    public String toString(){
        return "R";     // indentificador da peça Torre (Rook)
    }

    // implementação da movimentação da peça da Torre
    @Override
    public boolean[][] possibleMove() {
        // criando matriz boolena de mesma dimensão que o tabuleiro, por padrão inicia-se todos falsos
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat;
    }
}
