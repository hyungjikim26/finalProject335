import javax.swing.*;
import java.awt.*;

public class BoardGUI implements java.awt.event.KeyListener{
    private Board board = new Board();
    private int score = 0;
    private JLabel[] slots = new JLabel[16];

    public static void main(String[] args) {
		new BoardGUI();
	}

    public void keyTyped(java.awt.event.KeyEvent e){
        int keyChar = e.getKeyChar();
        switch ( keyChar ) {
            case 'w':
                board.moveUp();
                break;
            case 's':
                board.moveDown();
                break;
            case 'd':
                board.moveRight();
                break;
            case 'a':
                board.moveLeft();
                break;
        }
        if(board.hasChanged()){
            board.addRandomTile();
        }
        updateGrid();
    }

    public void keyReleased(java.awt.event.KeyEvent e ){

    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch ( keyCode ) {
            case java.awt.event.KeyEvent.VK_UP:
                board.moveUp();
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                board.moveDown();
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                board.moveRight();
                break;
            case java.awt.event.KeyEvent.VK_LEFT:
                board.moveLeft();
                break;
        }
        if(board.hasChanged()){
            board.addRandomTile();
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
    }

    public BoardGUI(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,780);
        frame.setLocationRelativeTo(null);

        JLabel scoreLabel = new JLabel("Current Score: "+score);
        scoreLabel.setSize(750, 30);
        
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

        frame.addKeyListener(this);

        frame.add(scoreLabel,"North");
        frame.add(tiles);
        frame.setVisible(true);
        tiles.setFocusable(true);
    }

    public void changeTile(Tile tile, int slotNum){
        if(tile.getValue()!=0){
            slots[slotNum].setText(Integer.toString(tile.getValue()));
        }
        slots[slotNum].setFont(new Font("", Font.PLAIN, 60));
        slots[slotNum].setBackground(tile.getColor());
        slots[slotNum].setOpaque(true);
    }
}
