public class TraditionalMode implements GameMode {
    private final Board board;

    public TraditionalMode(Board board) {
        this.board = board;
    }

    @Override
    public boolean isGameOver() {
        return board.losingCondition() || board.winningCondition();
    }

    // @Override
    // public void updateGameState() {
    //     // no states to update in traditional mode
    // }

    // @Override
    // public void updateLeaderboard() {
    // }

    @Override
    public String getGameOverMessage() {
        return board.winningCondition() ? "You win!" : "You lose!";
    }
}