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
    
    // Test that probablity of 2 and 4 is approximately correct
    @Test
    public void testProbability(){
        int two = 0;
        int four = 0;

        for (int i = 0; i < 1000; i ++){
            Tile tile = new Tile();
            int value = tile.getValue();
            
            if (value == 2){
                two ++;
            }else{
                four ++;
            }
        }

        assertTrue(two > 800 && two < 950);
        assertTrue(four > 50 && four < 200);
    }

    @Test
    public void testMerge(){
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();

        if (tile1.getValue() == 2){
            // if two values are the same as 2
            if (tile2.getValue() == 2){
                tile1.merge(tile2);
                assertEquals(4, tile1.getValue());
            } else{
                // if two are different
                tile1.merge(tile2);
                assertEquals(2,  tile1.getValue());
            }
        }else{
            // if two values are the same as 4
            if (tile2.getValue() == 4){
                tile1.merge(tile2);
                assertEquals(8, tile1.getValue());
            } else{
                // if two are different
                tile1.merge(tile2);
                assertEquals(4,  tile1.getValue());
            }
        }
    }
}
