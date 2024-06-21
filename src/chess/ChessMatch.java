package chess;      // xadrez

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// classe referente a Partida de xadrez
public class ChessMatch {

    // atributos
    private int turn;       // vez
    private Color currentPlayer;    // jogador atual
    private Board board;    // tabuleiro
    private boolean check;  // verifica se uma peça está em posição de check
    private boolean checkMate;    // verifica se a peça está em posição de checkMate
    private ChessPiece enPassantVulnerable;     // peça sujeita ao movimento enPassant
    private ChessPiece promoted;        // peça suejeita  a condição de promoção (trocar a peça)

    // lista de peças no tabuleiro
    List<Piece> piecesOnTheBoard = new ArrayList<Piece>();
    // lista de peças capturadas
    List<Piece> capturedPieces = new ArrayList<Piece>();


    // construtor
    public ChessMatch(){
        // a partida sabe a dimensão do tabuleiro
        board = new Board(8, 8);
        this.turn = 1;
        this.currentPlayer = Color.WHITE;
        initialSetup();
    }

    // geters
    public boolean getCheckMate(){
        return checkMate;
    }

    public boolean getCheck(){
        return check;
    }

    public int getTurn(){
        return turn;
    }

    public Color getCurrentPlayer(){
        return currentPlayer;
    }

    public ChessPiece getEnPassantVulnerable(){
        return enPassantVulnerable;
    }

    public ChessPiece getPromoted(){
        return promoted;
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
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();    // posição de origem
        Position target = targetPosition.toPosition();  // posição destino
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        // pegando a peça que foi movida
        ChessPiece movedPiece = (ChessPiece) board.piece(target);

        // verificando se a jogada feita colocou o player atual em check
        if (testCheck(currentPlayer)){
            // desfaz a jogada
            undoMove(source, target, capturedPiece);
            // lnaça uma exceção
            // você não pode se colocar em cheque
            throw new ChessException("you can't put yourself in check");
        }

        // #specialmove Promotion
        promoted = null;
        // verificando se a peça que foi movida foi um peão
        if (movedPiece instanceof Pawn){
            // verificando se a peça é uma torre e se a torre branca ou preta chegou no final
            if (movedPiece.getColor() == Color.WHITE && target.getRow() == 0 ||
                movedPiece.getColor() == Color.BLACK && target.getRow() == 7) {
                // definndo quem foi a peça promovida
                promoted = (ChessPiece)board.piece(target);
                // trocando o pião por outra peça
                promoted = replacePromotedPiece("Q");
            }
        }

        // testando se o opnente e ficou em chque
        check = testCheck(opponent(currentPlayer))? true: false;

        // verificando se a jogada do player atual deixou o oponente em cheque mate
        if (testCheckMate(opponent(currentPlayer))){
            checkMate = true;
        }
        else{
            nextTurn(); // trocando o a vez
        }

        // verificando se a peça movida foi um peão e se foi o primeiro movimento dele
        if (movedPiece instanceof Pawn &&
           (target.getRow() == source.getRow() + 2) ||
           (target.getRow() == source.getRow() - 2)){
            // então esse peão ficou vulnerável a tomar o enPassant
            enPassantVulnerable = movedPiece;
        }
        else{
            // caso a peça não seja vulnerável
            enPassantVulnerable = null;
        }

        return (ChessPiece) capturedPiece;
    }

    // método para fazer a troca de carta na jogada de promoção
    public ChessPiece replacePromotedPiece(String type){
//        // se não tiver peça promovida
//        if (promoted == null){
//            throw new IllegalStateException("There is no piece to be promoted");
//        }
//        // se o type não corresponder a nenhum dos tipos possiveis
//        if (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")){
//            throw new InvalidParameterException("Invalid type for promotion");
//        }
//
//        // pegando posição da peça promovida
//        Position pos = promoted.getChessPosition().toPosition();
//        // removendo peça que etá na posição pos e guardando em p
//        Piece p = board.piece(pos);
//        // excluindo e peça p da lista de peças no tauleiro
//        piecesOnTheBoard.remove(p);
//        // instanciando a nova peça
//        ChessPiece newPiece = newPiece(type, promoted.getColor());
//        // colocando a nova peça na posição da peça promovida
//        board.placePiece(newPiece, pos);
//        // adicionando essa nova peça na lista de peças no tabuleiro
//        piecesOnTheBoard.add(newPiece);
//        return newPiece;
        if (promoted == null) {
            throw new IllegalStateException("There is no piece to be promoted");
        }
        if (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
            return promoted;
        }

        Position pos = promoted.getChessPosition().toPosition();
        Piece p = board.removePiece(pos);
        piecesOnTheBoard.remove(p);

        ChessPiece newPiece = newPiece(type, promoted.getColor());
        board.placePiece(newPiece, pos);
        piecesOnTheBoard.add(newPiece);

        return newPiece;
    }

    // método auxiliar para instanciar uma nova peça de acordo com a letra passada
    private ChessPiece newPiece(String type, Color color){
        if (type.equals("B")) return new Bishop(board, color);
        if (type.equals("N")) return new Knight(board, color);
        if (type.equals("R")) return new Rook(board, color);
        return new Queen(board, color);
    }

