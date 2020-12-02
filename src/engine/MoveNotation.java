package engine;
import java.util.ArrayList;

public class MoveNotation {
	
	boolean rider;
	int[] movnote;
	
	public static final int[] ROOK = {1};
	public static final int[] BISHOP = {2};
	public static final int[] UNICORN = {3};
	public static final int[] DRAGON = {4};
	public static final int[] KING = {1,2,3,4};
	public static final int[] QUEEN = {1,2,3,4};
	
	//All commented out vectors are pure foreward time travel, or pure foreward with some spatial
	//which means that it is impossible and we dont need to consider
	//Stuff gets messed up with a vector of 0,
	
	
	public static final CoordFour[] NULLMOVESET = {};
	
	public static final CoordFour[] ROOKMOVESET = {
			//spatial
			new CoordFour(1,0,0,0),
			new CoordFour(0,1,0,0),
			new CoordFour(-1,0,0,0),
			new CoordFour(0,-1,0,0),
			//temporal
			new CoordFour(0,0,0,1),
			new CoordFour(0,0,-1,0),
			new CoordFour(0,0,0,-1)		
			//new CoordFive(0,0,1,0), redundant, cannot move foreward purely
	};
	
	public static final CoordFour[] BISHOPMOVESET = {
		//pure spatial
		new CoordFour(1,1,0,0),
		new CoordFour(1,-1,0,0),
		new CoordFour(-1,1,0,0),
		new CoordFour(-1,-1,0,0),
		//pure Temporal
		new CoordFour(0,0,1,1),
		new CoordFour(0,0,1,-1),
		new CoordFour(0,0,-1,1),
		new CoordFour(0,0,-1,-1),
		//+L
		new CoordFour(1,0,0,1),
		new CoordFour(-1,0,0,1),
		new CoordFour(0,1,0,1),
		new CoordFour(0,-1,0,1),
		//-L
		new CoordFour(1,0,0,-1),
		new CoordFour(-1,0,0,-1),
		new CoordFour(0,1,0,-1),
		new CoordFour(0,-1,0,-1),
		//-T
		new CoordFour(1,0,-1,0),
		new CoordFour(-1,0,-1,0),
		new CoordFour(0,1,-1,0),
		new CoordFour(0,-1,-1,0),
		//+T
		/*
		new CoordFour(1,0,1,0),
		new CoordFour(-1,0,1,0),
		new CoordFour(0,1,1,0),
		new CoordFour(0,-1,1,0),
		*/
	};
	
	public static final CoordFour[] UnicornMoveset = {
			new CoordFour(1,1,1,0),
			new CoordFour(1,1,0,1),
			new CoordFour(1,0,1,1),
			new CoordFour(0,1,1,1),
			
			new CoordFour(-1,1,1,0),
			new CoordFour(-1,1,0,1),
			new CoordFour(-1,0,1,1),
			new CoordFour(0,-1,1,1),
			
			new CoordFour(1,-1,1,0),
			new CoordFour(1,-1,0,1),
			new CoordFour(1,0,-1,1),
			new CoordFour(0,1,-1,1),
			
			new CoordFour(1,1,-1,0),
			new CoordFour(1,1,0,-1),
			new CoordFour(1,0,1,-1),
			new CoordFour(0,1,1,-1),
			
			new CoordFour(-1,-1,1,0),
			new CoordFour(-1,-1,0,1),
			new CoordFour(-1,0,-1,1),
			new CoordFour(0,-1,-1,1),
			
			new CoordFour(-1,1,-1,0),
			new CoordFour(-1,1,0,-1),
			new CoordFour(-1,0,1,-1),
			new CoordFour(0,-1,1,-1),
			
			new CoordFour(-1,-1,-1,0),
			new CoordFour(-1,-1,0,-1),
			new CoordFour(-1,0,-1,-1),
			new CoordFour(0,-1,-1,-1),
	};
	
