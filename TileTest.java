/**
 * File: TileTest.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose:
 * 
 */

import static org.junit.Assert.*;

import org.junit.Test;



public class TileTest {

    @Test
    public void testMerge(){
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(2);

        tile1.merge(tile2);
        
        assertEquals(4, tile1.getValue());
        assertEquals(0, tile2.getValue());
        
        Tile tile3 = new Tile(16);
        Tile tile4 = new Tile(32);

        tile3.merge(tile4);
        
        assertEquals(16, tile3.getValue());
        assertEquals(32, tile4.getValue());
    }
    
    @Test
    public void testEquals(){
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();

        assertTrue(tile1.equals(tile2));
        
        Tile tile3 = new Tile(1024);
        Tile tile4 = new Tile(1024);

        assertTrue(tile3.equals(tile4));
        
        Tile tile5 = new Tile(8);
        Tile tile6 = new Tile(0);

        assertFalse(tile5.equals(tile6));
    }
    
    @Test
    public void testIsEmpty(){
        Tile tile1 = new Tile();
        Tile tile2 = new Tile(0);
        Tile tile3 = new Tile(8);

        assertTrue(tile1.isEmpty());
        assertTrue(tile2.isEmpty());
        assertFalse(tile3.isEmpty());
    }
}
