import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class BoardGUI implements java.awt.event.KeyListener{
    private Board board = new Board();
    private Controller controller = new Controller();
    private int score = 0;
    private JLabel[] slots = new JLabel[16];
    private JLabel scoreLabel;
    // private GameState currentState;
    private Leaderboard leaderboard;
    private GameMode gameMode;
    private JLabel timerLabel;
    private JLabel movesLeftLabel;
    private JLabel modeLabel;
    private boolean isGameOver = false;


    public static void main(String[] args) {
		new BoardGUI();
	}

    public BoardGUI() {
        GameModeType selectedMode = chooseGameMode();
        initializeGame(selectedMode);
    }

    public GameModeType chooseGameMode() {
        Object[] options = {"Traditional", "Time Limit", "Move Limit"};
        int selected = JOptionPane.showOptionDialog(null, 
            "Select a Game Mode", 
            "Game Mode Selection", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, options, options[0]);
        
        switch (selected) {
            case 0:
                return GameModeType.TRADITIONAL;
            case 1:
                return GameModeType.TIME_LIMIT;
            case 2:
                return GameModeType.MOVE_LIMIT;
            default:
                return GameModeType.TRADITIONAL;
        }
    }

    public void initializeGame(GameModeType modeType) {
        board = new Board();
        leaderboard = new Leaderboard();

        switch (modeType) {
            case TRADITIONAL:
                gameMode = new TraditionalMode(board);
                break;
            case TIME_LIMIT:
                gameMode = new TimeTrialMode(board);
                break;
            case MOVE_LIMIT:
                gameMode = new MoveLimitMode(board);
                break;
            default:
                gameMode = new TraditionalMode(board);
        }
        board.setType(gameMode);

        setup(modeType);
    }

    

    public void keyTyped(java.awt.event.KeyEvent e){
        int keyChar = e.getKeyChar();
        switch ( keyChar ) {
            case 'w':
                controller.moveBoardUp();
                break;
            case 's':
                controller.moveBoardDown();
                break;
            case 'd':
                controller.moveBoardRight();
                break;
            case 'a':
                controller.moveBoardLeft();
                break;
        }
        updateGrid();
    }

    public void keyReleased(java.awt.event.KeyEvent e ){

    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        // prevent further moves when game is over
        if (isGameOver) {
            return;
        }

        int keyCode = e.getKeyCode();
        switch ( keyCode ) {
            case java.awt.event.KeyEvent.VK_UP:
                controller.moveBoardUp();
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                controller.moveBoardDown();
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                controller.moveBoardRight();
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                controller.moveBoardLeft();
                break;
        }
        updateGrid();

        if (gameMode.isGameOver()) {
            handleGameOver();
        }
    }

    private void updateGrid(){
        Tile[][] curGrid = controller.getGrid();
        int slotNum = 0;
        for(int j = 0; j<=3; j++){
            for( int k = 0; k<=3; k++){
                changeTile(curGrid[j][k], slotNum);
                slotNum++;
            }
        }
        scoreLabel.setText("Current Score: "+Integer.toString(board.getScore()));

        // update mode-specific info
        if (gameMode instanceof MoveLimitMode moveLimitMode) {
            movesLeftLabel.setText("Moves Left: " + moveLimitMode.getMovesLeft());
        }
    }

    private void setup(GameModeType modeType) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,780);
        frame.setLocationRelativeTo(null);

        scoreLabel = new JLabel("Current Score: "+score);
        //scoreLabel.setSize(750, 30);
        modeLabel = new JLabel("Game Mode: " + getModeString(modeType));

        
        // JPanel top = new JPanel();
        JPanel top = new JPanel(new GridLayout(1, 3));

        top.add(scoreLabel);
        top.add(modeLabel);

        // add moves left label for move limit mode
        if (gameMode instanceof MoveLimitMode moveLimitMode) {
            movesLeftLabel = new JLabel("Moves Left: " + moveLimitMode.getMovesLeft());
            top.add(movesLeftLabel);
        }

        // add timer label for time limit mode
        // if (gameMode instanceof TimeTrialMode timeTrialMode) {
        //     top.add(timerLabel);
        // }

        
        JPanel tiles = new JPanel();
        tiles.setSize(750,750);

        GridLayout grid = new GridLayout(4,4);
        tiles.setLayout(grid);

        for(int i = 0; i<=15; i++){
            slots[i] = new JLabel("",SwingConstants.CENTER);
            slots[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            tiles.add(slots[i]);
        }

        updateGrid();

        tiles.addKeyListener(this);

        frame.add(top,"North");
        frame.add(tiles);
        frame.setVisible(true);
        tiles.setFocusable(true);
    }

    public void changeTile(Tile tile, int slotNum){
        if(tile.getValue()==0){
            slots[slotNum].setText("");
        }
        else{
            slots[slotNum].setText(Integer.toString(tile.getValue()));
        }
        slots[slotNum].setFont(new Font("", Font.PLAIN, 60));
        slots[slotNum].setBackground(tile.getColor());
        slots[slotNum].setOpaque(true);
    }

    private String getModeString(GameModeType modeType) {
        switch (modeType) {
            case TRADITIONAL:
                return "Traditional";
            case TIME_LIMIT:
                return "Time Limit";
            case MOVE_LIMIT:
                return "Move Limit";
            default:
                return "Traditional";
        }
    }

    private String getGameOverMessage() {
        return gameMode.getGameOverMessage();
    }

    private void displayLeaderboard() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Leaderboard");
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        StringBuilder sb = new StringBuilder();
        ArrayList<Entry> scores;

        // show all scores or only the top 10
        // let user choose
        Object[] options = {"All Scores", "Top 10"};
        int selected = JOptionPane.showOptionDialog(null, 
            "Select a Leaderboard Display Option", 
            "Leaderboard Display", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, options, options[0]);

        if (selected == 0) {
            sb.append("All Scores:\n");
            scores = leaderboard.getAllScores();
            
        } else {
            sb.append("Top 10 Scores:\n");
            scores = leaderboard.getTopScore();
        }

        for (int i = 0; i < scores.size(); i++) {
            Entry entry = scores.get(i);
            sb.append(i + 1);
            sb.append(". ");
            sb.append(entry);
            sb.append("\n");
        }

        textArea.setText(sb.toString());
        dialog.add(textArea);
        dialog.setVisible(true);
    }

    private void handleGameOver() {
        isGameOver = true;

        int finalScore = board.getScore();

        String gameOverMessage = getGameOverMessage();
        String playerName = JOptionPane.showInputDialog(
            null, 
            gameOverMessage + "\nYour score: " + finalScore + "\nEnter your name: ", 
            "Enter Name", 
            JOptionPane.INFORMATION_MESSAGE);

        if (playerName != null && !playerName.isEmpty()) {
            leaderboard.addScore(playerName, finalScore);

            displayLeaderboard();
        }

        // prevent further moves
        // disable key listener

        // remove focus from the game board

    }
}
