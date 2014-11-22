package board;

import go.Stone;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;

import pieces.Grouping;
import pieces.Piece;
import pieces.Record;

public class Board {
	
	private Piece[][] board;
	private Vector<Record> playedAreas;
	private HashSet<String> history;
	private int boardsize;
	private Hashtable<Integer,Grouping> groupings;
	private Integer groupId;
	private boolean updated;	
	
	private final int CARDINAL_DIRECTIONS = 4;
	private final int ALL_DIRECTIONS = 8;
	public static final int X = 0;
	public static final int Y = 1;
	
	
	public Board(int size) {
		board = new Piece[size][size];
		playedAreas = new Vector<Record>();
		history = new HashSet<String>();
		groupings = new Hashtable<Integer,Grouping>();
		boardsize = size;
		groupId = 0;
		for (int x = 0; x < boardsize; x++) {
			for (int y = 0; y < boardsize; y++) {
				board[x][y] = new Piece();
			}
		}
	}
	
	public void setValue(int x, int y, Stone value) throws Exception {
		Move this_move = new Move(x, y, value);
		
		if(isValidMove(this_move)) {
			Stone[] stones;
			Stone currentstone = this.getValue(x, y).getValue();
			this.getValue(x, y).setValue(value);
			updated = true;
			if (this.isUnqiue()) {
				stones = updateLiberties(x,y);
				setGroup(x, y, stones);
				playedAreas.add(new Record(x,y,value));
				
				Iterator<Entry<Integer, Grouping>> groups = groupings.entrySet().iterator();
				ArrayList<Integer> removal = new ArrayList<Integer>();
				
				while (groups.hasNext()) {
					Entry<Integer, Grouping> group = groups.next();
					Grouping groupValue = group.getValue();
					
					if (groupValue.getLiberties() < 1) {
						groupings.get(group.getKey()).setType(Stone.BLANK);
						removal.add(group.getKey());
						int size = groupValue.size();
						for(int index = 0; index < size; index++) {
							int[] coord = groupValue.getItem(index);
							this.getValue(coord[0], coord[1]).setValue(Stone.BLANK);
							Piece piece;
							if (y > 0 && (piece = this.getValue(coord[0], coord[1]-1)).getValue() != Stone.BLANK) {
								piece.incrementLiberty();
								groupings.get(piece.getGroup()).incrementLiberties();
							}
							if (y < boardsize-1 && (piece = this.getValue(coord[0], coord[1]+1)).getValue() != Stone.BLANK) {
								piece.incrementLiberty();
								groupings.get(piece.getGroup()).incrementLiberties();
							}
							if (x > 0 && (piece = this.getValue(coord[0]-1, coord[1])).getValue() != Stone.BLANK) {
								piece.incrementLiberty();
								groupings.get(piece.getGroup()).incrementLiberties();
							}
							if (x < boardsize-1 && (piece = this.getValue(coord[0]+1, coord[1])).getValue() != Stone.BLANK) {
								piece.incrementLiberty();
								groupings.get(piece.getGroup()).incrementLiberties();
							}
						}
					}
				}
				//remove all groups that just got all stones removed
				int removalsize = removal.size();
				for (int index = 0; index < removalsize; index++) {
					groupings.remove(removal.get(index));
				}
			}
			else {
				this.getValue(x, y).setValue(currentstone);//revert change
				throw new Exception("Ko rule violated");
			}
		}				
	}
	public Piece getValue(Move value) {
		int[] coord = value.getCoords();
		return this.getValue(coord[0], coord[1]);
	}
	
