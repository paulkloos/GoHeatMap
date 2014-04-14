package pieces;

import go.STONE;

public class Record {
	private int x;
	private int y;
	private STONE piece;
	
	public Record(int x, int y, STONE piece) {
		this.x = x;
		this.y = y;
		this.piece = piece;
	}
	
	public int[] getCoords() {
		return new int[]{x,y};
	}
	
	public STONE getPiece() {
		return piece;
	}
}
