package boardgame;      // jogo de tabuleiro

// classe referente a peça
public abstract class Piece {

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

    // método abstrato que retorna uma matriz de booleanos, representando os movimentos possíveis
    public abstract boolean[][] possibleMove();

    // método para verificar se a peça pode se mover para uma determinada posição
    public boolean possibleMove(Position position){
        // na matriz de movimentos possiveis, verifique se essa posição é uma delas
        return possibleMove()[position.getRow()][position.getColumn()];
    }

    // método que verifica se existe pelo menos um movimento possivel para a peça
    public boolean IsThereAnyPossibleMove(){
        boolean[][] mat = possibleMove();
        for (int r = 0; r < mat.length; r++){    // para cada linha
            for (int c = 0; c < mat.length; c++){
                if (mat[r][c]){
                    return true;
                }
            }
        }
        return false;
    }

}
