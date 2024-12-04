/**
 * File: TraditionalMode.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: Defines the game mode where the player plays the traditional
 * version of the 2048 game without any time or move limits.
 * 
 */

public class TraditionalMode implements GameMode {
    private final Board board;

    /**
     * Constructor
     * @param board the board to play on
     */
    public TraditionalMode(Board board) {
        this.board = board;
    }


    /**
     * Determines if the game is over. Game is over if board has a losing
     * condition or board has a winning condition.
     * @return true if the game is over, false otherwise
     */
    @Override
    public boolean isGameOver() {
        if (board.losingCondition()) {
            return true;
        } else if (board.winningCondition()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Returns the message to display when the game is over.
     * @return the game over message
     */
    @Override
    public String getGameOverMessage() {
        return board.winningCondition() ? "You win!" : "You lose!";
    }
}

