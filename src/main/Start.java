package main;

import go.Stone;
import heat.Map;
import board.Board;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Board goboard = new Board(9);
		Map map = new Map(Stone.BLACK,goboard);
		try {
			goboard.setValue(2,2, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(5,5, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(4,4, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(6,3, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(4,5, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(5,4, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(5,2, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(4,1, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(5,3, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(6,1, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(6,2, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(7,2, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(5,0, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(4,0, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(5,1, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(6,0, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(7,1, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(8,1, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(7,0, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(8,2, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(7,3, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(7,4, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(6,4, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(6,5, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(8,3, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(8,4, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(8,0, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(6,3, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(4,2, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(3,3, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(6,4, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(3,2, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(3,1, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(1,1, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(2,3, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(1,2, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(3,4, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(4,6, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(5,6, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(6,6, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(4,7, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(6,3, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(3,6, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(1,4, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(6,4, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(7,5, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(4,3, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(6,3, Stone.WHITE);
			printBoard(goboard);
			goboard.setValue(3,0, Stone.BLACK);
			printBoard(goboard);
			goboard.setValue(2,0, Stone.WHITE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		printBoard(goboard);
		map.update();
		
		printBoard(map.getHeatMap());
		System.out.println(System.currentTimeMillis() - start);
	}
	
	public static void printBoard(Double[][] board) {
		int size = board.length;
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				System.out.print(board[x][y]);
				if(x < size-1) {
					System.out.print("---");
				}
			}
			System.out.println(" ");
		}
	}
	
	public static void printBoard(Board board) {
		int size = board.getBoardSize();
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				System.out.print(board.getValue(x, y).getValue().toString());
				if(x < size-1) {
					System.out.print("---");
				}
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

}
