/**
 * File: MoveLimitMode.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: Defines the game mode where the player has limited number of moves.
 */

public class MoveLimitMode implements GameMode {
    private static final int MOVE_LIMIT = 125;
    private int movesLeft = MOVE_LIMIT;
    private final Board board;

    /**
     * Constructor
     * @param board the board to play on
     */
    public MoveLimitMode(Board board) {
        this.board = board;
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
        } else if (board.losingCondition()) {
            return true;
        } else if (board.winningCondition()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Updates the gate state by decrementing the number of moves left.
     * @pre movesLeft > 0
     * @post movesLeft is decremented by 1
     */
    public void updateGateState() {
        movesLeft--;
    }


    /**
     * Returns the number of moves left in the current game.
     * @pre movesLeft >= 0
     * @return the number of moves left
     */
    public int getMovesLeft() {
        return movesLeft;
    }


    /**
     * Returns the message to display when the game is over.
     * @return the game over message
     */
    @Override
    public String getGameOverMessage() {
        if (board.winningCondition()) {
            return "You win!";
        } else if (movesLeft == 0) {
            return "No moves left. You lose!";
        }  else {
            return "You lose!";
        }
    }
}

