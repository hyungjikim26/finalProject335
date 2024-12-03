public class TraditionalMode implements GameMode {
    private final Board board;

    public TraditionalMode(Board board) {
        this.board = board;
    }

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

    // @Override
    // public void updateLeaderboard() {
    //     // no states to update in traditional mode
    // }

    @Override
    public String getGameOverMessage() {
        return board.winningCondition() ? "You win!" : "You lose!";
    }
}