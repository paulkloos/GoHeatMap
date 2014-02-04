package board;

import java.util.ArrayList;
import java.util.HashMap;

import pieces.Piece;

public class Board<TYPE> {
	
	private HashMap<String, Piece<TYPE>> board;
	private ArrayList<int[]> playedAreas;
	private int boardSize;
	
	public Board(int size) {
		board = new HashMap<String, Piece<TYPE>>();
		playedAreas = new ArrayList<int[]>();
		boardSize = size;
	}
	
	public void setValue(int x, int y, TYPE value) {
		if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
			String key = keyGenerator(x,y);
			Piece<TYPE> temp = board.get(key);
			if(temp == null) {
				board.put(key, new Piece<TYPE>(value));
				int[] coords = {x,y};
				playedAreas.add(coords);
			}
			else {
				board.get(key).setValue(value);
			}
		}
		else {
			throw new IndexOutOfBoundsException("board index does not exist");
		}
	}
	
	public void setValue(int x, int y, int index, TYPE value) {
		if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
			String key = keyGenerator(x,y);
			Piece<TYPE> temp = board.get(key);
			if(temp == null && index == 0) {
				board.put(key, new Piece<TYPE>(value));
				int[] coords = {x,y};
				playedAreas.add(coords);
			}
			else if(temp == null) {
				throw new IndexOutOfBoundsException("Index in piece history does not exist");
			}
			else {
				board.get(key).setValue(index,value);
			}
		}
		else {
			throw new IndexOutOfBoundsException("board index does not exist");
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
	
	public Piece<TYPE> getValue(int x, int y) {
		if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
			String key = keyGenerator(x,y); 
			if(isBlank(key)) {
				return null;
			}
			else {
				return board.get(key);
			}
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public int getPiecesPlayed() {
		return playedAreas.size();
	}
	
	public int getBoardSize() {
		return boardSize;
	}
	
	public int[] getBoardCoords(int index) {
		return playedAreas.get(index);
	}

}
