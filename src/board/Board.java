package board;

import java.util.HashMap;

import board.Piece.PIECE;

public class Board {
	
	private HashMap<String,Piece> board;
	private int boardSize;
	
	public Board(int size) {
		board = new HashMap<String, Piece>();
		boardSize = size;
	}
	
	public void setValue(int x, int y, PIECE value) {
		if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
			String key = keyGenerator(x,y);
			Piece temp = board.get(key);
			if(temp == null) {
				board.put(key, new Piece(value));
			}
			else {
				board.get(key).setValue(value);
			}
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	private String keyGenerator(int x, int y) {
		return "x"+x+"y"+y;
	}
	
	private boolean isBlank(String key) {
		Piece value = board.get(key);
		if(value != null && value.getValue() == PIECE.blank) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public PIECE getValue(int x, int y) {
		if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
			if(isBlank(keyGenerator(x,y))) {
				return PIECE.blank;
			}
			else {
				return board.get(keyGenerator(x,y)).getValue();
			}
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public int getPiecesPlayed() {
		return board.size();
	}

}
