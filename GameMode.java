/**
 * File: GameMode.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: Defines interface for game modes.
 * 
 */

public interface GameMode {
    /**
     * Determines if the game is over.
     * @return true if the game is over, false otherwise
     */
    boolean isGameOver();

    /**
     * Returns the message to display when the game is over.
     * @return the game over message
     */
    String getGameOverMessage();
}

