package chess;      // xadrez

import boardgame.Position;

// classe referente ao sistema de posições do xadrez (a1 até h8)
public class ChessPosition {

    // atributos
    private char column;    // coluna
    private int row;        // linha

    // construtor
    public ChessPosition(char column, int row) {
        // verificando se a as coordenadas estão fora do intevrlao permitido
        if (column < 'a' || column > 'h' || row < 1 || row > 8) {
            // lançará uma exceção
            throw new ChessException("Error instatiating ChessPosition. Valid values are from a1 to h8");
        }
        this.column = column;
        this.row = row;
    }

    // get e set

    public char getColumn() {
        return column;
    }
    public int getRow() {
        return row;
    }

    // método que converte a posição chessPosition para position
    protected Position toPosition (){
        return new Position(8 - row, column - 'a');
    }

    // método que retorna o retorna o a uma posição de xadrez(a1-h8) baseado em uma posição do tabuleiro(matriz)
    public static ChessPosition fromPosition (Position position){
        return new ChessPosition((char)(position.getColumn() + 'a'), 8 - position.getRow());
    }

    // sobreposição do método toString()
    @Override
    public String toString(){
        return "" + column + row;
    }
}