	public Piece getValue(int x, int y) {
		if(x >= 0 && x < boardsize && y >= 0 && y < boardsize) { 
			return board[x][y];
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	private Stone[] updateLiberties(int x, int y) {
		int liberties = 4;
		Stone[] stones = new Stone[]{Stone.BLANK,Stone.BLANK,Stone.BLANK,Stone.BLANK};
		Stone tmp;
		for (int index = 0; index < CARDINAL_DIRECTIONS; index++) {
			int[] offsets = getOffset(Direction.getDirection(index));
			if (x+offsets[0] < boardsize && x+offsets[0] >=0 && y+offsets[1] < boardsize && y+offsets[1] >= 0) {
				if ((tmp = this.getValue(x+offsets[0], y+offsets[1]).getValue()) != Stone.BLANK) {
					liberties--;
					stones[index] = tmp;
				}
			}
			else {
				liberties--;
				stones[index] = null;
			}
		}
		this.getValue(x, y).setLiberties(liberties);
		
		if (this.getValue(x, y).getValue() != Stone.BLANK) {
			Piece piece;
			if (y > 0 && (piece = this.getValue(x, y-1)).getValue() != Stone.BLANK) {
				piece.decrementLiberty();
				groupings.get(piece.getGroup()).decrementLiberties();
			}
			if (y < boardsize-1 && (piece = this.getValue(x, y+1)).getValue() != Stone.BLANK) {
				piece.decrementLiberty();
				groupings.get(piece.getGroup()).decrementLiberties();
			}
			if (x > 0 && (piece = this.getValue(x-1, y)).getValue() != Stone.BLANK) {
				piece.decrementLiberty();
				groupings.get(piece.getGroup()).decrementLiberties();
			}
			if (x < boardsize-1 && (piece = this.getValue(x+1, y)).getValue() != Stone.BLANK) {
				piece.decrementLiberty();
				groupings.get(piece.getGroup()).decrementLiberties();
			}			
		}
		
		return stones;
	}
	
	private void setGroup(int x, int y, Stone[] stones) {
		Piece current = this.getValue(x, y);
		Integer last = -1;
		for (int index = 0; index < CARDINAL_DIRECTIONS; index++) {
			if (stones[index] == current.getValue()) {
				Direction direction = Direction.getDirection(index);
				int[] offset = Board.getOffset(direction);
				last = this.getValue(x + offset[0], y + offset[1]).getGroup();
				if (current.getGroup() != last) {
					addGroup(x, y, last);
				}
			}
		}
		if (last == -1) {
			addGroup(x, y, last);
		}
	}
	
	private void addGroup(int x, int y,  Integer groups) {
		if (groups == -1) {
			groupId++;
			groupings.put(groupId, new Grouping(x, y, this, this.getValue(x, y).getValue()));
			this.getValue(x, y).setGroup(groupId);
		}
		else {
			Integer currentgroup = this.getValue(x, y).getGroup();
			if (currentgroup == null) {
				groupings.get(groups).addItem(x, y);
				this.getValue(x, y).setGroup(groups);
			}
			else {
				groupings.get(groups).addGroup(groupings.get(currentgroup));
				
				int size = groupings.get(currentgroup).size();
				for (int index = 0; index < size; index++) {
					int[] coords = groupings.get(currentgroup).getItem(index);
					this.getValue(coords[0], coords[1]).setGroup(groups);
				}
				
				groupings.remove(currentgroup);
			}
		}
	}
	/**
	 * 
	 * @param index
	 * Value indicating a direction relative from the current space
	 * @return
	 */
	private static int[] getOffset(Direction index) {
		int[] value = new int[2];
		switch (index) {
			case TOP:
				value[Board.X] = 0;//x
				value[Board.Y] = -1;//y
				break;
			case LEFT:
				value[Board.X] = -1;//x
				value[Board.Y] = 0;//y
				break;
			case RIGHT:
				value[Board.X] = 1;//x
				value[Board.Y] = 0;//y
				break;
			case BOTTOM:
				value[Board.X] = 0;//x
				value[Board.Y] = 1; //y
				break;
			case TOP_LEFT:
				value[Board.X] = -1;
				value[Board.Y] = -1;
				break;
			case TOP_RIGHT:
				value[Board.X] = 1;
				value[Board.Y] = -1;
				break;
			case BOTTOM_LEFT:
				value[Board.X] = -1;
				value[Board.Y] = 1;
				break;
			case BOTTOM_RIGHT:
				value[Board.X] = 1;
				value[Board.Y] = 1;
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
		return boardsize;
	}
	
	public Boolean isValidMove(Move move) {
		if (this.getValue(move).getValue() != Stone.BLANK) {
			return false;
		}
		int[] coords = move.getCoords();
		int libertycount = 4;
		int groupliberty = 0;
		for (int index = 0; index < CARDINAL_DIRECTIONS; index++) {
			int[] offset = Board.getOffset(Direction.getDirection(index));
			if ( coords[0] + offset[0] < boardsize && coords[0] + offset[0] >= 0 && coords[1] + offset[1] < boardsize && coords[1] + offset[1] >= 0) {
				Piece value = this.getValue(coords[0] + offset[0], coords[1] + offset[1]);
				if (value.getValue() == move.getType()) {
					libertycount--;
					groupliberty += groupings.get(value.getGroup()).getLiberties()-1;
				}
				else if (value.getValue() != Stone.BLANK && value.getLiberties() > 1) {
					libertycount--;
				}
			}
			else {
				libertycount--;
			}
		}
		
		if (libertycount == 0 && groupliberty == 0) {
			return false;
		}		
		
		return true;
	}
	
	public Boolean isUnqiue() {
		if (this.updated) {
			StringBuilder currentboard = new StringBuilder();
			for(int x = 0; x < boardsize; x++) {
				for(int y = 0; y < boardsize; y++) {
					if(board[x][y].getValue() == Stone.BLACK) {
						currentboard.append("b");
					}
					else if(board[x][y].getValue() == Stone.WHITE) {
						currentboard.append("w");
					}
					else {
						currentboard.append("x");
					}
				}
			}
			if(history.contains(currentboard.toString())) {
				return false;
			}
			else {
				this.updated = false;
				history.add(currentboard.toString());
				return true;
			}
		}
		else {
			return true;//non-unique boards should be reverted in set value
		}
	}
}
