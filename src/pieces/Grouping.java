package pieces;

import java.util.Vector;

import board.Board;

public class Grouping {
	private Vector<int[]> list;
	private int liberties;
	private Board board;
	
	public Grouping(int x, int y, Board board) {
		list.add(new int[]{x,y});
		this.board = board;
		liberties = board.getValue(x, y).getLiberties();
	}
	
	public void addGroup(Grouping group) {
		int size = group.size();
		for (int x = 0; x < size; x++) {
			list.add(group.getItem(x));
			int[] coords = group.getItem(x);
			liberties += board.getValue(coords[0], coords[1]).getLiberties();
		}
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
}
