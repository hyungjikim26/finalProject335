/**
 * File: Controller.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: Defines the controller class that handles the game logic.
 */

import java.util.ArrayList;

public class Controller {
	
	private Board board;
	private Leaderboard leaderboard;
	
	/**
	 * Default constructor for controller class
	 */
	public Controller() {
		board = null;
		leaderboard = new Leaderboard();
		newGame(GameModeType.TRADITIONAL);
	}
	
	/** TODO: DELETE
	 * Constructor for the Board class.
	 * 
	 * @pre numTiles >= 0 && numTiles <= size*size && size > 0
	 * @post a new Controller object is created with Board object being an instance
	 * @param size     the size of the board
	 * @param numTiles the number of tiles to place on the board
	 */
	// Constructor was here
	
	
	public void newGame(GameModeType mode) {
		switch (mode) {
		case TRADITIONAL:
			board = new Board();
			break;
		case TIME_LIMIT:
			board = new TimeTrialMode();
			break;
		case MOVE_LIMIT:
			board = new MoveLimitMode();
			break;
		}
	}
	
	public Controller(GameModeType mode) {
		board = null;
		leaderboard = new Leaderboard();
		newGame(mode);
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
	
	public boolean isGameOver() {
		return board.isGameOver();
	}
	
	public boolean won() {
		return board.winningCondition();
	}
	public boolean lost() {
		return board.losingCondition();
	}
	
	public String getGameOverMessage() {
		return board.getGameOverMessage();
	}
	
	public GameModeType getGameMode() {
		if (board instanceof TimeTrialMode timeTrialMode ) {
			return GameModeType.TIME_LIMIT;
		}
		if (board instanceof MoveLimitMode moveLimitMode) {
			return GameModeType.MOVE_LIMIT;
		}
		return GameModeType.TRADITIONAL;
	}
	
	public int getMovesLeft() {
		if (board instanceof MoveLimitMode moveLimitMode) {
			return moveLimitMode.getMovesLeft();
		}
		return -1;
	}
	
	public void startTimer(TimeListener listener) {
		if (board instanceof TimeTrialMode timeTrialMode ) {
			timeTrialMode.start(listener);
		}
	}
	
	public boolean getTimeUp() {
		if (board instanceof TimeTrialMode timeTrialMode ) {
			return timeTrialMode.getTimeUp();
		}
		return false;
	}
	
	/**
	 * @return current board score
	 */
	public int getScore() {
		return board.getScore();
	}

	/**
	 * @return current grid
	 */
	public Tile[][] getGrid() {
		return board.getGrid();
	}
	
	public void addTile(int row, int column, int value) {
		board.addTile(row, column, value);
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