    // método para fazer mover a peça de uma origem para um destino
    private Piece makeMove(Position source, Position target){
        // removendo a peça na posição de origem e gaurdando na variável p
        ChessPiece p = (ChessPiece) board.removePiece(source);
        // incrementando a quantidade de movimentos
        p.increaseMoveCount();
        // removendo possivel peça que se encontra na posição de destino, e guardando-a em capturedPiece
        Piece capturedPiece = board.removePiece(target);
        // colocando peça na posição de destino
        board.placePiece(p, target);
        // verificando se houve peça capturada
        if (capturedPiece != null){
            // remove da lista de peças no tabuleiro
            piecesOnTheBoard.remove(capturedPiece);
            // e adiciona a peça na lista de peças capturadas
            capturedPieces.add(capturedPiece);
        }

        // #specialmove castling, torre da direita
        // se a peça movida for o rei e se a quantidade de casas andadas forem 2
        if (p instanceof King && target.getColumn() == source.getColumn() + 2){
            // pegando a peça que está do lado do rei agora, no caso a torre
            Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
            Position targetT = new Position(source.getRow(), source.getColumn() + 1);
            // retirando a torre da posição de origem dela
            ChessPiece rook = (ChessPiece) board.removePiece(sourceT);
            // adicionando torre na nova posição de destino
            board.placePiece(rook, targetT);
            // incremementando a quantidade de movimento da torre
            rook.increaseMoveCount();
        }

        // #specialmove castling, torre da esquerda
        // se a peça movida for o rei e se a quantidade de casas andadas forem 2
        if (p instanceof King && target.getColumn() == source.getColumn() - 2){
            // pegando a peça que está do lado do rei agora, no caso a torre
            Position sourceT = new Position(source.getRow(), source.getColumn() - 4);
            Position targetT = new Position(source.getRow(), source.getColumn() - 1);
            // retirando a torre da posição de origem dela
            ChessPiece rook = (ChessPiece) board.removePiece(sourceT);
            // adicionando torre na nova posição de destino
            board.placePiece(rook, targetT);
            // incremementando a quantidade de movimento da torre
            rook.increaseMoveCount();
        }

        // #specialmove en passant
        // se a peça que foi movida é uma instancia de um peão
        if (p instanceof Pawn){
            // se o peão andou na diagonal e não teve peça capturada, foi um movimento de en passant
            if (source.getColumn() != target.getColumn() && capturedPiece == null){
                // instanciando posição do Peão
                Position pawnPosition;
                // descobrindo se o movimento foi pra direita ou esuqerda, baseado na cor da peça
                if (p.getColor() == Color.WHITE){
                    // a peça a ser capturada está em baixo do peão
                    pawnPosition = new Position(target.getRow() + 1, target.getColumn());
                }
                else{
                    pawnPosition = new Position(target.getRow() - 1, target.getColumn());
                }
                // removendo o pião do tabuleiro
                capturedPiece = board.removePiece(pawnPosition);
                // adicionando na lista das peças capturada
                capturedPieces.add(capturedPiece);
                // removendo a peça capturada da lista das peças no tabuleiro
                piecesOnTheBoard.remove(capturedPiece);
            }
        }

        // retornando possivel peça capturada
        return  capturedPiece;
    }

