import java.util.ArrayList;

public class Controller {
	
	private GameMode gameMode;
	private Board board;
	private Leaderboard leaderboard;
	
	/**
	 * Default constructor for controller class
	 */
	public Controller() {
		gameMode = null;
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
		board = new Board();
		switch (mode) {
		case TRADITIONAL:
			gameMode = new TraditionalMode(board);
			break;
		case TIME_LIMIT:
			gameMode = new TimeTrialMode(board);
			break;
		case MOVE_LIMIT:
			gameMode = new MoveLimitMode(board);
			break;
		}
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
		if (gameMode instanceof MoveLimitMode moveLimitMode) {
			moveLimitMode.updateGateState();
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
		if (gameMode instanceof MoveLimitMode moveLimitMode) {
			moveLimitMode.updateGateState();
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
		if (gameMode instanceof MoveLimitMode moveLimitMode) {
			moveLimitMode.updateGateState();
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
		if (gameMode instanceof MoveLimitMode moveLimitMode) {
			moveLimitMode.updateGateState();
		}
	}
	
	public boolean isGameOver() {
		return gameMode.isGameOver();
	}
	
	public String getGameOverMessage() {
		return gameMode.getGameOverMessage();
	}
	
	public GameModeType getGameMode() {
		if (gameMode instanceof TimeTrialMode timeTrialMode ) {
			return GameModeType.TIME_LIMIT;
		}
		if (gameMode instanceof MoveLimitMode moveLimitMode) {
			return GameModeType.MOVE_LIMIT;
		}
		return GameModeType.TRADITIONAL;
	}
	
	public int getMovesLeft() {
		if (gameMode instanceof MoveLimitMode moveLimitMode) {
			return moveLimitMode.getMovesLeft();
		}
		return -1;
	}
	
	public void startTimer(TimeListener listener) {
		if (gameMode instanceof TimeTrialMode timeTrialMode ) {
			timeTrialMode.start(listener);
		}
	}
	
	public boolean getTimeUp() {
		if (gameMode instanceof TimeTrialMode timeTrialMode ) {
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
	 * @param mode - the GameMode that was selected
	 */
	public void setMode(GameMode mode) {
		board.setType(mode);
	}

	public GameMode newTraditional() {
		return new TraditionalMode(board);
	}

	public GameMode newTime() {
		return new TimeTrialMode(board);
	}

	public GameMode newMove() {
		return new MoveLimitMode(board);
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
