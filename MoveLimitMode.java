public class MoveLimitMode implements GameMode {
    private static final int MOVE_LIMIT = 5;
    public int movesLeft = MOVE_LIMIT;
    private final Board board;

    public MoveLimitMode(Board board) {
        this.board = board;
    }

    @Override
    public boolean isGameOver() {
        return movesLeft == 0 || board.losingCondition() || board.winningCondition();
    }

    @Override
    public void updateGameState() {
        movesLeft--;
    }

    @Override
    public void updateLeaderboard() {
    }

    @Override
    public String getGameOverMessage() {
        return movesLeft == 0 ? "No moves left!" : board.winningCondition() ? "You win!" : "You lose!";
    }
}