	public static final CoordFour[] DragonMoveset = {
			new CoordFour(1,1,1,1),
			new CoordFour(-1,1,1,1),	
			new CoordFour(1,-1,1,1),	
			new CoordFour(1,1,-1,1),
			new CoordFour(1,1,1,-1),	
			new CoordFour(-1,-1,1,1),	
			new CoordFour(-1,1,-1,1),	
			new CoordFour(-1,1,1,-1),
			new CoordFour(1,-1,-1,1),
			new CoordFour(1,-1,1,-1),
			new CoordFour(1,1,-1,-1),
			new CoordFour(1,-1,-1,-1),
			new CoordFour(-1,1,-1,-1),	
			new CoordFour(-1,-1,1,-1),	
			new CoordFour(-1,-1,-1,1),
			new CoordFour(-1,-1,-1,-1),
	};
	
	public static final CoordFour[] KNIGHTMOVESET = {
			//pure Spatial
			new CoordFour(1,2,0,0),
			new CoordFour(2,1,0,0),
			new CoordFour(-1,2,0,0),
			new CoordFour(-2,1,0,0),
			new CoordFour(-2,-1,0,0),
			new CoordFour(-1,-2,0,0),
			new CoordFour(1,-2,0,0),
			new CoordFour(2,-1,0,0),
			//pure temporal
			new CoordFour(0,0,1,2),
			new CoordFour(0,0,2,1),
			new CoordFour(0,0,-1,2),
			new CoordFour(0,0,-2,1),
			new CoordFour(0,0,-2,-1),
			new CoordFour(0,0,-1,-2),
			new CoordFour(0,0,1,-2),
			new CoordFour(0,0,2,-1),
			//Half Spatial/temporal
			//    +L
			new CoordFour(2,0,0,1),
			new CoordFour(-2,0,0,1),
			new CoordFour(0,2,0,1),
			new CoordFour(0,-2,0,1),
			new CoordFour(1,0,0,2),
			new CoordFour(-1,0,0,2),
			new CoordFour(0,1,0,2),
			new CoordFour(0,-1,0,2),
			//  -L
			new CoordFour(2,0,0,-1),
			new CoordFour(-2,0,0,-1),
			new CoordFour(0,2,0,-1),
			new CoordFour(0,-2,0,-1),
			new CoordFour(1,0,0,-2),
			new CoordFour(-1,0,0,-2),
			new CoordFour(0,1,0,-2),
			new CoordFour(0,-1,0,-2),
			//  -T
			new CoordFour(2,0,-1,0),
			new CoordFour(-2,0,-1,0),
			new CoordFour(0,2,-1,0),
			new CoordFour(0,-2,-1,0),
			new CoordFour(1,0,-2,0),
			new CoordFour(-1,0,-2,0),
			new CoordFour(0,1,-2,0),
			new CoordFour(0,-1,-2,0)
			//pure foreward (+T)
			/*
			new CoordFour(2,0,1,0),
			new CoordFour(-2,0,1,0),
			new CoordFour(0,2,1,0),
			new CoordFour(0,-2,1,0),
			new CoordFour(1,0,2,0),
			new CoordFour(-1,0,2,0),
			new CoordFour(0,1,2,0),
			new CoordFour(0,-1,2,0)
			*/
			
	};
	
	public static final CoordFour[] PRINCESSMOVESET = {
			//ROOK
			//spatial
			new CoordFour(1,0,0,0),
			new CoordFour(0,1,0,0),
			new CoordFour(-1,0,0,0),
			new CoordFour(0,-1,0,0),
			//temporal
			new CoordFour(0,0,0,1),
			new CoordFour(0,0,-1,0),
			new CoordFour(0,0,0,-1),		
			//new CoordFive(0,0,1,0), redundant, cannot move foreward purely
			
			//Bishop
			//pure spatial
			new CoordFour(1,1,0,0),
			new CoordFour(1,-1,0,0),
			new CoordFour(-1,1,0,0),
			new CoordFour(-1,-1,0,0),
			//pure Temporal
			new CoordFour(0,0,1,1),
			new CoordFour(0,0,1,-1),
			new CoordFour(0,0,-1,1),
			new CoordFour(0,0,-1,-1),
			//+L
			new CoordFour(1,0,0,1),
			new CoordFour(-1,0,0,1),
			new CoordFour(0,1,0,1),
			new CoordFour(0,-1,0,1),
			//-L
			new CoordFour(1,0,0,-1),
			new CoordFour(-1,0,0,-1),
			new CoordFour(0,1,0,-1),
			new CoordFour(0,-1,0,-1),
			//-T
			new CoordFour(1,0,-1,0),
			new CoordFour(-1,0,-1,0),
			new CoordFour(0,1,-1,0),
			new CoordFour(0,-1,-1,0),
			//+T
			/*
			new CoordFour(1,0,1,0),
			new CoordFour(-1,0,1,0),
			new CoordFour(0,1,1,0),
			new CoordFour(0,-1,1,0),
			*/
	};
	
