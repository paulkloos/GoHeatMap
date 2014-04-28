package server;

import go.stone;

public class Message {
	private String command;
	private String piece;
	private int x;
	private int y;
	
	public enum commands{NEW_GAME, PLAY_PIECE, PASS, INVALID};
	
	public Message(String command) {
		this.command = command;
	}
	
	public Message(String command, String piece, int x, int y) {
		this.command = command;
		this.piece = piece;
		this.x = x;
		this.y = y;
	}
	
	public commands getCommand() {
		switch (command) {
		case "new game":
			return commands.NEW_GAME;
		case "play piece":
			return commands.PLAY_PIECE;
		case "pass":
			return commands.PASS;
		default:
			return commands.INVALID;
		}
	}
	
	public int[] getCoords() {
		return new int[]{x,y};
	}
	
	public stone getPiece() {
		switch (piece) {
		case "black":
			return stone.BLACK;
		case "white":
			return stone.WHITE;
		default:
			return null;
		}
	}

}
