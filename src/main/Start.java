package main;

import pieces.Piece;
import go.STONE;
import heat.Map;
import board.Board;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board goboard = new Board(9);
		Map map = new Map(STONE.black,goboard);
		goboard.setValue(5, 5, STONE.black);
		goboard.setValue(3, 3, STONE.black);
		map.update(5);
		
		printBoard(map.getHeatMap());
	}
	
	public static void printBoard(Double[][] board) {
		int size = board.length;
		for(int x = 0; x < size-1; x++) {
			for(int y = 0; y < size-1; y++) {
				System.out.print(board[x][y]);
				if(y < size-1) {
					System.out.print("---");
				}
			}
			System.out.println(" ");
		}
	}

}
