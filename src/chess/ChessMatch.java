package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//classe que vai possuir as regras da partida
public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
	}
	
	//retorna uma matriz de peças de xadrez que corresponde a essa partida
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i=0; i<board.getRows(); i++) {
			for(int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece)board.piece(i,j);
			}
		}
		return mat;
		
	}
	
	//metodo para mover peça 
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
		//converter posições para posição da matriz
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		//validacão se havia uma peça na posição de origem
		validadeSourcePosition(source);
		Piece capturedPiece = makeMove(source,target); //realizar o movimento da peça
		return (ChessPiece)capturedPiece; //realizar um do downcasting para chessPiece
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); //retirar a peça que está na posição de origem
		Piece capturedPiece = board.removePiece(target); //remover possivel peça que esta na posição de destino
		board.placePiece(p, target);
		return capturedPiece;
	}

	private void validadeSourcePosition(Position position){
		if(!board.thereIsAPiece(position)){
			throw new ChessException("There is no piece on source position");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, (char) row).toPosition());
	}
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
