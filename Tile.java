/**
 * File: Tile.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose:
 * 
 */

public class Tile {
    private int value ;
    private boolean isMerging = false;


    public Tile(int value) {
        this.value = value;
    }
    
    public Tile() {
        this(0);
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

}
