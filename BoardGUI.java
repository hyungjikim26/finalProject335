import javax.swing.*;
import java.awt.*;

public class BoardGUI implements java.awt.event.KeyListener{
    private Board board = new Board();
    private int score = 0;
    private JLabel[] slots = new JLabel[16];
    private JLabel scoreLabel;
    private GameState currentState;

    public static void main(String[] args) {
		new BoardGUI();
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
            currentState = board.checkState();
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
            currentState = board.checkState();
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
    }

    public BoardGUI(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,780);
        frame.setLocationRelativeTo(null);

        scoreLabel = new JLabel("Current Score: "+score);
        //scoreLabel.setSize(750, 30);
        
        JPanel top = new JPanel();
        top.add(scoreLabel);
        
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

        frame.add(scoreLabel,"North");
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
}
