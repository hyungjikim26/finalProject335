/**
 * File: TraditionalModeTest.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose:
 * 
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TraditionalModeTest {
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
	void testWin1() {
		int[][] matrix = {
			{2, 4, 8, 16},
			{32, 64, 128, 256},
			{512, 1024, 2048, 2},
			{32, 64, 128, 256},
		};
		
		Board board = createBoard(matrix);
		TraditionalMode traditionalMode = new TraditionalMode(board);
		assertTrue(traditionalMode.isGameOver());
		
		String message = traditionalMode.getGameOverMessage();
		assertEquals("You win!", message);
	}

	@Test
	void testWin2() {
		int[][] matrix = {
			{2, 2, 8, 16},
			{32, 64, 128, 256},
			{512, 1024, 2048, 2},
			{32, 64, 128, 256},
		};
		
		Board board = createBoard(matrix);
		TraditionalMode traditionalMode = new TraditionalMode(board);
		assertTrue(traditionalMode.isGameOver());
		
		String message = traditionalMode.getGameOverMessage();
		assertEquals("You win!", message);
	}

	@Test
	void testLose1() {
		int[][] matrix = {
			{2, 4, 8, 16},
			{32, 64, 128, 256},
			{512, 1024, 8, 16},
			{32, 64, 128, 256}
		};
		
		Board board = createBoard(matrix);
		TraditionalMode traditionalMode = new TraditionalMode(board);
		assertTrue(traditionalMode.isGameOver());

		String message = traditionalMode.getGameOverMessage();
		assertEquals("You lose!", message);
	}

	@Test
	void testNotWinNotLose() {
		int[][] matrix = {
			{2, 2, 8, 16},
			{32, 64, 128, 256},
			{512, 1024, 8, 16},
			{32, 64, 128, 256}
		};
		
		Board board = createBoard(matrix);
		TraditionalMode traditionalMode = new TraditionalMode(board);
		assertFalse(traditionalMode.isGameOver());
	}

}
