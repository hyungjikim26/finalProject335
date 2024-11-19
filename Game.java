import java.util.Scanner;

public class Game {
    private Board board;
    private int score;


    public Game() {
        // it is possible to play the game multiple times
        // which would mean we would get a new bord
        board = new Board();
        score = 0;
    }


    public void start() {
        board = new Board();
        score = 0;
    }


    public int getScore() {
        return score;
    }


    public void printBoard() {
        System.out.println("Score: " + score);
        System.out.println(board.toString());
    }


    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("                2048                 ");
        System.out.println("=====================================");

        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        
        // TODO: add break condition
        while (true) {
            game.printBoard();
            System.out.println("Enter a move: (w) up, (s) down, (a) left, (d) right");
            String move = scanner.nextLine().toLowerCase();
            if (move.equals("w")) {
                game.board.moveUp();
                // score += game.board.moveUp();
            // } else if (move.equals("s")) {
            //     score += game.board.moveDown();
            // } else if (move.equals("a")) {
            //     score += game.board.moveLeft();
            // } else if (move.equals("d")) {
            //     score += game.board.moveRight();
            } else {
                System.out.println("Invalid move. Please enter w, a, s, or d.");
                continue;
            }
            
            // add a new tile only when the board changes
            if (game.board.hasChanged()) {
                game.board.addRandomTile();
            }

            // TODO: add break condition here?

            // if (game.board.isGameOver()) {
            //     game.printBoard();
            //     System.out.println("Game over!");
            //     break;
            // }

        }
    }
	
}