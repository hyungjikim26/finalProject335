import javax.swing.*;
import java.awt.*;

public class BoardGUI {
    JLabel[] slots = new JLabel[16];

    public static void main(String[] args) {
		new BoardGUI();
	}

    public BoardGUI(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,750);
        
        GridLayout grid = new GridLayout(4,4);
        frame.setLayout(grid);
        for(int i = 0; i<=15; i++){
            slots[i] = new JLabel();
            slots[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            frame.add(slots[i]);
        }
        frame.setVisible(true);
    }

    public void changeTile(Tile tile, int slotNum){
        slots[slotNum].setText(Integer.toString(tile.getValue()));
    }
}
