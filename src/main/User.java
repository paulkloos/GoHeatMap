package main;

import java.util.Arrays;
import java.util.Scanner;

import go.Stone;
import board.Board;

public class User extends Player {
	
	private Scanner input;
	private static String inputpattern = "^[A-z]{1,2}[0-9]{1,2}$";

	public User(Board board, Stone type) {
		super(board, type);
		input = new Scanner(System.in);
	}

	public void run() {
		
		while(true) {
			if (input.hasNextLine()) {
				processInput(input.nextLine());
			}
		}
	}
	
	private void processInput(String value) {
		if (value.matches(inputpattern)) {
			char[] characters = new char[value.length()];
			int x, y;
			value.getChars(0, value.length(), characters, 0);
			x = Character.getNumericValue(characters[0]);
			if (Character.isLetter(characters[1])) {
				x += Character.getNumericValue(characters[1]);
				y = Integer.valueOf(String.valueOf(Arrays.copyOfRange(characters, 2, value.length())));
			}
			else {
				y = Integer.valueOf(String.valueOf(Arrays.copyOfRange(characters, 1, value.length())));
			}
			
			try {
				this.placeStone(x, y);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (value.contains("pass")) {
			this.pass();
		}
		else if (value.contains("resign")) {
			this.resign();
		}
	}

}
