/**
 * File: BoardGUI.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose:
 * 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private ColorScheme colorScheme = ColorScheme.RED;
    private JPanel currentScorePanel = null;
    private boolean scoreEffectActive = false;
    private JButton scoreEffectButton;
    private JPanel tiles;
    private GameModeType selected;


    public static void main(String[] args) {
        new BoardGUI();
    }

    public BoardGUI() {
        GameModeType selectedMode = chooseGameMode();
        //initializeGame(selectedMode);
    }

    private GameModeType getGameMode(GameModeType mode){
        return mode;
    }

    public GameModeType chooseGameMode() {
        frame = new JFrame("2048");
        cardPanel = new JPanel();
        layout = new CardLayout();
        cardPanel.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 780);
        frame.setLocationRelativeTo(null);
        frame.add(cardPanel);

        JPanel main = new JPanel(new BorderLayout());

        JPanel chooseMode = new JPanel(new BorderLayout());
        Font labelFont = new Font("Helvetica", Font.BOLD, 180);
        JLabel title = new JLabel("2048",SwingConstants.CENTER);
        title.setForeground(new Color(0xF7603B));
        title.setFont(labelFont);
        JLabel message = new JLabel("Select a Game Mode", SwingConstants.CENTER);
        Font messageFont = new Font("Helvetica", Font.BOLD, 25);
        message.setFont(messageFont);
        

        JPanel buttons = new JPanel(new FlowLayout());
        JButton traditional = new JButton("Traditional");
        JButton time = new JButton("Time");
        JButton move = new JButton("Move Limit");
        chooseMode.add(title, BorderLayout.CENTER);
        chooseMode.add(message, BorderLayout.SOUTH);
        chooseMode.setBorder(BorderFactory.createEmptyBorder(140,0,10,0));

        buttons.add(traditional, BorderLayout.WEST);
        buttons.add(time,BorderLayout.CENTER);
        buttons.add(move,BorderLayout.EAST);

        main.add(chooseMode, BorderLayout.NORTH);
        main.add(buttons, BorderLayout.CENTER);
        cardPanel.add(main);

        traditional.addActionListener(e ->{
            selected = GameModeType.TRADITIONAL;
            initializeGame(GameModeType.TRADITIONAL);
            layout.next(cardPanel);
        });

        time.addActionListener(e ->{
            selected = GameModeType.TIME_LIMIT;
            initializeGame(GameModeType.TIME_LIMIT);
            layout.next(cardPanel);
        });

        move.addActionListener(e ->{
            selected = GameModeType.MOVE_LIMIT;
            initializeGame(GameModeType.MOVE_LIMIT);
            layout.next(cardPanel);
        });
        frame.setVisible(true);
        return selected;
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
        if (controller.isGameOver()) {
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

        int newScore = controller.getScore();
		if (newScore > score) {
			updateScoreEffect(newScore - score);
			score = newScore;
		}
    }

    	/**
	 * Displays a score effect on the game screen.
	 * @param pointsGained the number of points gained
	 */
	private void updateScoreEffect(int pointsGained) {
        if (!scoreEffectActive) {
            return;
        }
	    // remove previous effect
	    if (currentScorePanel != null) {
	        frame.getLayeredPane().remove(currentScorePanel);
	        frame.getLayeredPane().revalidate();
	        frame.getLayeredPane().repaint();
	    }
	
	    JLabel scoreEffect = new JLabel("+" + pointsGained, SwingConstants.CENTER);
	    scoreEffect.setFont(new Font("Clear Sans", Font.BOLD, 60));
	    scoreEffect.setForeground(new Color(119, 110, 101, 200));
	    scoreEffect.setBounds(0, 0, 300, 100);

	    JPanel effectPanel = new JPanel(null);
	    effectPanel.setOpaque(false);
	    effectPanel.setBounds(250, 300, 300, 100);
	    effectPanel.add(scoreEffect);

	    currentScorePanel = effectPanel;

	    frame.getLayeredPane().add(effectPanel, JLayeredPane.POPUP_LAYER);

	    TimeListener timeListener = new TimeListener() {
	        int transparency = 200;
	        long elapsedTime = 0;

	        @Override
	        public boolean onTimeUpdate(long timeDelta) {
	            elapsedTime += timeDelta; 
	            if (elapsedTime >= 100) { 
	                transparency -= 40; 
	                if (transparency < 0) transparency = 0; 
	                scoreEffect.setForeground(new Color(119, 110, 101, transparency)); 
	                elapsedTime = 0; 
	            }
	            return false;
	        }
	    };

	    Timer timer = new Timer(30, (ActionListener) new ActionListener() {
	        long lastUpdateTime = System.currentTimeMillis();

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            long currentTime = System.currentTimeMillis();
	            long timeDelta = currentTime - lastUpdateTime; 
	            lastUpdateTime = currentTime;
	            timeListener.onTimeUpdate(timeDelta); 

	            if (scoreEffect.getForeground().getAlpha() <= 0) {
	                ((Timer) e.getSource()).stop();
	                frame.getLayeredPane().remove(effectPanel);
	                frame.getLayeredPane().revalidate();
	                frame.getLayeredPane().repaint();
	                if (currentScorePanel == effectPanel) {
	                    currentScorePanel = null;
	                }
	            }
	        }
	    });
	    timer.start();
	}

    private void setup(GameModeType modeType) {


        Font font = new Font("Clear Sans", Font.BOLD, 12);

        scoreLabel = new JLabel("Current Score: " + score);
        scoreLabel.setFont(font);
        // scoreLabel.setSize(750, 30);
        modeLabel = new JLabel("Mode: " + getModeString(modeType));
        modeLabel.setFont(font);

        // JPanel top = new JPanel();
        JPanel top = new JPanel(new GridLayout(1, 6));

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
            timerLabel = new JLabel("Time Remaining: 120s");
            timerLabel.setFont(font);
            
            controller.startTimer(new TimeListener() {
                @Override
                public boolean onTimeUpdate(long remainingTime) {
                    SwingUtilities.invokeLater(() -> {
                    	if (controller.won() || controller.lost()) {
                    		return;
                    	}
                        timerLabel.setText("Time Remaining: " + remainingTime / 1000 + "s");
                        if (controller.isGameOver()){
                        	handleGameOver();
                        }
                    });
                    return controller.won() || controller.lost();
                }
            });

            top.add(timerLabel);
        }

        // JPanel tiles = new JPanel();
        tiles = new JPanel();
        tiles.setSize(750, 750);
        
        tiles.setBackground(colorScheme == ColorScheme.BLUE ? new Color(0xD0D9E8) : new Color(0xbbada0));

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

        JButton colorSchemeButton = new JButton("Change Color");
        colorSchemeButton.setFont(font);
        colorSchemeButton.addActionListener(e -> switchColorScheme());
        top.add(colorSchemeButton);

        scoreEffectButton = new JButton("Score Effect: " + (scoreEffectActive ? "ON" : "OFF"));
        scoreEffectButton.setFont(font);
        scoreEffectButton.addActionListener(e -> toggleScoreEffect());
        top.add(scoreEffectButton);

        updateGrid();

        tiles.addKeyListener(this);
        frame.addKeyListener(this);

        tiles.setFocusable(true);
        frame.setFocusable(true);

        tiles.requestFocusInWindow();

        JSplitPane split = new JSplitPane(SwingConstants.HORIZONTAL, top, tiles);

        cardPanel.add(split);
        cardPanel.setVisible(true);
        //frame.add(cardPanel);
        //frame.setVisible(true);
    }

    private void toggleScoreEffect() {
        scoreEffectActive = !scoreEffectActive;
        scoreEffectButton.setText("Score Effect: " + (scoreEffectActive ? "ON" : "OFF"));

        // tiles.revalidate();
        // tiles.repaint();
        tiles.requestFocusInWindow();
    }

    private void switchColorScheme() {
        // toggle between blue and red
        colorScheme = (colorScheme == ColorScheme.BLUE) 
            ? ColorScheme.RED 
            : ColorScheme.BLUE;
                
        tiles.setBackground(colorScheme == ColorScheme.BLUE ? new Color(0xD0D9E8) : new Color(0xbbada0));
        // update all tiles
        Tile[][] curGrid = controller.getGrid();
        int slotNum = 0;
        for (int j = 0; j <= 3; j++) {
            for (int k = 0; k <= 3; k++) {
                changeTile(curGrid[j][k], slotNum);
                slotNum++;
            }
        }
        
        tiles.revalidate();
        tiles.repaint();
        tiles.requestFocusInWindow();
    }
    private Color decideColor(int value) {   
        switch (colorScheme) {
            case BLUE:
                return calculateColorBlue(value);
            case RED:
                return calculateColorRed(value);
            default:
                return calculateColorBlue(value);
        }
    }

    private Color calculateColorRed(int value){
        switch (value) {
            case 0:
                return new Color(254, 250, 250);
            case 2:
                return new Color(0xEEE4DB);
            case 4:
                return new Color(0xEFDFCB);
            case 8:
                return new Color(0xF3B279);
            case 16:
                return new Color(0xF69564);
            case 32:
                return new Color(0xF67C5F);
            case 64:
                return new Color(0xF7603B);
            case 128:
                return new Color(0xECD072);
            case 256:
                return new Color(0xEDCC62);
            case 512:
                return new Color(0xEEC950);
            case 1024:
                return new Color(0xEEC43F);
            case 2048:
                return new Color(0xEDC12E);
            default:
                return new Color(0xEDC12E);
        }
    }

 
    private Color calculateColorBlue(int value) {
        switch (value) {
            case 0:
                return new Color(250, 250, 254);
            case 2:
                return new Color(0xfafaff);
            case 4:
                return new Color(0xf0f4fc);
            case 8:
                return new Color(0xA2C2E3);
            case 16:
                return new Color(0x8DAFE0);
            case 32:
                return new Color(0x7F98D7);
            case 64:
                return new Color(0x5C84D0);
            case 128:
                return new Color(0x4B74B9);
            case 256:
                return new Color(0x3E64A3);
            case 512:
                return new Color(0x335B8C);
            case 1024:
                return new Color(0x2C5181);
            case 2048:
                return new Color(0x204773);
            default:
                return new Color(0x204773);
        }
    }

    public void changeTile(Tile tile, int slotNum) {
        JLabel slot = slots[slotNum];
        JPanel tilePanel = (JPanel) slot.getParent();
        if (tile.getValue() == 0) {
            slot.setText("");
            tilePanel.setBackground(colorScheme == ColorScheme.BLUE ? new Color(0xE1E8F0) : new Color(0xcdc1b4));
        } else {
            slot.setText(Integer.toString(tile.getValue()));
            tilePanel.setBackground(decideColor(tile.getValue()));

            // change font color for dark tiles
            if (tile.getValue() < 8) {
                slot.setForeground(colorScheme == ColorScheme.BLUE ? new Color(0x656F86) : new Color(0x766E65));
            } else {
                slot.setForeground(Color.WHITE);
            }
        }
        slot.setBackground(decideColor(tile.getValue()));
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
        JPanel main = new JPanel(new BorderLayout());
        JPanel leaders = new JPanel(new GridLayout());
        JPanel buttonHolder = new JPanel();

        leaders.setPreferredSize(new Dimension(750, 745));
        buttonHolder.setPreferredSize((new Dimension(750,35)));

        JButton newGame = new JButton("Start a New Game");
        newGame.addActionListener(e ->{
            frame.dispose();
            new BoardGUI();
        });
        buttonHolder.add(newGame);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("", Font.PLAIN, 20));
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

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

        leaders.add(scroll);

        main.add(leaders, BorderLayout.CENTER);
        main.add(buttonHolder, BorderLayout.PAGE_END);
        cardPanel.add(main);
        layout.next(cardPanel);
    }

    private void handleGameOver() {
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

class RoundedPanel extends JPanel {

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
