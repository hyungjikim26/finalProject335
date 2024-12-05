/**
 * File: Tile.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose:
 * 
 */

import java.awt.*;

public class Tile {
    private int value ;
    private Color color;
    private ColorScheme colorScheme;
    private boolean isMerging = false;


    public Tile(int value, ColorScheme colorScheme) {
        this.value = value;
        this.colorScheme = colorScheme;
        calculateColor();
    }

    public Tile(int value) {
        this(value, ColorScheme.RED);
    }

    // if other has the same value, update this.value
    public void merge(Tile other){
        if (other.getValue() == value){
            value *= 2;
            other.destroy();
            isMerging = true;
        }
    }

    public int getValue(){
        return value;
    }

    public Color getColor(){
        return color;
    }
    
    public boolean equals(Tile other) {
    	return this.value == other.value;
    }
    
    private void destroy() {
    	this.value = 0;
    }
    
    public boolean isEmpty() {
    	return value == 0;
    }

    public boolean isMerging() {
        return isMerging;
    }

    public void setMerging(boolean merging) {
        isMerging = merging;
    }

    public void switchColorScheme(ColorScheme newScheme) {
        this.colorScheme = newScheme;
        calculateColor();
    }

    public void calculateColor() {   
        switch (colorScheme) {
            case BLUE:
                calculateColorBlue();
                break;
            case RED:
                calculateColorRed();
                break;
            default:
                calculateColorBlue();
        }
    }

    private void calculateColorRed(){
        switch (value) {
            case 0:
                color = new Color(254, 250, 250);
                break;
            case 2:
                color = new Color(0xEEE4DB);
                break;
            case 4:
                color = new Color(0xEFDFCB);
                break;
            case 8:
                color = new Color(0xF3B279);
                break;
            case 16:
                color = new Color(0xF69564);
                break;
            case 32:
                color = new Color(0xF67C5F);
                break;
            case 64:
                color = new Color(0xF7603B);
                break;
            case 128:
                color = new Color(0xECD072);
                break;
            case 256:
                color = new Color(0xEDCC62);
                break;
            case 512:
                color = new Color(0xEEC950);
                break;
            case 1024:
                color = new Color(0xEEC43F);
                break;
            case 2048:
                color = new Color(0xEDC12E);
                break;
        }
    }

 
    private void calculateColorBlue() {
        switch (value) {
            case 0:
                color = new Color(250, 250, 254);
                break;
            case 2:
                color = new Color(0xfafaff);
                break;
            case 4:
                color = new Color(0xf0f4fc);
                break;
            case 8:
                color = new Color(0xA2C2E3);
                break;
            case 16:
                color = new Color(0x8DAFE0);
                break;
            case 32:
                color = new Color(0x7F98D7);
                break;
            case 64:
                color = new Color(0x5C84D0);
                break;
            case 128:
                color = new Color(0x4B74B9);
                break;
            case 256:
                color = new Color(0x3E64A3);
                break;
            case 512:
                color = new Color(0x335B8C);
                break;
            case 1024:
                color = new Color(0x2C5181);
                break;
            case 2048:
                color = new Color(0x204773);
                break;
            default:
                color = new Color(0x204773);
                break;
        }
    }
}
