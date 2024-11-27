import java.util.ArrayList;

public class Controller {
	
	private Board board;
	private Leaderboard leaderboard;
	
	/**
	 * Default constructor for controller class
	 */
	public Controller() {
		board = new Board();
		leaderboard = new Leaderboard();
	}
	
	/**
	 * Constructor for the Board class.
	 * 
	 * @pre numTiles >= 0 && numTiles <= size*size && size > 0
	 * @post a new Controller object is created with Board object being an instance
	 * @param size     the size of the board
	 * @param numTiles the number of tiles to place on the board
	 */
	public Controller(int size, int numTiles) {
		board = new Board(size, numTiles);
	}
	
	/**
	 * Move all tiles in the board up
	 * 
	 * If the method produced change in board object, add a new tiles
	 */
	public void moveBoardUp() {
		boolean isChanged = board.moveUp();
		if (isChanged) {
			board.addRandomTile();			
		}
	}
	
	/**
	 * Move all tiles in the board right
	 * 
	 * If the method produced change in board object, add a new tiles
	 */
	public void moveBoardRight() {
		boolean isChanged = board.moveRight();
		if (isChanged) {
			board.addRandomTile();			
		}
	}
	
	/**
	 * Move all tiles in the board down
	 * 
	 * If the method produced change in board object, add a new tiles
	 */
	public void moveBoardDown() {
		boolean isChanged = board.moveDown();
		if (isChanged) {
			board.addRandomTile();			
		}
	}
	
	/**
	 * Move all tiles in the board left
	 * 
	 * If the method produced change in board object, add a new tiles
	 */
	public void moveBoardLeft() {
		boolean isChanged = board.moveLeft();
		if (isChanged) {
			board.addRandomTile();			
		}
	}
	
	/**
	 * @return true if the player won (there is 2048 somewhere on board), false otherwise
	 */
	public boolean won() {
		return board.winningCondition();
	}
	
	/**
	 * @return true if the player lost can't make any move, false otherwise
	 */
	public boolean lost() {
		return board.losingCondition();
	}
	
	/**
	 * @return current board score
	 */
	public int getScore() {
		return board.getScore();
	}
	/**
	 * @param name - name of person playing
	 * @param score - score of the board after finishing game
	 */
	public void addScore(String name, int score) {
		leaderboard.addScore(name, score);
	}
	
	/**
	 * @return get 10 (by default) top scores
	 */
	public ArrayList<Entry> getTopScores() {
		return leaderboard.getTopScore();
	}
	
	/**
	 * @return get all resulting scores
	 */
	public ArrayList<Entry> getAllScores() {
		return leaderboard.getAllScores();
	}
}
