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

        JLabel slot1 = new JLabel();
        slots[0] = slot1;
        JLabel slot2 = new JLabel();
        slots[1] = slot2;
        JLabel slot3 = new JLabel();
        slots[2] = slot3;
        JLabel slot4 = new JLabel();
        slots[3] = slot4;
        JLabel slot5 = new JLabel();
        slots[4] = slot5;
        JLabel slot6 = new JLabel();
        slots[5] = slot6;
        JLabel slot7 = new JLabel();
        slots[6] = slot7;
        JLabel slot8 = new JLabel();
        slots[7] = slot8;
        JLabel slot9 = new JLabel();
        slots[8] = slot9;
        JLabel slot10 = new JLabel();
        slots[9] = slot10;
        JLabel slot11 = new JLabel();
        slots[10] = slot11;
        JLabel slot12 = new JLabel();
        slots[11] = slot12;
        JLabel slot13 = new JLabel();
        slots[12] = slot13;
        JLabel slot14 = new JLabel();
        slots[13] = slot14;
        JLabel slot15 = new JLabel();
        slots[14] = slot15;
        JLabel slot16 = new JLabel();
        slots[15] = slot16;
        
        GridLayout grid = new GridLayout(4,4);
        frame.setLayout(grid);
        for(int i = 0; i<=15; i++){
            slots[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            frame.add(slots[i]);
        }
        frame.setVisible(true);
    }

    public void changeTile(Tile tile, int slotNum){
        slots[slotNum].setText(Integer.toString(tile.getValue()));
    }
}