	//This one is probably not needed, due to the reason mentioned in the function below.
	public static final CoordFour[] QUEENMOVESET = { //all copied directly from the other movesets, so problems in those will have problems in these
			
			//Rook Movement
			//spatial
			new CoordFour(1,0,0,0),
			new CoordFour(0,1,0,0),
			new CoordFour(-1,0,0,0),
			new CoordFour(0,-1,0,0),
			//temporal
			new CoordFour(0,0,0,1),
			new CoordFour(0,0,-1,0),
			new CoordFour(0,0,0,-1),		
			//new CoordFive(0,0,1,0), redundant, cannot move foreward purely
			
			//BishopMovement
			new CoordFour(1,1,0,0),
			//new CoordFive(1,0,1,0),
			new CoordFour(1,0,0,1),
			//new CoordFive(0,1,1,0),
			new CoordFour(0,1,0,1),
			new CoordFour(0,0,1,1),
			new CoordFour(-1,1,0,0),
			//new CoordFive(-1,0,1,0),
			new CoordFour(-1,0,0,1),
			//new CoordFive(0,-1,1,0),
			new CoordFour(0,-1,0,1),
			new CoordFour(0,0,-1,1),
			new CoordFour(1,-1,0,0),
			new CoordFour(1,0,-1,0),
			new CoordFour(1,0,0,-1),
			new CoordFour(0,1,-1,0),
			new CoordFour(0,1,0,-1),
			new CoordFour(0,0,1,-1),	
			new CoordFour(-1,-1,0,0),
			new CoordFour(-1,0,-1,0),
			new CoordFour(-1,0,0,-1),
			new CoordFour(0,-1,-1,0),
			new CoordFour(0,-1,0,-1),
			new CoordFour(0,0,-1,-1),
			
			
			//Uniconrn Movement
			new CoordFour(1,1,1,0),
			new CoordFour(1,1,0,1),
			new CoordFour(1,0,1,1),
			new CoordFour(0,1,1,1),
			new CoordFour(-1,1,1,0),
			new CoordFour(-1,1,0,1),
			new CoordFour(-1,0,1,1),
			new CoordFour(0,-1,1,1),
			new CoordFour(1,-1,1,0),
			new CoordFour(1,-1,0,1),
			new CoordFour(1,0,-1,1),
			new CoordFour(0,1,-1,1),
			new CoordFour(1,1,-1,0),
			new CoordFour(1,1,0,-1),
			new CoordFour(1,0,1,-1),
			new CoordFour(0,1,1,-1),
			new CoordFour(-1,-1,1,0),
			new CoordFour(-1,-1,0,1),
			new CoordFour(-1,0,-1,1),
			new CoordFour(0,-1,-1,1),
			new CoordFour(-1,1,-1,0),
			new CoordFour(-1,1,0,-1),
			new CoordFour(-1,0,1,-1),
			new CoordFour(0,-1,1,-1),
			new CoordFour(-1,-1,-1,0),
			new CoordFour(-1,-1,0,-1),
			new CoordFour(-1,0,-1,-1),
			new CoordFour(0,-1,-1,-1),
			
			
			//Dragon Movement 
			new CoordFour(1,1,1,1),
			new CoordFour(-1,1,1,1),	
			new CoordFour(1,-1,1,1),	
			new CoordFour(1,1,-1,1),
			new CoordFour(1,1,1,-1),	
			new CoordFour(-1,-1,1,1),	
			new CoordFour(-1,1,-1,1),	
			new CoordFour(-1,1,1,-1),
			new CoordFour(1,-1,-1,1),
			new CoordFour(1,-1,1,-1),
			new CoordFour(1,1,-1,-1),
			new CoordFour(1,-1,-1,-1),
			new CoordFour(-1,1,-1,-1),	
			new CoordFour(-1,-1,1,-1),	
			new CoordFour(-1,-1,-1,1),
			new CoordFour(-1,-1,-1,-1),
	};
	
