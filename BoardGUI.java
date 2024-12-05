/**
 * File: BoardGUI.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose:
 * 
 */

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class BoardGUI implements java.awt.event.KeyListener {
    private Controller controller = new Controller();
    private int score = 0;
    private JLabel[] slots = new JLabel[16];
    private JLabel scoreLabel;
    private CardLayout layout;
    private JFrame frame;
    private JPanel cardPanel;
    // private GameState currentState;
    private Leaderboard leaderboard;
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
        Object[] options = { "Traditional", "Time Limit", "Move Limit" };
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
        leaderboard = new Leaderboard();

        controller.newGame(modeType);

        setup(modeType);
    }

    public void keyTyped(java.awt.event.KeyEvent e) {
        int keyChar = e.getKeyChar();
        switch (keyChar) {
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
        
        if (controller.isGameOver()) {
            handleGameOver();
        }
    }

    public void keyReleased(java.awt.event.KeyEvent e) {

    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        // prevent further moves when game is over
        if (isGameOver) {
            return;
        }

        int keyCode = e.getKeyCode();
        switch (keyCode) {
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

        if (controller.isGameOver()) {
            handleGameOver();
        }
    }

    private void updateGrid() {
        Tile[][] curGrid = controller.getGrid();
        int slotNum = 0;
        for (int j = 0; j <= 3; j++) {
            for (int k = 0; k <= 3; k++) {
                changeTile(curGrid[j][k], slotNum);
                slotNum++;
            }
        }
        scoreLabel.setText("Current Score: " + Integer.toString(controller.getScore()));

        // update mode-specific info
        if (controller.getGameMode() == GameModeType.MOVE_LIMIT) {
            movesLeftLabel.setText("Moves Left: " + controller.getMovesLeft());
        }
    }

    private void setup(GameModeType modeType) {
        frame = new JFrame("2048");
        cardPanel = new JPanel();
        layout = new CardLayout();
        cardPanel.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 780);
        frame.setLocationRelativeTo(null);

        Font font = new Font("Clear Sans", Font.BOLD, 12);

        scoreLabel = new JLabel("Current Score: " + score);
        scoreLabel.setFont(font);
        // scoreLabel.setSize(750, 30);
        modeLabel = new JLabel("Game Mode: " + getModeString(modeType));
        modeLabel.setFont(font);

        // JPanel top = new JPanel();
        JPanel top = new JPanel(new GridLayout(1, 3));

        top.add(scoreLabel);
        top.add(modeLabel);

        // add moves left label for move limit mode
        if (controller.getGameMode() == GameModeType.MOVE_LIMIT) {
            movesLeftLabel = new JLabel("Moves Left: " + controller.getMovesLeft());
            movesLeftLabel.setFont(font);
            top.add(movesLeftLabel);
        }

        // add timer label for time limit mode
        if (controller.getGameMode() == GameModeType.TIME_LIMIT) {
            timerLabel = new JLabel("Time Remaining: 120 s");
            timerLabel.setFont(font);
            
            controller.startTimer(new TimeListener() {
                @Override
                public void onTimeUpdate(long remainingTime) {
                    SwingUtilities.invokeLater(() -> {
                        timerLabel.setText("Time Remaining: " + remainingTime / 1000 + " s");
                        isGameOver = controller.getTimeUp();
                        if (isGameOver){
                            handleGameOver();
                        }
                    });
                }
            });

            top.add(timerLabel);
        }

        JPanel tiles = new JPanel();
        tiles.setSize(750, 750);
        
        tiles.setBackground(new Color(0xbbada0));

        GridLayout grid = new GridLayout(4, 4);
        grid.setHgap(10);
        grid.setVgap(10);
        tiles.setLayout(grid);

        // add padding to tiles
        tiles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i <= 15; i++) {
            JPanel tilePanel = new RoundedPanel(20);
            tilePanel.setOpaque(false);

            JLabel tileLabel = new JLabel("", SwingConstants.CENTER);
            tileLabel.setFont(new Font("Clear Sans", Font.BOLD, 60));
            tileLabel.setOpaque(true);

            tilePanel.setLayout(new GridBagLayout());
            tilePanel.add(tileLabel);

            slots[i] = tileLabel;
            tiles.add(tilePanel);
        }

        updateGrid();

        tiles.addKeyListener(this);

        JSplitPane split = new JSplitPane(SwingConstants.HORIZONTAL, top, tiles);

        cardPanel.add(split);
        cardPanel.setVisible(true);
        frame.add(cardPanel);
        tiles.setFocusable(true);
        frame.setVisible(true);
    }

    public void changeTile(Tile tile, int slotNum) {
        JLabel slot = slots[slotNum];
        JPanel tilePanel = (JPanel) slot.getParent();
        if (tile.getValue() == 0) {
            slot.setText("");
            tilePanel.setBackground(new Color(0xcdc1b4));           
        } else {
            slot.setText(Integer.toString(tile.getValue()));
            tilePanel.setBackground(tile.getColor());

            // change font color for dark tiles
            if (tile.getValue() <= 8) {
                slot.setForeground(Color.BLACK);
            } else {
                slot.setForeground(Color.WHITE);
            }
        }
        slot.setBackground(tile.getColor());
        slot.setOpaque(true);
        // slot.setBorder(BorderFactory.createCompoundBorder(
        //     BorderFactory.createLineBorder(Color.WHITE),
        //     BorderFactory.createEmptyBorder(10, 10, 10, 10)
        // ));

        // add round border
        // slot.setBorder(new RoundBorder(20));

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
        return controller.getGameOverMessage();
    }

    private void displayLeaderboard() {
        JPanel leaders = new JPanel();
        
        //JDialog dialog = new JDialog();
        //dialog.setTitle("Leaderboard");
        //dialog.setSize(300, 300);
        //dialog.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        StringBuilder sb = new StringBuilder();
        ArrayList<Entry> scores;

        // show all scores or only the top 10
        // let user choose
        Object[] options = { "All Scores", "Top 10" };
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
        leaders.add(textArea);
        leaders.setVisible(true);
        cardPanel.add(leaders);
        layout.next(cardPanel);
    }

    private void handleGameOver() {
        isGameOver = true;

        int finalScore = controller.getScore();

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

    // additional class for round border
    class RoundBorder implements Border {
        private int radius;

        RoundBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(5, 5, 5, 5);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            // transparent border
            g.setColor(Color.WHITE);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}

public class RoundedPanel extends JPanel {

    private int cornerRadius = 20;

    public RoundedPanel(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2.dispose();
    }
}
