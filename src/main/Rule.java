package main;

import board.Board;

public abstract class Rule implements Runnable {
	
	protected Board board;
	private Computer callback;
	
	public Rule(Board board, Computer reference) {
		this.board = board;
		callback = reference;
	}
	
	protected void setFinished(int x, int y, double weight) {
		callback.update(x, y, weight);
	}	 
}
