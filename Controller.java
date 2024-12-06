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
	
	/**
	 * Creates a new board of of specified game mode
	 * 
	 * @param mode - game mode of current game
	 */
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
	
	/**
	 * Creates a new controller with board of specified game mode
	 * 
	 * @param mode - game mode of current game
	 */
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
	
	/**
	 * @return true if game is finished (won or lost), false if not (still playing)
	 */
	public boolean isGameOver() {
		return board.isGameOver();
	}
	
	/**
	 * @pre board.isGameOver() == true
	 * @post the returned message shows appropriate win or loss message
	 * @return message which is displayed when the game is over
	 */
	public String getGameOverMessage() {
		return board.getGameOverMessage();
	}
	
	/**
	 * @return mode of the current game 
	 */
	public GameModeType getGameMode() {
		if (board instanceof TimeTrialMode timeTrialMode ) {
			return GameModeType.TIME_LIMIT;
		}
		if (board instanceof MoveLimitMode moveLimitMode) {
			return GameModeType.MOVE_LIMIT;
		}
		return GameModeType.TRADITIONAL;
	}
	
	/**
	 * @return if game mode is MOVE_LIMIT, return amount of moves left
	 *         otherwise return -1
	 */
	public int getMovesLeft() {
		if (board instanceof MoveLimitMode moveLimitMode) {
			return moveLimitMode.getMovesLeft();
		}
		return -1;
	}
	
	/**
	 * If game mode is TIME_LIMIT, it starts a timer in class TimeTrialMode.
	 * Otherwise nothing happens.
	 * 
	 * @param listener - used to keep track of time in UI
	 */
	public void startTimer(TimeListener listener) {
		if (board instanceof TimeTrialMode timeTrialMode ) {
			timeTrialMode.start(listener);
		}
	}
	
	/**
	 * @return if game mode is TIME_LOMIT, return true if timer is up, false if not.
	 *         if game mode is not TIME_LIMIT, return false
	 */
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
	
	/**
	 * Adds a tile of specific value to a specific location of board instance
	 * 
	 * @param row    - row location to which to add a tile
	 * @param column - column location to which to add a tile
	 * @param value  - value of the Tile which we want to add
	 */
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

