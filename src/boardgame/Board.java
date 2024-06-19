package boardgame;      // jogo de tabuleiro

// classe referente ao tabuleiro
public class Board {

    // atributos
    private Integer rows;       // quantidade de linhas
    private Integer columns;    // quantidade de colunas
    private Piece[][] pieces;   // matriz de peças

    // construtor
    public Board(Integer rows, Integer columns) {
        // verificando se a quantidade de linha e colunas são pelo menos uma
        if (rows < 1 || columns < 1){
            // lançará uma exceção
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];  // define a matriz com a quantidade de linhas e colunas
    }

    // get e set
    public Integer getRows() {
        return rows;
    }
    public Integer getColumns() {
        return columns;
    }

    // método que retorna o objeto Piece em que se encontra em uma linha e coluna passadas como argumento
    public Piece piece(int row, int column){
        // verificnado se a posição passada não existe
        if (!positionExist(row, column)){
            // lançará uma exeção
            throw new BoardException("Position not on the board");
        }
        return pieces[row][column];
    }

    // sobrecarga: retorna o objeto Piece baseado em uma Position passada como argumento
    public Piece piece(Position position){
        // verificnado se a posição passada não existe
        if (!positionExist(position)){
            // lançará uma exeção
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    // método para colocar uma peça em uma posição do tabuleito
    public void placePiece(Piece piece, Position position){
        // verificando se existe uma peça naquela posição
        if (thereIsAPiece(position)){
            // lançará uma exeção
            throw new BoardException("there is already a piece on position " + position);
        }
        // adiconando peça no tabuleiro na posição especificada
        pieces[position.getRow()][position.getColumn()] = piece;
        // mudando status de posição da peça
        piece.position = position;
    }

    // método para remover uma peça baseado em uma posição
    public Piece removePiece(Position position){
        // verificando se a posição não existe
        if (!positionExist(position)){
            throw new BoardException("Position not on the board");
        }
        // verificando se não existe peça na posição de argumento
        if (piece(position) == null){
            return null;
        }
        // criando uma variável auxiliar para guardar a peça
        Piece aux = piece(position);
        // removendo posição da peça, deixando nulo
        aux.position = null;
        // removendo peça do tabuleiro, deixando-a nula
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }

    // método para verificar se uma posição existe
    private boolean positionExist(int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    // sobrecarga do método positionExist()
    public boolean positionExist(Position position){
        return positionExist(position.getRow(), position.getColumn());
    }

    // método para verificar se existe uma peça em uma determida posição
    public boolean thereIsAPiece(Position position){
        // verificnado se a posição passada não existe
        if (!positionExist(position)){
            // lançará uma exeção
            throw new BoardException("Position not on the board");
        }
        // se a peça estiver nessa posição for diferente de nulo, então há uma peça
        return piece(position) != null;
    }
}
