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

    @Override
    public boolean isGameOver() {
        return timeUp || board.losingCondition() || board.winningCondition();
    }

    // may or may not need this
    // @Override
    // public void updateGameState() {
    //     long currentTime = System.currentTimeMillis();
    //     elapsedTime = currentTime - startTime;    
    // }


    // @Override
    // public void updateLeaderboard() {

    // }

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