package pieces;

import go.STONE;

public class Piece {
	protected STONE value;
	private int liberties;
	private Integer id;
	
	public Piece() {
		setValue(null);
		liberties = 0;
	}
	
	public Piece(STONE value) {
		setValue(value);
		liberties = 4;
	}
	
	public STONE getValue() {
		return value;
	}
	
	public void setValue(STONE value) {
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
