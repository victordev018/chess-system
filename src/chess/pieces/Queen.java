package chess.pieces;       // peças de xadrez

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

// classe referente a peça Rainha
public class Queen extends ChessPiece {

    // construtor
    public Queen(Board board, Color color){
        super(board, color);
    }

    // sobreposição do método toSTring()
    @Override
    public String toString(){
        return "Q";     // indentificador da peça rainha (queen)
    }

    // implementação da movimentação da peça da rainha
    @Override
    public boolean[][] possibleMove() {
        // criando matriz boolena de mesma dimensão que o tabuleiro, por padrão inicia-se todos falsos
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        // variável auxiliar para gaurdar uma posição
        Position p = new Position(0, 0);

        // above
        p.setValue(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // left
        p.setValue(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // right
        p.setValue(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // below
        p.setValue(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // nw
        p.setValue(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValue(p.getRow() - 1, p.getColumn() - 1);
        }
        if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // ne
        p.setValue(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValue(p.getRow() - 1, p.getColumn() + 1);
        }
        if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // se
        p.setValue(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValue(p.getRow() + 1, p.getColumn() + 1);
        }
        if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // sw
        p.setValue(position.getRow() + 1, position.getColumn() - 1);
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValue(p.getRow() + 1, p.getColumn() - 1);
        }
        if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
