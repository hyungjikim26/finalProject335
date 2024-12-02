import java.awt.*;
import javax.swing.*;

public class BoardGUI implements java.awt.event.KeyListener{
    private Board board = new Board();
    private int score = 0;
    private JLabel[] slots = new JLabel[16];
    private JLabel scoreLabel;
    // private GameState currentState;
    private Leaderboard leaderboard;
    private GameMode gameMode;
    private JLabel timerLabel;
    private JLabel movesLeftLabel;
    private JLabel modeLabel;

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

        setup(modeType);
    }

    

    public void keyTyped(java.awt.event.KeyEvent e){
        int keyChar = e.getKeyChar();
        boolean boardChanged = false;
        switch ( keyChar ) {
            case 'w':
                boardChanged = board.moveUp();
                break;
            case 's':
                boardChanged = board.moveDown();
                break;
            case 'd':
                boardChanged = board.moveRight();
                break;
            case 'a':
                boardChanged = board.moveLeft();
                break;
        }
        if (boardChanged) {
            board.addRandomTile();
            // currentState = board.checkState();
        }
        updateGrid();
    }

    public void keyReleased(java.awt.event.KeyEvent e ){

    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        int keyCode = e.getKeyCode();
        boolean boardChanged = false;
        switch ( keyCode ) {
            case java.awt.event.KeyEvent.VK_UP:
                boardChanged = board.moveUp();
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                boardChanged = board.moveDown();
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                boardChanged = board.moveRight();
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                boardChanged = board.moveLeft();
                break;
        }
        if (boardChanged) {
            board.addRandomTile();
            // update moves left for move limit mode
            if (gameMode instanceof MoveLimitMode) {
                gameMode.updateGameState();
            }
            // currentState = board.checkState();
        }
        updateGrid();

    }

    private void updateGrid(){
        Tile[][] curGrid = board.getGrid();
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
            movesLeftLabel.setText("Moves Left: " + moveLimitMode.movesLeft);
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
            movesLeftLabel = new JLabel("Moves Left: " + moveLimitMode.movesLeft);
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
            for (Entry entry: leaderboard.getAllScores()) {
                sb.append(entry);
            }
        } else {
            sb.append("Top 10 Scores:\n");
            for (Entry entry: leaderboard.getTopScore()) {
                sb.append(entry);
            }
        }

        textArea.setText(sb.toString());
        dialog.add(textArea);
        dialog.setVisible(true);        
    }
}
