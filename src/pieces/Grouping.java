package pieces;

import go.Stone;

import java.util.Vector;

import board.Board;

public class Grouping {
	private Vector<int[]> list;
	private int liberties;
	private Board board;
	private Stone type;
	
	public Grouping(int x, int y, Board board, Stone type) {
		list = new Vector<int[]>();
		list.add(new int[]{x,y});
		this.board = board;
		liberties = board.getValue(x, y).getLiberties();
		this.type = type;
	}
	
	public void addGroup(Grouping group) {
		int size = group.size();
		for (int x = 0; x < size; x++) {
			list.add(group.getItem(x));
			int[] coords = group.getItem(x);
			liberties += board.getValue(coords[0], coords[1]).getLiberties();
		}
	}
	
	public void addItem(int x, int y) {
		list.add(new int[]{x, y});
		liberties += board.getValue(x, y).getLiberties();
	}
	
	public int[] getItem(int index) {
		return list.get(index);
	}
	
	public int size() {
		return list.size();
	}
	
	public int getLiberties() {
		return liberties;
	}
	
	public void incrementLiberties() {
		liberties++;
	}
	
	public void decrementLiberties() {
		liberties--;
	}
	
	public void setType(Stone type) {
		this.type = type;
	}
	
	public Stone getType() {
		return type;
	}
}