	public static final CoordFour[] KINGMOVESET = {
			//pure Spatial
			new CoordFour(1,0,0,0),
			new CoordFour(1,1,0,0),
			new CoordFour(1,-1,0,0),
			new CoordFour(-1,0,0,0),
			new CoordFour(-1,1,0,0),
			new CoordFour(-1,-1,0,0),
			new CoordFour(0,1,0,0),
			new CoordFour(0,-1,0,0),
			//+L board
			new CoordFour(1,0,0,1),
			new CoordFour(1,1,0,1),
			new CoordFour(1,-1,0,1),
			new CoordFour(-1,0,0,1),
			new CoordFour(-1,1,0,1),
			new CoordFour(-1,-1,0,1),
			new CoordFour(0,1,0,1),
			new CoordFour(0,-1,0,1),
			new CoordFour(0,0,0,1),
			//-L board
			new CoordFour(1,0,0,-1),
			new CoordFour(1,1,0,-1),
			new CoordFour(1,-1,0,-1),
			new CoordFour(-1,0,0,-1),
			new CoordFour(-1,1,0,-1),
			new CoordFour(-1,-1,0,-1),
			new CoordFour(0,1,0,-1),
			new CoordFour(0,-1,0,-1),
			new CoordFour(0,0,0,-1),
			//-T board
			new CoordFour(1,0,-1,0),
			new CoordFour(1,1,-1,0),
			new CoordFour(1,-1,-1,0),
			new CoordFour(-1,0,-1,0),
			new CoordFour(-1,1,-1,0),
			new CoordFour(-1,-1,-1,0),
			new CoordFour(0,1,-1,0),
			new CoordFour(0,-1,-1,0),
			new CoordFour(0,0,-1,0),
			//-T,-L
			new CoordFour(1,0,-1,-1),
			new CoordFour(1,1,-1,-1),
			new CoordFour(1,-1,-1,-1),
			new CoordFour(-1,0,-1,-1),
			new CoordFour(-1,1,-1,-1),
			new CoordFour(-1,-1,-1,-1),
			new CoordFour(0,1,-1,-1),
			new CoordFour(0,-1,-1,-1),
			new CoordFour(0,0,-1,-1),
			//-T,+L
			new CoordFour(1,0,-1,1),
			new CoordFour(1,1,-1,1),
			new CoordFour(1,-1,-1,1),
			new CoordFour(-1,0,-1,1),
			new CoordFour(-1,1,-1,1),
			new CoordFour(-1,-1,-1,1),
			new CoordFour(0,1,-1,1),
			new CoordFour(0,-1,-1,1),
			new CoordFour(0,0,-1,1),
			//+T,+L
			new CoordFour(1,0,1,1),
			new CoordFour(1,1,1,1),
			new CoordFour(1,-1,1,1),
			new CoordFour(-1,0,1,1),
			new CoordFour(-1,1,1,1),
			new CoordFour(-1,-1,1,1),
			new CoordFour(0,1,1,1),
			new CoordFour(0,-1,1,1),
			new CoordFour(0,0,1,1),
			//+T,-L
			new CoordFour(1,0,1,-1),
			new CoordFour(1,1,1,-1),
			new CoordFour(1,-1,1,-1),
			new CoordFour(-1,0,1,-1),
			new CoordFour(-1,1,1,-1),
			new CoordFour(-1,-1,1,-1),
			new CoordFour(0,1,1,-1),
			new CoordFour(0,-1,1,-1),
			new CoordFour(0,0,1,-1),
			//+T board
			/*
			new CoordFour(1,0,1,0),
			new CoordFour(1,1,1,0),
			new CoordFour(1,-1,1,0),
			new CoordFour(-1,0,1,0),
			new CoordFour(-1,1,1,0),
			new CoordFour(-1,-1,1,0),
			new CoordFour(0,1,1,0),
			new CoordFour(0,-1,1,0),
			new CoordFour(0,0,1,0),
			*/
	};
	
