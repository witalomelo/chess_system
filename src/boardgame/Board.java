package boardgame;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	//metodo retornando piece do tipo Piece
	public Piece piece(int row, int column) {
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	
	//sobrecarga do metodo piece passando a posição
	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		//testar se ja existe uma peça na posição
		if(thereIsAPiece(position)) {
			throw new BoardException("There is a already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
		
	}

	//remover peca
	public Piece removePiece(Position position) {
		if(!positionExists(position)){ //verifica se a posição existe no tabuleiro
			throw new BoardException("Position not on the board");
		}
		if(piece(position) == null){ //verifica se existe alguma outra peça ocupando a posição
			return null;
		}
		Piece aux = piece(position); //variavel aux para receber peça
		aux.position = null; //removendo aux 
		pieces[position.getRow()][position.getColumn()] = null; //acessando a matriz e passando null para linha e coluna
		return aux; //retorna aux que contem a peça retirada
	}


	
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	//metodo para verificar se a posição é valida reaproveitando o metodo positionExist
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	//verificar se a posição ja possui um piece
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}
	
	

}
