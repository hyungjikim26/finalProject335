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

    public Tile(int value) {
        this.value = value;
        calculateColor();
    }

    public Tile() {
        this(0);
    }

    // if other has the same value, update this.value
    public void merge(Tile other){
        if (other.getValue() == value){
            value *= 2;
            other.destroy();
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

    private void calculateColor(){
        switch (value) {
            case 0:
                color = new Color(254, 250, 250);
                break;
            case 2:
                color = new Color(250, 241, 234);
                break;
            case 4:
                color = new Color(250, 238, 225);
                break;
            case 8:
                color = new Color(255,230,180);
                break;
            case 16:
                color = new Color(250, 210, 120);
                break;
            case 32:
                color = new Color(240,130,73);
                break;
            case 64:
                color = new Color(250,100,60);
                break;
            case 128:
                color = new Color(255,250,160);
                break;
            case 256:
                color = new Color(250,243,145);
                break;
            case 512:
                color = new Color(245,238,120);
                break;
            case 1024:
                color = new Color(253,238,90);
                break;
            case 2048:
                color = new Color(255,238,50);
                break;
        }
    }
}