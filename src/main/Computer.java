package main;

import java.util.ArrayList;

import go.Stone;
import board.Board;

public class Computer extends Player {
	
	private int threadcount;
	private ArrayList<Rule> rulelist;
	
	public Computer(Board board, Stone type, int threadcount) {
		super(board, type);
		
		this.threadcount = threadcount;
	}

	public void run() {
		rulelist = new ArrayList<Rule>();
		//TODO: 1 create lists of rule classes
		if (rulelist.size() > threadcount) {
			threadcount = rulelist.size();
		}
		
		//TODO: 2 apply rules on separate threads
		//TODO: 3 create a list of possible moves
		//TODO: 4 order from highest weight to lowest
		//TODO: 5 execute number of possible moves by thread count divided by rules
		// if less moves than thread groups, run them all
		//TODO: 6 check for unique boards and group non-unique (order of moves determined by weights) overall weight averaged
		//TODO: repeat step 3 through 6 until out of time or out of moves (next move should always be accessible by a constant time function)
		
	}
	
	public void update(int x, int y, double weight) {
		
	}

}
