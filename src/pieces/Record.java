package pieces;

import go.stone;

public class Record {
	private int x;
	private int y;
	private stone piece;
	
	public Record(int x, int y, stone piece) {
		this.x = x;
		this.y = y;
		this.piece = piece;
	}
	
	public int[] getCoords() {
		return new int[]{x,y};
	}
	
	public stone getPiece() {
		return piece;
	}
}
