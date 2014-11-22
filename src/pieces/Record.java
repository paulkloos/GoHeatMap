package pieces;

import go.Stone;

public class Record {
	private int x;
	private int y;
	private Stone piece;
	
	public Record(int x, int y, Stone piece) {
		this.x = x;
		this.y = y;
		this.piece = piece;
	}
	
	public int[] getCoords() {
		return new int[]{x,y};
	}
	
	public Stone getPiece() {
		return piece;
	}
}
