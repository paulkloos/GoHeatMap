package pieces;

import java.util.ArrayList;

public class Piece<TYPE> {
	protected ArrayList<TYPE> list;
	
	public Piece() {
		list = new ArrayList<TYPE>();
	}
	
	public Piece(TYPE value) {
		super();
		list.add(value);
	}
	
	public TYPE getValue() {
		return list.get(0);
	}
	
	public void setValue(TYPE value) {
		list.add(0, value);
	}
	
	public int getHistoryCount() {
		return list.size();
	}
	
	public TYPE getValue(int index) {
		return list.get(index);
	}
	
	public boolean isBlank() {
		return false;
	}
}
