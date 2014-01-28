package board;

import java.util.HashMap;

import pieces.Piece;

public class Board<TYPE> {
	
	private HashMap<String, Piece<TYPE>> board;
	private int boardSize;
	
	public Board(int size) {
		board = new HashMap<String, Piece<TYPE>>();
		boardSize = size;
	}
	
	public void setValue(int x, int y, TYPE value) {
		if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
			String key = keyGenerator(x,y);
			Piece<TYPE> temp = board.get(key);
			if(temp == null) {
				board.put(key, new Piece<TYPE>(value));
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
		Piece<TYPE> value = board.get(key);
		if(value != null && value.isBlank()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public TYPE getValue(int x, int y) {
		if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
			if(isBlank(keyGenerator(x,y))) {
				return (new Piece<TYPE>()).getValue();
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
