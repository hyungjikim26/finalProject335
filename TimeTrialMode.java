public class TimeTrialMode implements GameMode {
    private static final long TIME_LIMIT = 12000;
    private final long startTime;
    private long elapsedTime;
    private final Board board;

    public TimeTrialMode(Board board) {
        this.board = board;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public boolean isGameOver() {
        return elapsedTime >= TIME_LIMIT || board.losingCondition() || board.winningCondition();
    }

    @Override
    public void updateGameState() {
        long currentTime = System.currentTimeMillis();
        elapsedTime = currentTime - startTime;    
    }

    @Override
    public void updateLeaderboard() {

    }

    @Override
    public String getGameOverMessage() {
        return elapsedTime >= TIME_LIMIT ? "Time's up!" : board.winningCondition() ? "You win!" : "You lose!";
    }
}