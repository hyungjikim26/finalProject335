/**
 * File: MoveLimitModeTest.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose:
 * 
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoveLimitModeTest {
	Board createBoard(int[][] matrix) {
		Board board = new Board(matrix.length, 0);
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
        
        Board board = createBoard(matrix);
        MoveLimitMode moveLimitMode = new MoveLimitMode(board);
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
        
        Board board = createBoard(matrix);
        MoveLimitMode moveLimitMode = new MoveLimitMode(board);
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
        
        Board board = createBoard(matrix);
        MoveLimitMode moveLimitMode = new MoveLimitMode(board);
        // moveLimitMode.movesLeft = 0;
        for (int i = 0; i < 125; i++) {
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
        
        Board board = createBoard(matrix);
        MoveLimitMode moveLimitMode = new MoveLimitMode(board);
        // moveLimitMode.movesLeft = 0;
        for (int i = 0; i < 125; i++) {
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
        
        Board board = createBoard(matrix);
        MoveLimitMode moveLimitMode = new MoveLimitMode(board);
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
        
        Board board = createBoard(matrix);
        MoveLimitMode moveLimitMode = new MoveLimitMode(board);
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

        Board board = createBoard(matrix);
        MoveLimitMode moveLimitMode = new MoveLimitMode(board);
        assertTrue(moveLimitMode.isGameOver());
    }
}
