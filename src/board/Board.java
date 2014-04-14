package board;

import go.STONE;

import java.util.Hashtable;
import java.util.Vector;

import pieces.Grouping;
import pieces.Piece;
import pieces.Record;

public class Board {
	
	private Piece[][] board;
	private Vector<Record> playedAreas;
	private int boardSize;
	private Hashtable<Integer,Grouping> groupings;
	private Integer groupId;
	
	public Board(int size) {
		board = new Piece[size][size];
		playedAreas = new Vector<Record>();
		groupings = new Hashtable<Integer,Grouping>();
		boardSize = size;
		groupId = 1;
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				board[x][y] = new Piece();
			}
		}
	}
	
	public void setValue(int x, int y, STONE value) {
		STONE[] stones;
		getValue(x, y).setValue(value);
		stones = updateLiberties(x,y);
		setGroup(x, y, stones);
		playedAreas.add(new Record(x,y,value));
		//TODO: check for groups with 0 liberties
	}
			
	public Piece getValue(int x, int y) {
		if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) { 
			return board[x][y];
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	private STONE[] updateLiberties(int x, int y) {
		int liberties = 4;
		STONE[] stones = new STONE[]{null,null,null,null};
		STONE tmp;
		for (int index = 0; index < 4; index++) {
			int[] offsets = getOffset(index);
			if ((tmp = getValue(x+offsets[0], y+offsets[1]).getValue()) != null) {
				liberties--;
				stones[index] = tmp;
			}
		}
		getValue(x, y).setLiberties(liberties);
		
		if (getValue(x, y).getValue() != null) {
			getValue(x, y-1).decrementLiberty();
			getValue(x-1, y).decrementLiberty();
			getValue(x+1, y).decrementLiberty();
			getValue(x, y+1).decrementLiberty();
		}
		
		return stones;
	}
	
	private void setGroup(int x, int y, STONE[] stones) {
		Piece current;
		if ((current = getValue(x, y)).getValue() != null) {
			Integer last = -1;
			for (int index = 0; index < 4; index++) {
				if (stones[index] == current.getValue()) {
					last = current.getGroup();
					addGroup(x, y, index, last);
				}
			}
			if (last == -1) {
				addGroup(x, y, last, last);
			}
		}
		else {
			//TODO: handle liberty grouping
		}
	}
	
	private void addGroup(int x, int y, int source, Integer groups) {
		if (source == -1) {
			groupings.put(groupId++, new Grouping(x, y, this));
		}
		else {
			int[] offset = getOffset(source);//add the group of the current
			groupings.get(getValue(x+offset[0], y+offset[1]).getGroup()).addGroup(groupings.get(getValue(x, y).getGroup()));	
		}
	}
	
	private int[] getOffset(int index) {
		int[] value = new int[2];
		switch (index) {
			case 0:
				value[0] = 0;//x
				value[1] = -1;//y
				break;
			case 1:
				value[0] = -1;//x
				value[1] = 0;//y
				break;
			case 2:
				value[0] = 1;//x
				value[1] = 0;//y
				break;
			case 3:
				value[0] = 0;//x
				value[1] = 1; //y
				break;
		}		
		return value;
	}
	public int getPiecesPlayed() {
		return playedAreas.size();
	}
	
	public Record getPlay(int index) {
		return playedAreas.get(index);
	}
	
	public int getBoardSize() {
		return boardSize;
	}
}