	public static final CoordFour[] whitePawnMovement = {
			new CoordFour(0,1,0,0),
			new CoordFour(0,0,0,-1)
	};
	
	public static final CoordFour[] whitePawnattack = {
			new CoordFour(1,1,0,0),
			new CoordFour(-1,1,0,0),
			new CoordFour(0,0,1,-1),
			new CoordFour(0,0,-1,-1)
	};
	
	public static final CoordFour[] blackPawnMovement = {
			new CoordFour(0,-1,0,0),
			new CoordFour(0,0,0,1),
	};
	
	public static final CoordFour[] blackPawnattack = {
			new CoordFour(1,-1,0,0),
			new CoordFour(-1,-1,0,0),
			new CoordFour(0,0,1,1),
			new CoordFour(0,0,-1,1)
	};
	
	public static int[] getMovNotation(Piece.PieceType pt) {
		switch(pt) {
		case BISHOP:
			return MoveNotation.BISHOP;
		case ROOK:
			return MoveNotation.ROOK;
		case PAWN:
		case KNIGHT:
		default:
			int[] nullmove = {};
			return nullmove;
		}
	}
	
	/**
	 * Take a piece and turns it into an array of movement vectors,
	 * 
	 * @param piece ordinal integer relating to the piece enum defined in board
	 * @return an array of vectors, or empty array if nothing is found.
	 */
	public static CoordFour[] getMoveVectors(int piece) {
		//@TODO add brawns to this list
		switch(piece) {
		case 1:
			return whitePawnMovement;
		case 1 + 10:
			return blackPawnMovement;
		case 2:
		case 2 + 10:
			return KNIGHTMOVESET;
		case 3:
		case 3 + 10:
			return BISHOPMOVESET;
		case 4:
		case 4 + 10:
			return ROOKMOVESET;
		case 5:
		case 5 + 10:
			return PRINCESSMOVESET;
		case 6:
		case 6 + 10:
			//this case is the queen, but it has the same movement vectors as a king, but is a rider instead. making the queen a "king rider" (that sounds dirty)
			return KINGMOVESET;
		case 7:
		case 7 + 10:
			return KINGMOVESET;
		case 8:
		case 8 + 10:
			return UnicornMoveset;
		case 9:
		case 9 + 10:
			return DragonMoveset;
		case 0:
		default:
			return NULLMOVESET;
		}
	}
	
	//this is depricated, due to the above function.
	public static ArrayList<CoordFour> getMultiMoveset(int[] moveAxes){
		ArrayList<CoordFour> moveset = new ArrayList<CoordFour>();
		for(int i: moveAxes) {
			moveset.addAll(getMoveset(i));
		}
		return moveset;
	}	
	
	
	//this is probably not useful.
	public static ArrayList<CoordFour> getMoveset(int moveAxi){
		ArrayList<CoordFour> moveset = new ArrayList<CoordFour>();
		
		switch(moveAxi)
		{
		case 1:
			for(CoordFour c : ROOKMOVESET) {
				moveset.add(c);				
			}
			break;
		case 2:
			for(CoordFour c : BISHOPMOVESET) {
				moveset.add(c);				
			}
			break;
		case 3:
			for(CoordFour c : UnicornMoveset) {
				moveset.add(c);				
			}
			break;
		case 4:
			for(CoordFour c : DragonMoveset) {
				moveset.add(c);				
			}
			break;
		default:
			break;
		}
		return moveset;
		
		
	}
	
}
