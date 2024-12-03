public class MoveLimitMode implements GameMode {
    private static final int MOVE_LIMIT = 125;
    private int movesLeft = MOVE_LIMIT;
    private final Board board;

    public MoveLimitMode(Board board) {
        this.board = board;
    }

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

    // @Override
    // public void updateGameState() {
    //     movesLeft--;
    // }

    public void updateGateState() {
        movesLeft--;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    // @Override
    // public void updateLeaderboard() {
    // }

    @Override
    public String getGameOverMessage() {
        if (board.winningCondition()) {
            return "You win!";
        } else if (movesLeft == 0) {
            return "No moves left. You lose!";
        }  else {
            return "You lose!";
        }

        // return movesLeft == 0 ? "No moves left!" : board.winningCondition() ? "You win!" : "You lose!";
    }
}