public interface GameMode {
    boolean isGameOver();
    void updateGameState();
    // void updateLeaderboard();
    String getGameOverMessage();
}
