package heat;

import pieces.Piece;
import board.Board;
import go.STONE;

public class Map {
	
	private Board<STONE> board;
	private Board<Double> heatBoard;
	private STONE type;
	
	public Map(STONE type, Board<STONE> board) {
		this.board = board;
		this.type = type;
		clearBoard();
	}
	
	public void update(int radius) {
		int size = board.getPiecesPlayed();
		clearBoard();
		for(int index = 0; index < size; index++) {
			int[] coords = board.getBoardCoords(index);
			Piece<STONE> temp = board.getValue(coords[0], coords[1]);
			
			if(temp.getValue() == type) {
				for(int x = coords[0]-radius; x < coords[0]+radius; x++) {
					if(x < 0) {
						continue;
					}
					else if(x > board.getBoardSize()) {
						break;
					}
					for(int y = coords[1]-radius; y < coords[1]+radius; y++) {
						if(y < 0) {
							continue;
						}
						else if(y > board.getBoardSize()) {
							break;
						}//subtracted 1 from each coord to make zero based
						if(board.getValue(x, y).isBlank()) {//set value if no current value
							if(heatBoard.getValue(x, y).isBlank()) {
								heatBoard.setValue(x, y, strength(Math.abs((double)(coords[0]-x-1)), Math.abs((double)(coords[1]-y-1))));
							}
							else {//sum value if one already exists
								Double current = heatBoard.getValue(x, y).getValue();
								heatBoard.setValue(x, y, 0, current+strength(Math.abs((double)(coords[0]-x-1)), Math.abs((double)(coords[1]-y-1))));
							}
						}
					}
				}
			}
		}
	}
	
	private Double strength(Double absx, Double absy) {
		return Math.pow(0.5D, (absx+absy));
	}
	
	private void clearBoard() {
		heatBoard = new Board<Double>(board.getBoardSize());
	}
	
	public Board<Double> getHeatMap() {
		return heatBoard;
	}

}
