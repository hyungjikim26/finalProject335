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
        
        while (true) {
            game.printBoard();
            System.out.println("Enter a move: (w) up, (s) down, (a) left, (d) right");
            String move = scanner.nextLine().toLowerCase();
            switch (move) {
                case "w":
                    game.board.moveUp();
                    break;
                case "s":
                    game.board.moveDown();
                    break;
                case "a":
                    game.board.moveLeft();
                    break;
                case "d":
                    game.board.moveRight();
                    break;
                default:
                    System.out.println("Invalid move. Please enter w, a, s, or d.");
                    continue;
            }
            
            // add a new tile only when the board changes
            if (game.board.hasChanged()) {
                game.board.addRandomTile();
            }

            // game ending condition
            if (game.board.losingCondition() || game.board.winningCondition()){
                game.printBoard();
                System.out.println("Game over!");

                if (game.board.losingCondition()){
                    System.out.println("You lose!");
                } else{
                    System.out.println("You win!");
                }

                break;
            }

        }
    }
	
}