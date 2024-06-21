package chess.pieces;       // peças de xadrez

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

// classe referente a peça Cavalo
public class Knight extends ChessPiece {

    // construtor
    public Knight(Board board, Color color) {
        super(board, color);
    }

    // sobreposição do método toString()
    @Override
    public String toString(){
        return "N";     // indentificador da peça Cavalo (Knight)
    }

    // método para verificar se o cavalo pode se mover
    private boolean canMove(Position position){
        // referência a Peça na posição dada
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        // se a posição estiver vazia ou for uma peça oponente, então pode se mover
        return p == null || p.getColor() != getColor();
    }


    // implementação da movientação da peça do Cavalo
    @Override
    public boolean[][] possibleMove() {
        // criando matriz boolena de mesma dimensão que o tabuleiro, por padrão inicia-se todos falsos
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        // variável auxiliar para gaurdar uma posição
        Position p = new Position(0, 0);

        p.setValue(position.getRow() - 1, position.getColumn() - 2);
        // verificando se a posição existe e se o cavalo pode se mover
        if (getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValue(position.getRow() - 2, position.getColumn() - 1);
        // verificando se a posição existe e se o rei pode se mover
        if (getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValue(position.getRow() - 2, position.getColumn() + 1);
        // verificando se a posição existe e se o cavalo pode se mover
        p.setValue(position.getRow() - 1, position.getColumn() - 2);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValue(position.getRow() - 2, position.getColumn() - 1);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValue(position.getRow() - 2, position.getColumn() + 1);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValue(position.getRow() - 1, position.getColumn() + 2);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValue(position.getRow() + 1, position.getColumn() + 2);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValue(position.getRow() + 2, position.getColumn() + 1);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValue(position.getRow() + 2, position.getColumn() - 1);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        p.setValue(position.getRow() + 1, position.getColumn() - 2);
        if (getBoard().positionExist(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
