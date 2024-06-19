package chess;      // xadrez

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

// classe referente a Partida de xadrez
public class ChessMatch {

    // atributos
    private Board board;    // tabuleiro

    // construtor
    public ChessMatch(){
        // a partida sabe a dimensão do tabuleiro
        board = new Board(8, 8);
        initialSetup();
    }

    // método para retornar uma matriz de CheesPiece (Peças de xadrez)
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

        // atribuindo as peças para a matriz de peça de xadrez
        for (int row = 0; row < board.getRows(); row++){   // para cada linha
            for (int column = 0; column < board.getColumns(); column++){    // para cada coluna
                mat[row][column] = (ChessPiece) board.piece(row, column);   // upcasting para ChessPiece
            }
        }
        return mat;
    }

    // método que pega uma matriz de movimentos possiveis dada uma posição
    public boolean[][] possibleMover(ChessPosition sourcePosition){
        // downcast de ChessPosition para Position
        Position position = sourcePosition.toPosition();
        // validando posição de origem
        validateSourcePosition(position);
        // retorna uma matriz de possiveis movimentos para a peça selceionada
        return board.piece(position).possibleMove();
    }

    // método para executar a movimento de xadrez
    public ChessPiece performChessMove(ChessPosition sorcePosition, ChessPosition targetPosition){
        Position source = sorcePosition.toPosition();    // posição de origem
        Position target = targetPosition.toPosition();  // posição destino
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece) capturedPiece;
    }

    // método para fazer mover a peça de uma origem para um destino
    private Piece makeMove(Position source, Position target){
        // removendo a peça na posição de origem e gaurdando na variável p
        Piece p = board.removePiece(source);
        // removendo possivel peça que se encontra na posição de destino, e guardando-a em capturedPiece
        Piece capturedPiece = board.removePiece(target);
        // colocando peça na posição de destino
        board.placePiece(p, target);
        // retornando possivel peça capturada
        return  capturedPiece;
    }

    // método para validar a posição de origem
    private void validateSourcePosition(Position source){
        // verificando se não existe uma peça nessa posição
        if (!board.thereIsAPiece(source)){
            throw new ChessException("There is not a piece on source position");
        }
        // verificando se no tabuleiro, a peça na posição de origem não possue movimento possivel
        if (!board.piece(source).IsThereAnyPossibleMove()){
            // lançara uma exceção
            // não há movimento possível para a peça escolhida
            throw new ChessException("There is no possible move for the chosen piece");
        }
    }

    // método para validar a posição de destino em relação a posição de origem
    public void validateTargetPosition(Position source, Position target){
        // verificando se a peça de origem não pode ser movida para a posição de destino
        if (!board.piece(source).possibleMove(target)){
            // lançara uma exceção
            // a peça escolhida não pode se mover para a posição de destino
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    // método para colocar uma nova peça no xadrez baseado em uma posição de xadrez (a1-h8)
    public void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    // método para fazer a configuração inicial do tebauleiro
    private void initialSetup(){
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }

}
