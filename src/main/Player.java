package main;

import go.Stone;
import board.Board;

public abstract class Player implements Runnable {
	private Board board;
	private Stone type;

	public Player(Board board, Stone type) {
		this.board = board;
		this.type = type;
		
	}
		
	protected void placeStone(int x, int y) throws Exception {
		board.setValue(x, y, this.type);
	}
	
	protected void pass() {
		
	}
	
	protected void resign() {
		
	}
	
	protected Board getBoard() {
		return board;
	}

	public abstract void run();
}
