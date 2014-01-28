package go;

import pieces.Piece;

public class GoPiece extends Piece<STONE>{
	
	public GoPiece() {
		super();
		list.set(0, STONE.blank);
	}
	public GoPiece(STONE piece) {
		super(piece);
	}
	@Override
	public boolean isBlank() {
		if(this.getValue() == STONE.blank) {
			return true;
		}
		else {
			return false;
		}
	}
}
