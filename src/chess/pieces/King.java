package chess.pieces;       // peças de xadrez

import boardgame.Board;
import boardgame.Position;
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

    // método para verificar se o Rei pode se mover
    private boolean canMove(Position position){
        // referência a Peça na posição dada
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        // se a posição estiver vazia ou for uma peça oponente, então pode se mover
        return p == null || p.getColor() != getColor();
    }


    // implementação da movientação da peça do Rei
    @Override
    public boolean[][] possibleMove() {
        // criando matriz boolena de mesma dimensão que o tabuleiro, por padrão inicia-se todos falsos
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        // variável auxiliar para gaurdar uma posição
        Position p = new Position(0, 0);

        // above    // acima
        p.setValue(position.getRow() - 1, position.getColumn());
        // verificando se a posição existe e se o rei pode se mover
        if (getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // below    // abaixo
        p.setValue(position.getRow() + 1, position.getColumn());
        // verificando se a posição existe e se o rei pode se mover
        if (getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // left    // esquerda
        p.setValue(position.getRow(), position.getColumn() - 1);
        // verificando se a posição existe e se o rei pode se mover
        if (getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // right    // direita
        p.setValue(position.getRow(), position.getColumn() + 1);
        // verificando se a posição existe e se o rei pode se mover
        if (getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // nw    // noroeste
        p.setValue(position.getRow() - 1, position.getColumn() - 1);
        // verificando se a posição existe e se o rei pode se mover
        if (getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // ne    // nordeste
        p.setValue(position.getRow() - 1, position.getColumn() + 1);
        // verificando se a posição existe e se o rei pode se mover
        if (getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // sw    // sudoeste
        p.setValue(position.getRow() + 1, position.getColumn() - 1);
        // verificando se a posição existe e se o rei pode se mover
        if (getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // se    // sudeste
        p.setValue(position.getRow() + 1, position.getColumn() + 1);
        // verificando se a posição existe e se o rei pode se mover
        if (getBoard().positionExist(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
