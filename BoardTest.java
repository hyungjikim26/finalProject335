import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

	@Test
	void test_moveUpMethod() {
		Board board = new Board(2, 0);
		System.out.println(board);
		board.addTile(0, 0, 2);
		board.addTile(0, 1, 2);
		board.addTile(1, 0, 2);
		board.addTile(1, 1, 2);
		System.out.println(board);
		assertEquals(2, board.getValue(0, 0));
		assertEquals(2, board.getValue(0, 1));
		assertEquals(2, board.getValue(1, 0));
		assertEquals(2, board.getValue(1, 1));
		board.moveUp();
		System.out.println(board);
		assertEquals(4, board.getValue(0, 0));
		assertEquals(4, board.getValue(0, 1));
		assertEquals(0, board.getValue(1, 0));
		assertEquals(0, board.getValue(1, 1));

		board = new Board(4, 0);
		System.out.println(board);
		board.addTile(0, 0, 2);
		board.addTile(0, 2, 2);
		board.addTile(0, 3, 2);
		board.addTile(2, 0, 4);
		board.addTile(2, 3, 4);
		board.addTile(3, 1, 8);
		board.addTile(3, 2, 2);
		board.addTile(3, 3, 4);
		System.out.println(board);
		board.moveUp();
		System.out.println(board);
		assertEquals(2, board.getValue(0, 0));
		assertEquals(8, board.getValue(0, 1));
		assertEquals(4, board.getValue(0, 2));
		assertEquals(2, board.getValue(0, 3));
		assertEquals(4, board.getValue(1, 0));
		assertEquals(0, board.getValue(1, 2));
		assertEquals(8, board.getValue(1, 3));

		board = new Board(4, 0);
		System.out.println(board);
		board.addTile(0, 0, 2);
		board.addTile(1, 0, 2);
		board.addTile(2, 0, 2);
		board.addTile(3, 0, 2);
		System.out.println(board);
		board.moveUp();
		System.out.println(board);
		assertEquals(4, board.getValue(0, 0));
		assertEquals(4, board.getValue(1, 0));
		assertEquals(0, board.getValue(2, 0));
	}

	@Test
	void test_moveRightMethod() {
		Board board = new Board(2, 0);
		System.out.println(board);
		board.addTile(0, 0, 2);
		board.addTile(0, 1, 2);
		board.addTile(1, 0, 2);
		board.addTile(1, 1, 2);
		System.out.println(board);
		assertEquals(2, board.getValue(0, 0));
		assertEquals(2, board.getValue(0, 1));
		assertEquals(2, board.getValue(1, 0));
		assertEquals(2, board.getValue(1, 1));
		board.moveRight();
		System.out.println(board);
		assertEquals(0, board.getValue(0, 0));
		assertEquals(4, board.getValue(0, 1));
		assertEquals(0, board.getValue(1, 0));
		assertEquals(4, board.getValue(1, 1));

		board = new Board(4, 0);
		System.out.println(board);
		board.addTile(0, 0, 2);
		board.addTile(0, 2, 2);
		board.addTile(0, 3, 2);
		board.addTile(2, 0, 4);
		board.addTile(2, 3, 4);
		board.addTile(3, 1, 8);
		board.addTile(3, 2, 2);
		board.addTile(3, 3, 4);
		System.out.println(board);
		board.moveRight();
		System.out.println(board);
		assertEquals(4, board.getValue(0, 3));
		assertEquals(2, board.getValue(0, 2));
		assertEquals(8, board.getValue(2, 3));
		assertEquals(4, board.getValue(3, 3));
		assertEquals(2, board.getValue(3, 2));
		assertEquals(8, board.getValue(3, 1));
		assertEquals(0, board.getValue(3, 0));

		board = new Board(4, 0);
		System.out.println(board);
		board.addTile(0, 0, 2);
		board.addTile(1, 0, 2);
		board.addTile(2, 0, 2);
		board.addTile(3, 0, 2);
		System.out.println(board);
		board.moveRight();
		System.out.println(board);
		assertEquals(2, board.getValue(0, 3));
		assertEquals(2, board.getValue(1, 3));
		assertEquals(2, board.getValue(2, 3));
		assertEquals(2, board.getValue(2, 3));
	}

	@Test
	void test_moveDownMethod() {
		Board board = new Board(2, 0);
		System.out.println(board);
		board.addTile(0, 0, 2);
		board.addTile(0, 1, 2);
		board.addTile(1, 0, 2);
		board.addTile(1, 1, 2);
		System.out.println(board);
		assertEquals(2, board.getValue(0, 0));
		assertEquals(2, board.getValue(0, 1));
		assertEquals(2, board.getValue(1, 0));
		assertEquals(2, board.getValue(1, 1));
		board.moveDown();
		System.out.println(board);
		assertEquals(0, board.getValue(0, 0));
		assertEquals(0, board.getValue(0, 1));
		assertEquals(4, board.getValue(1, 0));
		assertEquals(4, board.getValue(1, 1));

		board = new Board(4, 0);
		System.out.println(board);
		board.addTile(0, 0, 2);
		board.addTile(0, 2, 2);
		board.addTile(0, 3, 2);
		board.addTile(2, 0, 4);
		board.addTile(2, 3, 4);
		board.addTile(3, 1, 8);
		board.addTile(3, 2, 2);
		board.addTile(3, 3, 4);
		System.out.println(board);
		board.moveDown();
		System.out.println(board);
		assertEquals(4, board.getValue(3, 0));
		assertEquals(2, board.getValue(2, 0));
		assertEquals(8, board.getValue(3, 1));
		assertEquals(4, board.getValue(3, 2));
		assertEquals(8, board.getValue(3, 3));
		assertEquals(2, board.getValue(2, 3));
		assertEquals(0, board.getValue(0, 3));

		board = new Board(4, 0);
		System.out.println(board);
		board.addTile(0, 0, 2);
		board.addTile(1, 0, 2);
		board.addTile(2, 0, 2);
		board.addTile(3, 0, 2);
		System.out.println(board);
		board.moveDown();
		System.out.println(board);
		assertEquals(4, board.getValue(3, 0));
		assertEquals(4, board.getValue(2, 0));
		assertEquals(0, board.getValue(1, 0));
	}

	@Test
	void test_moveLeftMethod() {
		Board board = new Board(2, 0);
		System.out.println(board);
		board.addTile(0, 0, 2);
		board.addTile(0, 1, 2);
		board.addTile(1, 0, 2);
		board.addTile(1, 1, 2);
		System.out.println(board);
		assertEquals(2, board.getValue(0, 0));
		assertEquals(2, board.getValue(0, 1));
		assertEquals(2, board.getValue(1, 0));
		assertEquals(2, board.getValue(1, 1));
		boolean isChanged = board.moveLeft();
		System.out.println(board);
		assertTrue(isChanged);
		assertEquals(4, board.getValue(0, 0));
		assertEquals(0, board.getValue(0, 1));
		assertEquals(4, board.getValue(1, 0));
		assertEquals(0, board.getValue(1, 1));

		board = new Board(4, 0);
		System.out.println(board);
		board.addTile(0, 0, 2);
		board.addTile(0, 2, 2);
		board.addTile(0, 3, 2);
		board.addTile(2, 0, 4);
		board.addTile(2, 3, 4);
		board.addTile(3, 1, 8);
		board.addTile(3, 2, 2);
		board.addTile(3, 3, 4);
		System.out.println(board);
		boolean changed = board.moveLeft();
		System.out.println(board);
		assertTrue(changed);
		assertEquals(4, board.getValue(0, 0));
		assertEquals(2, board.getValue(0, 1));
		assertEquals(8, board.getValue(2, 0));
		assertEquals(8, board.getValue(3, 0));
		assertEquals(2, board.getValue(3, 1));
		assertEquals(4, board.getValue(3, 2));
		assertEquals(0, board.getValue(3, 3));

		board = new Board(2, 0);
		System.out.println(board);
		board.addTile(0, 0, 2);
		board.addTile(0, 1, 4);
		board.addTile(1, 0, 8);
		board.addTile(1, 1, 16);
		System.out.println(board);
		isChanged = board.moveLeft();
		System.out.println(board);
		assertFalse(isChanged);
		assertEquals(2, board.getValue(0, 0));
		assertEquals(4, board.getValue(0, 1));
		assertEquals(8, board.getValue(1, 0));
		assertEquals(16, board.getValue(1, 1));
	}

	@Test
	void test_initializingBoard() {
		Board board = new Board(2, 5);
		Tile[][] grid = board.getGrid();
		assertNotEquals(0, grid[0][0].getValue());
		assertNotEquals(0, grid[0][1].getValue());
		assertNotEquals(0, grid[1][0].getValue());
		assertNotEquals(0, grid[1][1].getValue());

		Board boardTwo = new Board(2, 3);
		Tile[][] gridTwo = boardTwo.getGrid();
		int countZeros = 0;
		for (int i = 0; i < boardTwo.getSize(); i++) {
			for (int j = 0; j < boardTwo.getSize(); j++) {
				System.out.println(gridTwo[i][j].getValue());
				if (gridTwo[i][j].isEmpty()) {
					countZeros++;
				}
			}
		}
		assertEquals(1, countZeros);

		Board boardDefault = new Board();
		Tile[][] gridDefault = boardDefault.getGrid();
		int countZerosDefault = 0;
		for (int i = 0; i < boardDefault.getSize(); i++) {
			for (int j = 0; j < boardDefault.getSize(); j++) {
				System.out.println(gridDefault[i][j].getValue());
				if (gridDefault[i][j].isEmpty()) {
					countZerosDefault++;
				}
			}
		}
		assertEquals(14, countZerosDefault);

	}

	@Test
	void testWinningCondition() {
		Board board = new Board(2, 0);
		assertFalse(board.winningCondition());
		board.addTile(0, 0, 2048);
		assertTrue(board.winningCondition());
	}

	@Test
	void testLoosingCondition() {
		Board boardA = new Board(4, 0);
		boardA.addTile(0, 0, 2);
		assertFalse(boardA.loosingCondition());

		Board boardB = new Board(4, 0);
		int[][] values = {{2, 4, 2, 4}, {4, 2, 4, 2}, {2, 4, 2, 4}, {4, 2, 4, 2}};
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				boardB.addTile(i, j, values[i][j]);
			}
		}
		assertTrue(boardB.loosingCondition());

		Board boardC = new Board(4, 0);
		int[][] valuesC = {{8, 4, 2, 4}, {8, 2, 4, 2}, {2, 4, 2, 4}, {4, 2, 4, 2}};
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				boardC.addTile(i, j, valuesC[i][j]);
			}
		}
		assertFalse(boardC.loosingCondition());
		
		Board boardD = new Board(4, 0);
		int[][] valuesD = {{2, 4, 2, 4}, {8, 8, 4, 2}, {2, 4, 2, 4}, {4, 2, 4, 2}};
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				boardD.addTile(i, j, valuesD[i][j]);
			}
		}
		assertFalse(boardD.loosingCondition());
		
		Board boardE = new Board(4, 0);
		int[][] valuesE = {{2, 4, 2, 4}, {4, 2, 4, 2}, {2, 4, 2, 8}, {4, 2, 4, 8}};
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				boardE.addTile(i, j, valuesE[i][j]);
			}
		}
		assertFalse(boardE.loosingCondition());
		
		Board boardF = new Board(4, 0);
		int[][] valuesF = {{2, 4, 2, 4}, {4, 2, 8, 8}, {2, 4, 2, 4}, {4, 2, 4, 2}};
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				boardF.addTile(i, j, valuesF[i][j]);
			}
		}
		assertFalse(boardF.loosingCondition());
	}
	
	@Test
    void testScore() {
		Board board = new Board();
		assertEquals(0, board.getScore());
		
        Board boardA = new Board(4, 0);
        boardA.addTile(0, 0, 2);
        boardA.addTile(1, 0, 2);
        boardA.moveUp();
        assertEquals(4, boardA.getScore());

        Board boardB = new Board(4, 0);
        boardB.addTile(0, 0, 2);
        boardB.addTile(1, 0, 2);
        boardB.addTile(2, 0, 4);
        boardB.addTile(3, 0, 4);
        boardB.moveUp();
        assertEquals(12, boardB.getScore());

        Board boardC = new Board(4, 0);
        boardC.addTile(0, 0, 2);
        boardC.addTile(1, 0, 4);
        boardC.moveUp();
        assertEquals(0, boardC.getScore());

        Board boardD = new Board(4, 0);
        boardD.addTile(0, 0, 2);
        boardD.addTile(1, 0, 2);
        boardD.moveUp();
        assertEquals(4, boardD.getScore());
        boardD.addTile(2, 0, 4);
        boardD.addTile(3, 0, 4);
        boardD.moveUp();
        assertEquals(12, boardD.getScore());

        Board boardE = new Board(4, 0);
        boardE.addTile(0, 0, 2);
        boardE.addTile(1, 0, 2);
        boardE.moveUp();
        assertEquals(4, boardE.getScore());
        boardE = new Board(4, 0);
        assertEquals(0, boardE.getScore());
    }
}
