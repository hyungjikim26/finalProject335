/**
 * File: BoardGUI.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: TThe BoardGUI class manages the graphical interface of the game board,
 *          displaying tiles and handling user interactions.
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
    private JLabel[] slots = new JLabel[16];
    private JLabel scoreLabel;
    private CardLayout layout;
    //the main frame for the GUI
    private JFrame frame;
    private JPanel cardPanel;
    private JLabel timerLabel;
    private JLabel movesLeftLabel;
    private JLabel modeLabel;
    private ColorScheme colorScheme = ColorScheme.RED;
    private JPanel currentScorePanel = null;
    private boolean scoreEffectActive = false;
    private JButton scoreEffectButton;
    private JPanel tiles;


    public static void main(String[] args) {
        new BoardGUI();
    }
    
    /**
     * Default constructor for the BoardGUI class.
     */
    public BoardGUI() {
        chooseGameMode();
    }
    
    /**
     * Create the main screen that allows the user to choose a game mode.
     * @return the GameModeType that was selected
     */
    public void chooseGameMode() {
        //format the frame
        frame = new JFrame("2048");
        cardPanel = new JPanel();
        layout = new CardLayout();
        cardPanel.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 780);
        //center the window on the computer screen
        frame.setLocationRelativeTo(null);
        frame.add(cardPanel);

        //the base panel that will contain other panels
        JPanel main = new JPanel(new BorderLayout());

        //the panel that will contain the game title and the select mode message
        JPanel chooseMode = new JPanel(new BorderLayout());
        Font labelFont = new Font("Helvetica", Font.BOLD, 180);
        JLabel title = new JLabel("2048",SwingConstants.CENTER);
        //make the title "2048" red
        title.setForeground(new Color(0xF7603B));
        title.setFont(labelFont);
        JLabel message = new JLabel("Select a Game Mode", SwingConstants.CENTER);
        Font messageFont = new Font("Helvetica", Font.BOLD, 25);
        message.setFont(messageFont);
        chooseMode.add(title, BorderLayout.CENTER);
        chooseMode.add(message, BorderLayout.SOUTH);

        //add padding at the top of the panel
        chooseMode.setBorder(BorderFactory.createEmptyBorder(140,0,10,0));

        //the panel that will contain the buttons to select a game mode.
        JPanel buttons = new JPanel(new FlowLayout());
        JButton traditional = new JButton("Traditional");
        JButton time = new JButton("Time");
        JButton move = new JButton("Move Limit");

        buttons.add(traditional, BorderLayout.WEST);
        buttons.add(time,BorderLayout.CENTER);
        buttons.add(move,BorderLayout.EAST);

        //add both panels to the main panel
        main.add(chooseMode, BorderLayout.NORTH);
        main.add(buttons, BorderLayout.CENTER);
        cardPanel.add(main);

        //ActionListeners for the buttons to initialize the game based on the selected 
        //GameModeType and switch to the next screen.
        traditional.addActionListener(e ->{
            initializeGame(GameModeType.TRADITIONAL);
            layout.next(cardPanel);
        });

        time.addActionListener(e ->{
            initializeGame(GameModeType.TIME_LIMIT);
            layout.next(cardPanel);
        });

        move.addActionListener(e ->{
            initializeGame(GameModeType.MOVE_LIMIT);
            layout.next(cardPanel);
        });
        frame.setVisible(true);
    }

    /**
     * Create a new Leaderboard object and a new Controller
     * based on the selected GameModeType, and call setUp()
     * to create the main part of the GUI
     * @return void
     * @param modeType the GameModeType that was selected
     */
    public void initializeGame(GameModeType modeType) {
        controller.newGame(modeType);

        setup(modeType);
    }
    /**
     * Create a KeyEvent for the wasd keys to
     * control the movement of the tiles in the game.
     * @return void
     */
    @Override
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
    /**
     * Unused method from the implemented KeyListener class.
     * @return void
     */
    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
    }

    /**
     * Create a KeyEvent for the arrow keys to
     * control the movement of the tiles in the game.
     * @return void
     */
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

    /**
     * Update the GUI to reflect the current information
     * in the Board, including tile positon, score, and time/moves left.
     * @return void
     */
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
	        public void onTimeUpdate(long timeDelta) {
	            elapsedTime += timeDelta; 
	            if (elapsedTime >= 100) { 
	                transparency -= 40; 
	                if (transparency < 0) transparency = 0; 
	                scoreEffect.setForeground(new Color(119, 110, 101, transparency)); 
	                elapsedTime = 0; 
	            }
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

    /**
     * Create the screen that displays the game, including the tiles, the
     * current score, the time/moves remaining, and the buttons to 
     * toggle the color scheme and score display effect.
     * @return void
     * @param modeType the GameModeType that was selected
     */
    private void setup(GameModeType modeType) {
        Font font = new Font("Clear Sans", Font.BOLD, 12);

        scoreLabel = new JLabel("Current Score: " + controller.getScore());
        scoreLabel.setFont(font);
        modeLabel = new JLabel("Mode: " + getModeString(modeType));
        modeLabel.setFont(font);

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
                public void onTimeUpdate(long remainingTime) {
                    SwingUtilities.invokeLater(() -> {
                        timerLabel.setText("Time Remaining: " + remainingTime / 1000 + "s");
                        if (controller.getTimeUp()){
                        	handleGameOver();
                        }
                    });
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
    }

    /**
     * Turn on or off the effect that displays
     * the amount of points earned by each successful move.
     * @return void
     */
    private void toggleScoreEffect() {
        scoreEffectActive = !scoreEffectActive;
        scoreEffectButton.setText("Score Effect: " + (scoreEffectActive ? "ON" : "OFF"));

        tiles.requestFocusInWindow();
    }

    /**
     * Switch between the red or blue color schemes.
     * @return void
     */
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
    /**
     * Switch between the red or blue color schemes.
     * @return the Color of the tile based on the color scheme
     * and the tile's value
     * @param value the value of a tile
     */
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

    /**
     * Switch between the red or blue color schemes.
     * @return the Color of a tile given its value
     * @param value, the value of a tile
     */
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

    /**
     * Switch between the red or blue color schemes.
     * @return the Color of a tile given its value
     * @param value, the value of a tile
     */
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

    /**
     * Set the color and numbers of a given tile.
     * @return void
     * @param tile, the Tile object to be changed.
     * @param slotNum, the tile's position on the grid (0-15) 
     */
    public void changeTile(Tile tile, int slotNum) {
        JLabel slot = slots[slotNum];
        JPanel tilePanel = (JPanel) slot.getParent();
        //only tiles with a value of 0 will be blank
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
    }

    /**
     * @return the String representation of the GameModeType.
     * @param modeType, the GameModeType that was selected
     */
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

    /**
     * @return the "game over" message from the Controller class.
     */
    private String getGameOverMessage() {
        return controller.getGameOverMessage();
    }

    /**
     * Ask the user whether to display the top 10 scores or all scores
     * then create a screen to display the corresponding leaderboard
     * and a button to start a new game.
     * @return void
     */
    private void displayLeaderboard() {
        //main panel that will contain other panels.
        JPanel main = new JPanel(new BorderLayout());
        //panel that will contain the leaderboard
        JPanel leaders = new JPanel(new GridLayout());
        //panel that will contain the Start New Game button
        JPanel buttonHolder = new JPanel();

        leaders.setPreferredSize(new Dimension(750, 745));
        buttonHolder.setPreferredSize((new Dimension(750,35)));

        JButton newGame = new JButton("Start a New Game");
        //to start a new game, close the current window and 
        //start a new game
        newGame.addActionListener(e ->{
            frame.dispose();
            new BoardGUI();
        });
        buttonHolder.add(newGame);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("", Font.PLAIN, 20));
        textArea.setEditable(false);
        //add a scroll bar to the leaderboard
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
            scores = controller.getAllScores();

        } else {
            sb.append("Top 10 Scores:\n");
            scores = controller.getTopScores();
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

    /**
     * Once the game is over, ask user for their name and enter
     * their name and score to the leaderboard, then call
     * displayLeaderboard().
     * @return void
     */
    private void handleGameOver() {
        int finalScore = controller.getScore();

        String gameOverMessage = getGameOverMessage();
        String playerName = JOptionPane.showInputDialog(
                null,
                gameOverMessage + "\nYour score: " + finalScore + "\nEnter your name: ",
                "Enter Name",
                JOptionPane.INFORMATION_MESSAGE);

        if (playerName != null && !playerName.isEmpty()) {
            controller.addScore(playerName, finalScore);

            displayLeaderboard();
        }
        else {
            frame.dispose();
        	new BoardGUI();
        }
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

//Additional class for rounded JPanels.
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
