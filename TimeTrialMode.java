/**
 * File: TimeTrialMode.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: Defines the game mode where the player has limited time to play.
 */

import java.util.Timer;
import java.util.TimerTask;

public class TimeTrialMode implements GameMode {
    private static final long TIME_LIMIT = 5000;
    private final Timer timer;
    private final Board board;
    private boolean timeUp;

    public TimeTrialMode(Board board) {
        this.board = board;
        timeUp = false;
        timer = new Timer();
    }

    public void start(TimeListener listener){
        TimerTask task = new TimerTask() {
            long remaining = TIME_LIMIT;

            @Override
            public void run(){
                remaining -= 1000;
                listener.onTimeUpdate(remaining);

                if (remaining <= 0){
                    timeUp = true;
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    /**
     * Determines if the game is over.
     * @return true if the game is over, false otherwise
     */
    @Override
    public boolean isGameOver() {
        return timeUp || board.losingCondition() || board.winningCondition();
    }

    /**
     * Returns the message to display when the game is over.
     * @return the game over message
     */
    @Override
    public String getGameOverMessage() {
        if (board.winningCondition()) {
            return "You win!";
        } else if (timeUp) {
            return "Time is up. You lose!";
        }  else {
            return "You lose!";
        }

    }

    public boolean getTimeUp(){
        return timeUp;
    }
}