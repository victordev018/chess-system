package chess.pieces;       // peças de xadrez

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

// classe referente a peça Rei
public class King extends ChessPiece {

    // atributos
    private ChessMatch chessMatch;

    // construtor
    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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

    // método que testa se a torre está apta para uma jogada especial (Roque)
    private boolean testRookCastling(Position position){
        // pegando a peça que está nessa posição informada
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        // verificando se a peça existe e se é uma Torre e se a cor da torre é igual a cor do rei e se tem 0 movimentos
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;

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

        // #specialmove castling
        // se o rei estiver com 0 jogadas e a partida não estiver em cheque
        if (getMoveCount() == 0 && !chessMatch.getCheck()){
            // #specialmove castling, do lado do rei
            // pegando posição de onde deve estar a torre do rei
            Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
            // se a peça nesta posição esta apta para o roque
            if (testRookCastling(posT1)){
                // pegando as posições das casas entre o rei e a torre 1
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);
                // verificando se nas duas posições as peças não existem
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    // coloca a p2 como uma das posições possiveis de movimento
                    mat[p2.getRow()][p2.getColumn()] = true;
                }
            }

            // #specialmove castling, do lado do rainha
            // pegando posição de onde deve estar a torre do rei
            Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
            // se a peça nesta posição esta apta para o roque
            if (testRookCastling(posT2)){
                // pegando as posições das casas entre o rei e a torre 1
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);
                // verificando se nas três posições as peças não existem
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
                    // coloca a p2 como uma das posições possiveis de movimento
                    mat[p2.getRow()][p2.getColumn()] = true;
                }
            }
        }
        return mat;
    }
}
