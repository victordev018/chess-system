package chess.pieces;   // peças

import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import boardgame.Board;
import chess.Color;

// classe referente a peça Peão
public class Pawn extends ChessPiece {

    // atributos
    private ChessMatch chessMatch;  // dependência para a partida

    // construtor
    public Pawn(Board board, Color color, ChessMatch chessMatch){
        super(board, color);
        this.chessMatch = chessMatch;
    }

    // sobreposição do método toString()
    @Override
    public String toString(){
        return "P";     // indentificador da peça Peão (Pawn)
    }

    // método sobreposto das possiveis movimentações do Peão
    @Override
    public boolean[][] possibleMove() {
        // criando matriz boolena de mesma dimensão que o tabuleiro, por padrão inicia-se todos falsos
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        // variável auxiliar para gaurdar uma posição
        Position p = new Position(0, 0);

        // verificando se a peça é branca
        if (getColor() == Color.WHITE){
            // define um novo valor para a posição p
            p.setValue(position.getRow() - 1, position.getColumn());    // move 1 vez para cima na vertical
            // verificando se a posição existe e se na posição não existe uma outra peça
            if (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            // para o caso de ser a primeira jogada do peão
            p.setValue(position.getRow() - 2, position.getColumn());    // move 2 vez para cima na vertical
            // criando posição anterior a p, para checar se a primeira posição também está livre
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            // verificando se a posição existe e se na posição não existe uma outra peça para as duas posições a sua
            // frente e se é o primeiro movimento
            if (getBoard().positionExist(p) &&
                    !getBoard().thereIsAPiece(p) &&
                    getBoard().positionExist(p2) &&
                    !getBoard().thereIsAPiece(p2) &&
                    getMoveCount() == 0)
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // para o peão poder se mover 1 vez nas diagonais a sua frente
            // define um novo valor para a posição p
            p.setValue(position.getRow() - 1, position.getColumn() - 1);    // move 1 vez para diagonal esquerda
            // verificando se a posição existe e se na posição existe uma outra peça
            if (getBoard().positionExist(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValue(position.getRow() - 1, position.getColumn() + 1);    // move 1 vez para diagonal direita
            // verificando se a posição existe e se na posição existe uma outra peça
            if (getBoard().positionExist(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            // #specialmove en passant White
            // verificando se a peça esta na linha 3
            if (position.getRow() == 3){
                // pegando posição da peça a esquerda do nosso peão
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                // verificação se a posição left existe, se se é uma peça openente e se é uma peça vulnerável ao movimento
                if (getBoard().positionExist(left) &&
                   isThereOpponentPiece(left) &&
                   getBoard().piece(left) == chessMatch.getEnPassantVulnerable())
                {
                    // diagonal superior direita fica como um movimento possivel
                    mat[left.getRow() - 1][left.getColumn()] = true;
                }

                // pegando posição da peça a esquerda do nosso peão
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                // verificação se a posição left existe, se se é uma peça openente e se é uma peça vulnerável ao movimento
                if (getBoard().positionExist(right) &&
                        isThereOpponentPiece(right) &&
                        getBoard().piece(right) == chessMatch.getEnPassantVulnerable())
                {
                    // diagonal superior direita fica como um movimento possivel
                    mat[right.getRow() - 1][right.getColumn()] = true;
                }
            }

        }
        // caso a peça seja preta
        else{
            // define um novo valor para a posição p
            p.setValue(position.getRow() + 1, position.getColumn());    // move 1 vez para vaixo na vertical
            // verificando se a posição existe e se na posição não existe uma outra peça
            if (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            // para o caso de ser a primeira jogada do peão
            p.setValue(position.getRow() + 2, position.getColumn());    // move 2 vez para baixo na vertical
            // criando posição anterior a p, para checar se a primeira posição também está livre
            Position p2 = new Position(position.getRow() + 1, position.getColumn());
            // verificando se a posição existe e se na posição não existe uma outra peça para as duas posições a sua
            // frente e se é o primeiro movimento
            if (getBoard().positionExist(p) &&
                    !getBoard().thereIsAPiece(p) &&
                    getBoard().positionExist(p2) &&
                    !getBoard().thereIsAPiece(p2) &&
                    getMoveCount() == 0)
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
            // para o peão poder se mover 1 vez nas diagonais a sua frente
            // define um novo valor para a posição p
            p.setValue(position.getRow() + 1, position.getColumn() - 1);    // move 1 vez para diagonal esquerda
            // verificando se a posição existe e se na posição existe uma outra peça
            if (getBoard().positionExist(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValue(position.getRow() + 1, position.getColumn() + 1);    // move 1 vez para diagonal direita
            // verificando se a posição existe e se na posição existe uma outra peça
            if (getBoard().positionExist(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            // #specialmove en passant Black
            // verificando se a peça esta na linha 3
            if (position.getRow() == 4){
                // pegando posição da peça a esquerda do nosso peão
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                // verificação se a posição left existe, se se é uma peça openente e se é uma peça vulnerável ao movimento
                if (getBoard().positionExist(left) &&
                        isThereOpponentPiece(left) &&
                        getBoard().piece(left) == chessMatch.getEnPassantVulnerable())
                {
                    // diagonal superior direita fica como um movimento possivel
                    mat[left.getRow() + 1][left.getColumn()] = true;
                }

                // pegando posição da peça a esquerda do nosso peão
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                // verificação se a posição left existe, se se é uma peça openente e se é uma peça vulnerável ao movimento
                if (getBoard().positionExist(right) &&
                        isThereOpponentPiece(right) &&
                        getBoard().piece(right) == chessMatch.getEnPassantVulnerable())
                {
                    // diagonal superior direita fica como um movimento possivel
                    mat[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }

        return mat;
    }
}
