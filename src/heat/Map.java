package heat;

import pieces.Piece;
import pieces.Record;
import board.Board;
import go.STONE;

public class Map {
	
	private Board board;
	private Double[][] heatBoard;
	private STONE type;
	private int size;
	
	public Map(STONE type, Board board) {
		this.board = board;
		this.type = type;
		size = board.getBoardSize();
		clearBoard();
	}
	
	public void update(int radius) {
		clearBoard();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Piece temp = board.getValue(x, y);
				if (temp.getValue() == type) {
					for (int x2 = x-radius; x2 < x+radius && x2 < size; x2++) {
						if (x2 < 0) {
							continue;
						}
						for(int y2 = y-radius; y2 < y+radius && y2 < size; y2++) {
							if (y2 < 0) {
								continue;
							}
							if (board.getValue(x2, y2).getValue() == null) {
								heatBoard[x][y] = strength(Math.abs((double)(x-x2)), Math.abs((double)(y-y2)));
							}
							else {//sum value if one already exists
								Double current = heatBoard[x][y];
								heatBoard[x][y] = current+strength(Math.abs((double)(x-x2)), Math.abs((double)(y-y2)));
							}
						}
					}
				}
			}
		}
	}
	
	private Double strength(Double absx, Double absy) {
		return Math.pow(0.5D, (absx+absy-1));
	}
	
	private void clearBoard() {
		heatBoard = new Double[size][size];
	}
	
	public Double[][] getHeatMap() {
		return heatBoard;
	}

}
