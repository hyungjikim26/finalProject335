import java.util.Scanner;

public class Game {
    private Board board;
    private int score;
    private Leaderboard leaderboard;


    public Game() {
        // it is possible to play the game multiple times
        // which would mean we would get a new bord
        board = new Board();
        score = 0;
    }


    public void start() {
        board = new Board();
        score = 0;
        leaderboard = new Leaderboard();
    }

    /**
     * 
     * Call move methods of the board object and adds 
     */
    public void makeMove(String direction) {
        boolean boardChanged = false;
        switch (direction) {
            case "w":
                boardChanged = board.moveUp();
                break;
            case "s":
                boardChanged = board.moveDown();
                break;
            case "a":
                boardChanged = board.moveLeft();
                break;
            case "d":
                boardChanged = board.moveRight();
                break;
            default:
                System.out.println("Invalid move. Please enter w, a, s, or d.");
                break;
        }

        if (boardChanged) {
            board.addRandomTile();
        }
    }

    public int getScore() {
        return score;
    }


    public void printBoard() {
        System.out.println("Score: " + score);
        System.out.println(board.toString());
    }

    // update leaderboard with given name and score
    public void updateLeaderboard(String name, int score) {
        leaderboard.addScore(name, score);
    }

    public void printLeaderboardAll() {
        for (Entry entry : leaderboard.getAllScores()) {
            System.out.println(entry);
        }
    }

    public void printLeaderboardTop() {
        for (Entry entry : leaderboard.getTopScore()) {
            System.out.println(entry);
        }
    }


    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("                2048                 ");
        System.out.println("=====================================");

        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            game.printBoard();
            System.out.println("Enter a move: (w) up, (s) down, (a) left, (d) right");
            String move = scanner.nextLine().toLowerCase();
            game.makeMove(move);

            // game ending condition
            if (game.board.losingCondition() || game.board.winningCondition()){
                game.printBoard();
                System.out.println("Game over!");

                if (game.board.losingCondition()){
                    System.out.println("You lose!");
                } else{
                    System.out.println("You win!");
                }

                System.out.println("Your score: " + game.getScore());

                // add score to leaderboard
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                game.updateLeaderboard(name, game.getScore());

                // print leaderboard
                System.out.println("Leaderboard:");

                // player can thoose to see all scores or only top 10
                System.out.println("Do you want to see all scores? (y/n)");
                String allScores = scanner.nextLine().toLowerCase();
                if (allScores.equals("y")) {
                    game.printLeaderboardAll();
                } else {
                    game.printLeaderboardTop();
                }

                // player is able to play again
                System.out.println("Do you want to play again? (y/n)");
                String playAgain = scanner.nextLine().toLowerCase();
                if (playAgain.equals("y")) {
                    game.start();
                } else {
                    System.out.println("Goodbye!");
                    scanner.close();
                    break;
                }
            }

        }
    }
	
}