package board;

import go.stone;

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
	private enum Direction{TOP(0),LEFT(1),RIGHT(2),BOTTOM(3), NONE(4);
		private final int value;
		Direction(int value){
			this.value = value;
		}
		public Direction next() {
			Direction next = NONE;//states: TOP -> LEFT -> RIGHT -> BOTTOM -> NONE
			switch(this) {
			case TOP:
				next = LEFT;
				break;
			case LEFT:
				next = RIGHT;
				break;
			case RIGHT:
				next = BOTTOM;
				break;
			}
			return next;
		}
		public int get() {
			return value;
		}
	};
	private enum Axis{X(0), Y(1);
		private final int value;
		Axis(int value) {
			this.value = value;
		}
		public int get(){
			return this.value;
		}
	};
	
	
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
	
	public void setValue(int x, int y, stone value) {
		stone[] stones;
		this.getValue(x, y).setValue(value);
		stones = updateLiberties(x,y);
		setGroup(x, y, stones);
		playedAreas.add(new Record(x,y,value));
		//TODO: check for groups with 0 liberties
		Integer gid = this.getValue(x, y).getGroup();
		Grouping group = groupings.get(gid);
		if (group.getLiberties() < 1 && group.getType() != null) {
			groupings.get(gid).setType(null);
			int size = group.size();
			for(int index = 0; index < size; index++) {//setting all peieces to blank
				int[] coord = group.getItem(index);
				this.getValue(coord[0], coord[1]).setValue(null);
			}
		}
	}
			
	public Piece getValue(int x, int y) {
		if(x >= 0 && x < boardSize && y >= 0 && y < boardSize) { 
			return board[x][y];
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	private stone[] updateLiberties(int x, int y) {
		int liberties = 4;
		stone[] stones = new stone[]{null,null,null,null};
		stone tmp;
		for (Direction index = Direction.TOP; index != Direction.NONE; index = index.next()) {
			int[] offsets = getOffset(index);
			if ((tmp = this.getValue(x+offsets[0], y+offsets[1]).getValue()) != null) {
				liberties--;
				stones[index.get()] = tmp;
			}
		}
		this.getValue(x, y).setLiberties(liberties);
		
		if (this.getValue(x, y).getValue() != null) {
			this.getValue(x, y-1).decrementLiberty();
			this.getValue(x-1, y).decrementLiberty();
			this.getValue(x+1, y).decrementLiberty();
			this.getValue(x, y+1).decrementLiberty();
		}
		
		return stones;
	}
	
	private void setGroup(int x, int y, stone[] stones) {
		Piece current;
		if ((current = getValue(x, y)).getValue() != null) {
			Integer last = -1;
			for (Direction index = Direction.TOP; index != Direction.NONE; index = index.next()) {
				if (stones[index.get()] == current.getValue()) {
					last = current.getGroup();
					addGroup(x, y, index, last);
				}
			}
			if (last == -1) {
				addGroup(x, y, null, last);
			}
		}
		else {
			for (Direction index = Direction.TOP; index.get() < 4; index = index.next()) {
				if (stones[index.get()] == null) {
					int[] offset = getOffset(index);
					Piece value;
					if ((value = this.getValue(x + offset[Axis.X.get()], y + offset[Axis.Y.get()])).getLiberties() < 4) {
						addGroup(x,y,index,value.getGroup());
					}
				}
			}
			
		}
	}
	
	private void addGroup(int x, int y, Direction source, Integer groups) {
		if (source == null) {
			groupings.put(groupId++, new Grouping(x, y, this, this.getValue(x, y).getValue()));
		}
		else {
			int[] offset = getOffset(source);//add the group of the current
			groupings.get(this.getValue(x+offset[Axis.X.get()], y+offset[Axis.Y.get()]).getGroup()).addGroup(groupings.get(this.getValue(x, y).getGroup()));	
		}
	}
	/**
	 * 
	 * @param index
	 * Value indicating a direction relative from the current space
	 * @return
	 */
	private int[] getOffset(Direction index) {
		int[] value = new int[2];
		switch (index) {
			case TOP:
				value[Axis.X.get()] = 0;//x
				value[Axis.Y.get()] = -1;//y
				break;
			case LEFT:
				value[Axis.X.get()] = -1;//x
				value[Axis.Y.get()] = 0;//y
				break;
			case RIGHT:
				value[Axis.X.get()] = 1;//x
				value[Axis.Y.get()] = 0;//y
				break;
			case BOTTOM:
				value[Axis.X.get()] = 0;//x
				value[Axis.Y.get()] = 1; //y
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
