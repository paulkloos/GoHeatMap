package pieces;

import go.stone;

public class Piece {
	protected stone value;
	private int liberties;
	private Integer id;
	
	public Piece() {
		setValue(null);
		liberties = 0;
	}
	
	public Piece(stone value) {
		setValue(value);
		liberties = 4;
	}
	
	public stone getValue() {
		return value;
	}
	
	public void setValue(stone value) {
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
