/**
 * File: TimeTrialMode.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: Defines the game mode where the player has limited time to play.
 */

import java.util.Timer;
import java.util.TimerTask;

public class TimeTrialMode extends Board {
    private static final long TIME_LIMIT = 120000;
    private final Timer timer;
    private boolean timeUp;

    /**
     * Default constructor for TimeTrialMode.
     */
    public TimeTrialMode() {
    	super();
        timeUp = false;
        timer = new Timer();
    }
     
    /**
     * Constructor for TimeTrialMode with specified board size and number of tiles.
     * @param size     the size of the board
     * @param numTiles the initial number of tiles
     */
    public TimeTrialMode(int size, int numTiles) {
        super(size, numTiles);
        timeUp = false;
        timer = new Timer();
    }

    /**
     * Starts the timer for the Time Trial mode.
     * The timer updates the remaining time every second and notifies the listener.
     * When time runs out or the game is over, the timer is stopped.
     * @param listener a TimeListener object to be notified of time updates
     */
    public void start(TimeListener listener){
        TimerTask task = new TimerTask() {
            long remaining = TIME_LIMIT;

            @Override
            public void run(){
                remaining -= 1000;
                listener.onTimeUpdate(remaining);

                if (isGameOver()) {
                    timer.cancel();
                    timer.purge();
                }
                if (remaining <= 0){
                    timeUp = true;
                    timer.cancel();
                    timer.purge();
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
        return timeUp || super.isGameOver();
    }

    /**
     * Returns the message to display when the game is over.
     * @return the game over message
     */
    @Override
    public String getGameOverMessage() {
        if (this.winningCondition()) {
            return "You win!";
        } else if (timeUp) {
            return "Time is up. You lose!";
        }  else {
            return "You lose!";
        }

    }

    /**
     * Checks whether the time limit has been reached.
     * @return true if the time is up, false otherwise
     */
    public boolean getTimeUp(){
        return timeUp;
    }
}