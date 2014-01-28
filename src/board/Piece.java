package board;

import java.util.ArrayList;

public class Piece {
	public enum PIECE{blank, black, white};
	private ArrayList<PIECE> value;
	
	public Piece(PIECE piece) {
		value = new ArrayList<PIECE>();
		value.add(piece);
	}
	
	public PIECE getValue() {
		return value.get(0);
	}
	
	public void setValue(PIECE piece) {
		value.add(0, piece);
	}
	
	public int getHistoryCount() {
		return value.size();
	}
	
	public PIECE getValue(int back) {
		return value.get(back);
	}
}