    // método para desfazer um movimento
    private void undoMove(Position source, Position target, Piece capturedPiece){
        // removendo a peça da posição destino
        ChessPiece p = (ChessPiece) board.removePiece(target);
        // decrementando o contador de movimentos
        p.decreaseMoveCount();
        // colocando p na posição de origem
        board.placePiece(p, source);
        // removendo a possivel peça capturada da lista dos capturados
        if (capturedPiece != null){
            capturedPieces.remove(capturedPiece);
            // coloca ele na sua posição de origem, no caso a target
            board.placePiece(capturedPiece, target);
            // adicionando a peça capturada a lista de peças no tabuleiro
            piecesOnTheBoard.add(capturedPiece);
        }

        // #specialmove castling, torre da direita
        // se a peça movida for o rei e se a quantidade de casas andadas forem 2
        if (p instanceof King && target.getColumn() == source.getColumn() + 2){
            // pegando a peça que está do lado do rei agora, no caso a torre
            Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
            Position targetT = new Position(source.getRow(), source.getColumn() + 1);
            // retirando a torre da posição de destino dela
            ChessPiece rook = (ChessPiece) board.removePiece(targetT);
            // adicionando torre na nova posição de origem
            board.placePiece(rook, sourceT);
            // decremementando a quantidade de movimento da torre
            rook.decreaseMoveCount();
        }

        // #specialmove castling, torre da esquerda
        // se a peça movida for o rei e se a quantidade de casas andadas forem 2
        if (p instanceof King && target.getColumn() == source.getColumn() - 2){
            // pegando a peça que está do lado do rei agora, no caso a torre
            Position sourceT = new Position(source.getRow(), source.getColumn() - 4);
            Position targetT = new Position(source.getRow(), source.getColumn() - 1);
            // retirando a torre da posição de destino dela
            ChessPiece rook = (ChessPiece) board.removePiece(targetT);
            // adicionando torre na nova posição de origem
            board.placePiece(rook, sourceT);
            // decremementando a quantidade de movimento da torre
            rook.decreaseMoveCount();
        }

        // #specialmove en passant
        // se a peça que foi movida é uma instancia de um peão
        if (p instanceof Pawn){
            // se o peão andou na diagonal e a peça capturada foi uma peça vulnerável ao movimento
            if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable){
                // a peça naturalmente volta para a posição de destino, que nesse caso é a posição errada
                // então removemos a peça da posição target e obtemos referência  a ela
                ChessPiece pawn = (ChessPiece)board.removePiece(target);
                // instanciando posição do Peão
                Position pawnPosition;
                // descobrindo se o movimento foi pra direita ou esuqerda, baseado na cor da peça
                if (p.getColor() == Color.WHITE){
                    // devolve a peça preta para a sua posição
                    pawnPosition = new Position(3, target.getColumn());
                }
                else{
                    // devolve a peça branca para sua posição
                    pawnPosition = new Position(4, target.getColumn());
                }
                // colocando a peça pawn no seu verdadeiro lugar de origem
                board.placePiece(pawn, pawnPosition);
            }
        }
    }

    // método para validar a posição de origem
    private void validateSourcePosition(Position source){
        // verificando se não existe uma peça nessa posição
        if (!board.thereIsAPiece(source)){
            throw new ChessException("There is not a piece on source position");
        }
        // verificando se a peça selecionada tem cor diferente da do player atual
        if (currentPlayer != ((ChessPiece)board.piece(source)).getColor()){
            // lançara uma exceção
            // a peça escolhida não é sua
            throw new ChessException("The chosen piece is not yours");
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

    // método para fazer a troca de vez de jogador
    private void nextTurn(){
        // incrementa o turn
        turn++;
        // troca o currentPlayer
        // se o player atual for branco, atribua preto, senão atribua branco
        currentPlayer = (currentPlayer == Color.WHITE)? Color.BLACK: Color.WHITE;
    }

    // método que informa o opnente
    private Color opponent (Color color){
        // se a cor for branca, retorne preto e se for preto retorne branca
        return (color == Color.WHITE)? Color.BLACK: Color.WHITE;
    }

    // mérodo para localizar a posição de um Rei de uma determinada cor
    private ChessPiece king(Color color){
        // lista com todas as peças que possuem cor dada
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        // varrendo nova lista de peças da cor passada como argumento
        for (Piece p: list){
            // se a peça for uma instancia da classe King
            if (p instanceof King){
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no " + color + " piece on the board");
    }

    // método para verificar se uma peça esta em check baseada em sua cor
    private boolean testCheck(Color color){
        // pegando a posição do rei no formato de matriz
        Position kingPosition = king(color).getChessPosition().toPosition();
        // lista das peças opnentes do Rei
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());

        // checacando cada uma das peças, e verificando se exite um movimento possível que leve-a até o king, deixando-o em check
        for (Piece p: opponentPieces){
            // recebendo matriz de possiveis movimentos da peça atual
            boolean[][] mat = p.possibleMove();
            // se a posição do king for um dos movimentos possiveis, ou seja, ser true
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]){
                // então o uma das possiveis posições compromete o rei
                return true;
            }
        }
        return false;
    }

    // método que checa se o rei está em cheque mate
    private boolean testCheckMate(Color color){
        // testando se essa cor não está em cheque
        if (!testCheck(color)){
            return false;
        }
        // lista de todas as peças da cor da peça que está em cheque
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        // percorrer lista e verificar para cada peça se pelo menos uma dela tem um movimento que tire o rei de cheque
        for (Piece p: list){
            // pega matriz de movimentos possiveis para a peça atual
            boolean[][] mat = p.possibleMove();
            // verificar cada um dos possiveis movimentos
            for (int r = 0; r < board.getRows(); r++){
                for (int c = 0; c < board.getColumns(); c++){
                    // Verificar se a posição atual é uma posição possivel, ou seja, true
                    if (mat[r][c]) {
                        // realizar um movimento de teste, para verificar se o movimento tira o rei de check
                        Position source = ((ChessPiece) p).getChessPosition().toPosition();  // origem é onde a peça está
                        Position target = new Position(r, c);   // posição atual da matriz de movimentos possiveis
                        Piece capturedPiece = makeMove(source, target);
                        // feito isso testamos se o a peça da desta cor ainda esta em cheque
                        boolean testCheck = testCheck(color);
                        // defaz o último movimento de teste
                        undoMove(source, target, capturedPiece);
                        // verifica não esta mais em cheque
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // método para colocar uma nova peça no xadrez baseado em uma posição de xadrez (a1-h8)
    public void placeNewPiece(char column, int row, ChessPiece piece){
        // adicionando nova peça no tabuleiro
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        // aidcionando peça na lista de peças no tabuleiro
        piecesOnTheBoard.add(piece);
    }

    // método para fazer a configuração inicial do tebauleiro
    private void initialSetup(){
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE, this));  // this = autoreferência a classe
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK, this));  // this = autoreferência a classe
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
    }

}
