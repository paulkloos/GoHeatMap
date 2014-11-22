package pieces;

import go.Stone;

public class Piece {
	protected Stone value;
	private int liberties;
	private Integer id;
	
	public Piece() {
		setValue(Stone.BLANK);
		liberties = 0;
	}
	
	public Piece(Stone value) {
		setValue(value);
		liberties = 4;
	}
	
	public Stone getValue() {
		return value;
	}
	
	public void setValue(Stone value) {
		this.value = value;
	}
	
	public void setLiberties(int count) {
		liberties = count;
	}
	
	public int getLiberties() {
		return liberties;
	}
	
	public void incrementLiberty() {
		liberties++;
	}
	
	public void decrementLiberty() {
		liberties--;
	}
	
	public boolean isBlank() {
		if (value == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setGroup(Integer value) {
		id = value;
	}
	
	public Integer getGroup() {
		return id;
	}
}
