package boardgame;      // jogo de tabuleiro

// classe referente ao tabuleiro
public class Board {

    // atributos
    private Integer rows;       // quantidade de linhas
    private Integer columns;    // quantidade de colunas
    private Piece[][] pieces;   // matriz de peças

    // construtor
    public Board(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];  // define a matriz com a quantidade de linhas e colunas
    }

    // get e set
    public Integer getRows() {
        return rows;
    }
    public void setRows(Integer rows) {
        this.rows = rows;
    }
    public Integer getColumns() {
        return columns;
    }
    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    // método que retorna o objeto Piece em que se encontra em uma linha e coluna passadas como argumento
    public Piece piece(int row, int column){
        return pieces[row][column];
    }

    // sobrecarga: retorna o objeto Piece baseado em uma Position passada como argumento
    public Piece piece(Position position){
        return pieces[position.getRow()][position.getColumn()];
    }
}
