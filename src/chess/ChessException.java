package chess;      // xadrez

import boardgame.BoardException;

// classe referente a exceção de xadrez
public class ChessException extends BoardException {
    // definindo o valor do serialVersionUID
    private static final long serialVersionUID = 1L;

    // construtor
    public ChessException(String message){
        super(message);
    }
}
