package chess;      // xadrez

// classe referente a exceção de xadrez
public class ChessException extends RuntimeException {
    // definindo o valor do serialVersionUID
    private static final long serialVersionUID = 1L;

    // construtor
    public ChessException(String message){
        super(message);
    }
}
