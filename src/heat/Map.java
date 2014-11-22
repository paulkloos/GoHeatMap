package heat;

import java.util.Arrays;

import pieces.Record;
import board.Board;
import go.Stone;

public class Map {
	
	private Board board;
	private Double[][] heatBoard;
	private Stone type;
	private int boardSize;
	
	public Map(Stone type, Board board) {
		this.board = board;
		this.type = type;
		boardSize = board.getBoardSize();
		clearBoard();
	}
	
	public void update() {
		clearBoard();
		int size = board.getPiecesPlayed();
		for (int index = 0; index < size; index++) {
			Record temp = board.getPlay(index);
			if (temp.getPiece() != type) {
				continue;
			}
			int[] start = temp.getCoords();
			Boolean blank;
			//positive x/y
			for (int indexx = start[0]+1; indexx < boardSize; indexx++) {
				blank = true;
				for (int indexy = start[1]; indexy < boardSize && blank; indexy++) {
					if (board.getValue(indexx, indexy).getValue() != Stone.BLANK) {
						blank = false;
					}
					else {
						//calculate heat
						heatBoard[indexx][indexy] += strength(Math.abs((double)(start[Board.X]-indexx)), Math.abs((double)(start[Board.Y]-indexy)));
					}
				}								
			}
			
			//negative x/ positive y
			for (int indexx = start[0]; indexx >= 0; indexx--) {
				blank = true;
				for (int indexy = start[1]+1; indexy < boardSize && blank; indexy++) {
					if (board.getValue(indexx, indexy).getValue() != Stone.BLANK) {
						blank = false;
					}
					else {
						//calculate heat
						heatBoard[indexx][indexy] += strength(Math.abs((double)(start[Board.X]-indexx)), Math.abs((double)(start[Board.Y]-indexy)));
					}
				}								
			}
			
			//negative x/y
			for (int indexx = start[0]-1; indexx >= 0; indexx--) {
				blank = true;
				for (int indexy = start[1]; indexy >= 0 && blank; indexy--) {
					if (board.getValue(indexx, indexy).getValue() != Stone.BLANK) {
						blank = false;
					}
					else {
						//calculate heat
						heatBoard[indexx][indexy] += strength(Math.abs((double)(start[Board.X]-indexx)), Math.abs((double)(start[Board.Y]-indexy)));
					}
				}								
			}
			
			//positive x/ negative y
			for (int indexx = start[0]; indexx < boardSize; indexx++) {
				blank = true;
				for (int indexy = start[1]-1; indexy >= 0 && blank; indexy--) {
					if (board.getValue(indexx, indexy).getValue() != Stone.BLANK) {
						blank = false;
					}
					else {
						//calculate heat
						heatBoard[indexx][indexy] += strength(Math.abs((double)(start[Board.X]-indexx)), Math.abs((double)(start[Board.Y]-indexy)));
					}
				}								
			}
		}
	}
	
	private Double strength(Double absx, Double absy) {
		return Math.pow(0.5D, (absx+absy-1));
	}
	
	private void clearBoard() {
		heatBoard = new Double[boardSize][boardSize];
		for (Double[] row: heatBoard) {
			Arrays.fill(row, 0.0);
		}
	}
	
	public Double[][] getHeatMap() {
		return heatBoard;
	}

}
