package chess.pieces;       // peças de xadrez

import boardgame.Board;
import boardgame.Position;
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

        // variável auxiliar para gaurdar uma posição
        Position p = new Position(0, 0);

        // above    // acima
        p.setValue(position.getRow() - 1, position.getColumn());

        // lógica de verificar possiveis posições para a mover a torre
        // enquanto a posição existir e não existir uma peça nesta posição
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            // adiciona na matriz booleana a posição como true
            mat[p.getRow()][p.getColumn()] = true;
            // decrementa a linha da posição
            p.setRow(p.getRow() - 1);
        }
        // verificando se a peça encontrado na posição existee se é uma peça de um opnente
        if (getBoard().positionExist(p) && isThereOpponentPiece(p)){
            // adicionando posição como true na matriz
            mat[p.getRow()][p.getColumn()] = true;
        }

        // left    // esquerda
        p.setValue(position.getRow() , position.getColumn() - 1);

        // lógica de verificar possiveis posições para a mover a torre
        // enquanto a posição existir e não existir uma peça nesta posição
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            // adiciona na matriz booleana a posição como true
            mat[p.getRow()][p.getColumn()] = true;
            // decrementa a linha da posição
            p.setColumn(p.getColumn() - 1);
        }
        // verificando se a peça encontrado na posição existee se é uma peça de um opnente
        if (getBoard().positionExist(p) && isThereOpponentPiece(p)){
            // adicionando posição como true na matriz
            mat[p.getRow()][p.getColumn()] = true;
        }

        // right    // direita
        p.setValue(position.getRow() , position.getColumn() + 1);

        // lógica de verificar possiveis posições para a mover a torre
        // enquanto a posição existir e não existir uma peça nesta posição
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            // adiciona na matriz booleana a posição como true
            mat[p.getRow()][p.getColumn()] = true;
            // decrementa a linha da posição
            p.setColumn(p.getColumn() + 1);
        }
        // verificando se a peça encontrado na posição existee se é uma peça de um opnente
        if (getBoard().positionExist(p) && isThereOpponentPiece(p)){
            // adicionando posição como true na matriz
            mat[p.getRow()][p.getColumn()] = true;
        }

        // below    // abaixo
        p.setValue(position.getRow() + 1, position.getColumn());

        // lógica de verificar possiveis posições para a mover a torre
        // enquanto a posição existir e não existir uma peça nesta posição
        while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
            // adiciona na matriz booleana a posição como true
            mat[p.getRow()][p.getColumn()] = true;
            // decrementa a linha da posição
            p.setRow(p.getRow() + 1);
        }
        // verificando se a peça encontrado na posição existee se é uma peça de um opnente
        if (getBoard().positionExist(p) && isThereOpponentPiece(p)){
            // adicionando posição como true na matriz
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
