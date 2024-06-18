package boardgame;      // jogo de tabuleiro

// classe referente a peça
public class Piece {

    // atributos
    protected Position position;    // posição
    private Board board;            // tabuleiro

    // construtor
    public Piece(Board board){
        this.board = board;
        position = null;
    }

    // get e set
    public Board getBoard() {
        return board;
    }

}
