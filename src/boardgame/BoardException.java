package boardgame;

public class BoardException extends RuntimeException {
    // definindo o valor da serialVersionUID
    private static final long serialVersionUID = 1L;

    // construtor da exceção
    public BoardException (String message){
        super(message);
    }
}
