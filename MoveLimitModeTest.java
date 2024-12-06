/**
 * File: MoveLimitModeTest.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: Contains unit tests for the MoveLimitMode class.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MoveLimitModeTest {
	MoveLimitMode createMoveLimitBoard(int[][] matrix) {
		MoveLimitMode board = new MoveLimitMode(matrix.length, 0);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				board.addTile(i, j, matrix[i][j]);
			}
		}
		return board;
	}

	@Test
	void testWin() {
        int[][] matrix = {
            {2, 4, 8, 16},
            {32, 64, 128, 256},
            {512, 1024, 2048, 2},
            {32, 64, 128, 256},
        };
        
        MoveLimitMode moveLimitMode = createMoveLimitBoard(matrix);
        assertNotEquals(0, moveLimitMode.getMovesLeft());
        assertTrue(moveLimitMode.isGameOver());
        
        String message = moveLimitMode.getGameOverMessage();
        assertEquals("You win!", message);
    }
	
	@Test
    void testLose() {
        int[][] matrix = {
            {2, 4, 8, 16},
            {32, 64, 128, 256},
            {512, 1024, 8, 16},
            {32, 64, 128, 256}
        };
        
        MoveLimitMode moveLimitMode = createMoveLimitBoard(matrix);
        assertNotEquals(0, moveLimitMode.getMovesLeft());
        assertTrue(moveLimitMode.isGameOver());
        

        String message = moveLimitMode.getGameOverMessage();
        assertEquals("You lose!", message);
    }

    @Test
    void testNoMovesLeftAndLose() {
        // merge is still possible, but no more moves left
        int[][] matrix = {
            {2, 4, 8, 16},
            {32, 64, 128, 256},
            {512, 8, 8, 16},
            {32, 64, 128, 256}
        };
        
        MoveLimitMode moveLimitMode = createMoveLimitBoard(matrix);
        // moveLimitMode.movesLeft = 0;
        for (int i = 0; i < moveLimitMode.getMoveLimit(); i++) {
            moveLimitMode.updateGateState();
        }
        assertEquals(0, moveLimitMode.getMovesLeft());
        assertTrue(moveLimitMode.isGameOver());

        String message = moveLimitMode.getGameOverMessage();
        assertEquals("No moves left. You lose!", message);
    }

    @Test
    void testNoMovesLeftAndWin() {
        int[][] matrix = {
            {2, 4, 8, 16},
            {32, 64, 128, 256},
            {512, 1024, 2048, 2},
            {32, 64, 128, 256},
        };
        
        MoveLimitMode moveLimitMode = createMoveLimitBoard(matrix);
        // moveLimitMode.movesLeft = 0;
        for (int i = 0; i < moveLimitMode.getMoveLimit(); i++) {
            moveLimitMode.updateGateState();
        }
        assertEquals(0, moveLimitMode.getMovesLeft());
        assertTrue(moveLimitMode.isGameOver());

        String message = moveLimitMode.getGameOverMessage();
        assertEquals("You win!", message);
    }

    @Test
    void testGameIsOver() {
        int[][] matrix = {
            {2, 4, 8, 16},
            {32, 64, 128, 256},
            {512, 1024, 8, 16},
            {32, 64, 128, 256}
        };
        
        MoveLimitMode moveLimitMode = createMoveLimitBoard(matrix);
        assertTrue(moveLimitMode.isGameOver());
    }

    @Test
    void testGameIsNotOver() {
        int[][] matrix = {
            {2, 2, 8, 16},
            {32, 64, 128, 256},
            {512, 1024, 8, 16},
            {32, 64, 128, 256}
        };
        
        MoveLimitMode moveLimitMode = createMoveLimitBoard(matrix);
        assertFalse(moveLimitMode.isGameOver());
    }

    @Test
    void testGameWon() {
        int[][] matrix = {
            {2, 2, 8, 16},
            {32, 64, 128, 256},
            {512, 2048, 8, 16},
            {32, 64, 128, 256}
        };

        MoveLimitMode moveLimitMode = createMoveLimitBoard(matrix);
        assertTrue(moveLimitMode.isGameOver());
    }
    
    @Test
    void test_moveMethods() {
        int[][] matrix = {
            {2, 2, 8, 16},
            {32, 64, 128, 256},
            {512, 2048, 8, 16},
            {32, 64, 128, 256}
        };

        MoveLimitMode moveLimitMode = createMoveLimitBoard(matrix);
        int movesLeftInitial = moveLimitMode.getMovesLeft();
        assertTrue(moveLimitMode.moveLeft());
        int movesLeftNext = moveLimitMode.getMovesLeft();
        assertEquals(1, movesLeftInitial - movesLeftNext);
        moveLimitMode.addTile(0, 3, 2);
        assertFalse(moveLimitMode.moveLeft());
        int movesLeftFinal = moveLimitMode.getMovesLeft();
        assertEquals(0, movesLeftNext - movesLeftFinal);
    }

    @Test
    void testDefaultConstructor() {
        // default will be 4x4 with two initial tiles
        MoveLimitMode moveLimitMode = new MoveLimitMode();
        Tile[][] grid = moveLimitMode.getGrid();
        int emptySpots = 0;

        for (int i = 0; i < moveLimitMode.getSize(); i++) {
			for (int j = 0; j < moveLimitMode.getSize(); j++) {
				System.out.println(grid[i][j].getValue());
				if (grid[i][j].isEmpty()) {
					emptySpots++;
				}
			}
		}
		assertEquals(14, emptySpots);
    }	
}
