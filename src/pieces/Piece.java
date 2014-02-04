package pieces;

import java.util.ArrayList;

public class Piece<TYPE> {
	protected ArrayList<TYPE> list;
	
	public Piece() {
		list = new ArrayList<TYPE>();
		list.set(0, null);
	}
	
	public Piece(TYPE value) {
		list = new ArrayList<TYPE>();
		list.add(value);
	}
	
	public TYPE getValue() {
		return list.get(0);
	}
	
	public void setValue(TYPE value) {
		list.add(0, value);
	}
	
	public void setValue(int index, TYPE value) {
		list.set(index, value);		
	}
	
	public int getHistoryCount() {
		return list.size();
	}
	
	public TYPE getValue(int index) {
		return list.get(index);
	}
	
	public boolean isBlank() {
		if(list.get(0) == null) {
			return true;
		}
		else {
			return false;
		}
	}
}
