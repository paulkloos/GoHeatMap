package heat;

import pieces.Piece;

public class ThermometerPiece extends Piece<Double> {
	
	public ThermometerPiece() {
		super();
		list.set(0, 0D);
	}
	
	public ThermometerPiece(Double value) {
		super(value);
	}
	
	@Override
	public boolean isBlank() {
		if(this.getValue() == 0) {
			return true;
		}
		else {
			return false;
		}
	}

}
