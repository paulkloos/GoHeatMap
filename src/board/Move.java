package board;

import go.Stone;
import pieces.Piece;

public class Move {
	private int x;
	private int y;
	protected Piece piece;
	private Integer[] groupIds;
	private int group_count;
	
	public Move(int x, int y, Stone type) {
		this.x = x;
		this.y = y;
		this.piece = new Piece(type);
		groupIds = new Integer[]{0,0,0,0};
		group_count = 0;
	}
	
	public void addGroup(Integer group) {
		groupIds[group_count] = group;
		group_count++;
	}
	
	public Integer[] getGroups() {
		return groupIds;
	}
	
	public int getGroupCount() {
		return group_count;
	}
	
	public int[] getCoords() {
		int[] temp = new int[2];
		temp[Board.X] = x;
		temp[Board.Y] = y;
		return temp;
	}
	
	public Stone getType() {
		return piece.getValue();
	}
}
