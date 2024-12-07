/**
 * File: MoveLimitMode.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: Defines the game mode where the player has limited number of moves.
 */

public class MoveLimitMode extends Board {
    private static final int MOVE_LIMIT = 125;
    private int movesLeft = MOVE_LIMIT;

    /**
     * Constructor
     * @param board the board to play on
     */
    public MoveLimitMode() {
        super();
    }
    
    public MoveLimitMode(int size, int numTiles) {
        super(size, numTiles);
    }


    /**
     * Determines if the game is over. Game is over if there is no more moves
     * left, board has a losing condition, or board has a winning condition.
     * @return true if the game is over, false otherwise
     */
    @Override
    public boolean isGameOver() {
        if (movesLeft == 0) {
            return true;
        } else if (this.losingCondition()) {
            return true;
        } else if (this.winningCondition()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void updateGateState() {
    	movesLeft--;
    }
    
    @Override
    public boolean moveUp() {
    	boolean isChanged = super.moveUp();
    	if (isChanged) {
    		updateGateState();
    	}
    	return isChanged;
    }
    
    @Override
    public boolean moveRight() {
    	boolean isChanged = super.moveRight();
    	if (isChanged) {
    		updateGateState();
    	}
    	return isChanged;
    }
    
    @Override
    public boolean moveDown() {
    	boolean isChanged = super.moveDown();
    	if (isChanged) {
    		updateGateState();
    	}
    	return isChanged;
    }
    
    @Override
    public boolean moveLeft() {
    	boolean isChanged = super.moveLeft();
    	if (isChanged) {
    		updateGateState();
    	}
    	return isChanged;
    }
    


    /**
     * Returns the number of moves left in the current game.
     * @pre movesLeft >= 0
     * @return the number of moves left
     */
    public int getMovesLeft() {
        return movesLeft;
    }
    
    public int getMoveLimit() {
        return MOVE_LIMIT;
    }

    /**
     * Returns the message to display when the game is over.
     * @return the game over message
     */
    @Override
    public String getGameOverMessage() {
        if (this.winningCondition()) {
            return "You win!";
        } else if (movesLeft == 0) {
            return "No moves left. You lose!";
        }  else {
            return "You lose!";
        }
    }
}

