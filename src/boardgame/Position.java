package boardgame;      // jogo de tabuleiro

// classe que representa a posição das peças
public class Position {

    // atributos
    private Integer row;        // linha
    private Integer column;     // coluna

    // construtor
    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    // get e set
    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    // método para definir novos valores para linha e coluna
    public void setValue(int row, int column){
        this.row = row;
        this.column = column;
    }

    // sobreposição do toString
    @Override
    public String toString(){
        return row + ", " + column;
    }
}
