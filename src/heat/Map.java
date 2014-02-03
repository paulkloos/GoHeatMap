package heat;

import board.Board;
import go.GoPiece;
import go.STONE;

public class Map {
	
	private Board<GoPiece> board;
	private Board<ThermometerPiece> heatBoard;
	private STONE type;
	
	public Map(STONE type, Board<GoPiece> board) {
		this.board = board;
		this.type = type;
		heatBoard = new Board<ThermometerPiece>(board.getBoardSize());
	}
	
	public void update() {
		int size = board.getPiecesPlayed();
		for(int index = 0; index < size; index++) {
			int[] coords = board.getBoardCoords(index);
			GoPiece temp = board.getValue(coords[0], coords[1]);
			
			if(temp.getValue() == type) {
				for(int x = coords[0]-1; x < coords[0]+1; x++) {
					if(x < 0) {
						continue;
					}
					else if(x > board.getBoardSize()) {
						break;
					}
					for(int y = coords[1]-1; y < coords[1]+1; y++) {
						if(y < 0) {
							continue;
						}
						else if(y > board.getBoardSize()) {
							break;
						}
						//TODO: continue heat logic here
						
					}
				}
			}
		}
	}
	
	public Board<ThermometerPiece> getHeatMap() {
		return heatBoard;
	}

}
