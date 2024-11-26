import java.util.Scanner;

public class Game {
    private Board board;
    private int score;
    private Leaderboard leaderboard;
    private GameState currentState;


    public Game() {
        // it is possible to play the game multiple times
        // which would mean we would get a new bord
        board = new Board();
        score = 0;
        currentState = GameState.CONTINUE;
        leaderboard = new Leaderboard();
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
        // check got early exit
        if (currentState != GameState.CONTINUE) {
            return;
        }

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
            case "n":
                start();
                break;
            case "q":
                currentState = GameState.QUIT;
                break;
            default:
                System.out.println("Invalid move.");
                break;
        }

        if (boardChanged) {
            board.addRandomTile();
            currentState = board.checkState();
        }
    }

    public GameState getGameState() {
        return currentState;
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

    private void handleEnd(Game game, Scanner scanner) {
        System.out.println("Your score: " + game.getScore());

        // prompt user for name and add score to leaderboard
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        game.updateLeaderboard(name, game.getScore());

        // show leaderboard display options
        System.out.println("Leaderboard:");
        System.out.println("Show (a)ll scores or only the (t)op 10?");
        String choice = scanner.nextLine().toLowerCase();

        switch (choice) {
            case "a":
                game.printLeaderboardAll();
                break;
            case "t":
                game.printLeaderboardTop();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }

        // prompt user to play again
        System.out.println("Do you want to play again? (y/n)");
        String playAgain = scanner.nextLine().toLowerCase();
        switch (playAgain) {
            case "y":
                game.start();
                break;
            case "n":
                System.out.println("Goodbye!");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice.");
                System.exit(0);
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
            System.out.println("Enter a move: (w) up, (s) down, (a) left, (d) right, (n) new game (q) quit");
            String move = scanner.nextLine().toLowerCase();
            game.makeMove(move);

            switch (game.currentState) {
                case WIN:
                    System.out.println("You win!");
                    game.handleEnd(game, scanner);
                    break;
                case LOSE:
                    System.out.println("You lose!");
                    game.handleEnd(game, scanner);
                    break;
                case QUIT:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
	
}