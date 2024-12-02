import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {
	
	Board createBoard(int[][] matrix) {
		Board board = new Board(matrix.length, 0);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				board.addTile(i, j, matrix[i][j]);
			}
		}
		return board;
	}
	
	void checkBoard(Board board, int[][] result) {
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				assertEquals(result[i][j], board.getValue(i, j));
			}
		}
	}

	@Test
	void test_moveUpMethod() {
		int[][] valuesA = {{2, 2}, 
						   {2, 2}};
		Board boardA = createBoard(valuesA);
		System.out.println(boardA);
		assertEquals(2, boardA.getValue(0, 0));
		assertEquals(2, boardA.getValue(0, 1));
		assertEquals(2, boardA.getValue(1, 0));
		assertEquals(2, boardA.getValue(1, 1));
		boardA.moveUp();
		System.out.println(boardA);
		assertEquals(4, boardA.getValue(0, 0));
		assertEquals(4, boardA.getValue(0, 1));
		assertEquals(0, boardA.getValue(1, 0));
		assertEquals(0, boardA.getValue(1, 1));

		int[][] valuesB = {{2, 0, 2, 2}, 
						   {0, 0, 0, 0}, 
						   {4, 0, 0, 4}, 
						   {0, 8, 2, 4}};
		Board boardB = createBoard(valuesB);
		System.out.println(boardB);
		boardB.moveUp();
		System.out.println(boardB);
		assertEquals(2, boardB.getValue(0, 0));
		assertEquals(8, boardB.getValue(0, 1));
		assertEquals(4, boardB.getValue(0, 2));
		assertEquals(2, boardB.getValue(0, 3));
		assertEquals(4, boardB.getValue(1, 0));
		assertEquals(0, boardB.getValue(1, 2));
		assertEquals(8, boardB.getValue(1, 3));

		int[][] valuesC = {{2, 32, 4, 0}, 
				   		   {2, 8, 0, 128}, 
				           {2, 32, 2, 0}, 
				           {2, 0, 0, 16}};
		Board boardC = createBoard(valuesC);
		int[][] resultC = {{4, 32, 4, 128}, 
		   		   		   {4, 8, 2, 16}, 
		   		   		   {0, 32, 0, 0}, 
		   		   		   {0, 0, 0, 0}};
		boardC.moveUp();
		checkBoard(boardC, resultC);
		
		int[][] valuesD = {{2, 0, 0, 0}, 
		   		   		   {2, 0, 0, 0}, 
		                   {4, 0, 0, 0}, 
		                   {0, 0, 0, 0}};
		Board boardD = createBoard(valuesD);
		
		int[][] resultD = {{4, 0, 0, 0}, 
		   		           {4, 0, 0, 0}, 
                           {0, 0, 0, 0}, 
                           {0, 0, 0, 0}};
		boardD.moveUp();
		checkBoard(boardD, resultD);
		
		int[][] valuesE = {{2, 16, 2, 32, 4},
			 			   {0, 0, 2, 16, 4},
		                   {2, 0, 2, 16, 4},
		                   {0, 16, 2, 0, 4},
		                   {4, 32, 2, 0, 16}};
		
		Board boardE = createBoard(valuesE);
		int[][] resultE = {{4, 32, 4, 32, 8},
						   {4, 32, 4, 32, 8},
	 			   		   {0, 0, 2, 0, 16},
	 			   		   {0, 0, 0, 0, 0},
	 			   		   {0, 0, 0, 0, 0}};
		boardE.moveUp();
		checkBoard(boardE, resultE);
		
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
		assertFalse(boardA.losingCondition());

		int[][] values = {{2, 4, 2, 4}, 
			              {4, 2, 4, 2}, 
			              {2, 4, 2, 4}, 
			              {4, 2, 4, 2}};
		Board boardB = createBoard(values);
		assertTrue(boardB.losingCondition());

		int[][] valuesC = {{8, 4, 2, 4}, 
				           {8, 2, 4, 2}, 
				           {2, 4, 2, 4}, 
				           {4, 2, 4, 2}};
		Board boardC = createBoard(valuesC);
		assertFalse(boardC.losingCondition());
		
		int[][] valuesD = {{2, 4, 2, 4}, 
				           {8, 8, 4, 2}, 
				           {2, 4, 2, 4}, 
				           {4, 2, 4, 2}};
		Board boardD = createBoard(valuesD);
		assertFalse(boardD.losingCondition());
		
		int[][] valuesE = {{2, 4, 2, 4}, 
				           {4, 2, 4, 2}, 
				           {2, 4, 2, 8}, 
				           {4, 2, 4, 8}};
		Board boardE = createBoard(valuesE);
		assertFalse(boardE.losingCondition());
		
		int[][] valuesF = {{2, 4, 2, 4}, 
				           {4, 2, 8, 8}, 
				           {2, 4, 2, 4}, 
				           {4, 2, 4, 2}};
		Board boardF = createBoard(valuesF);
		assertFalse(boardF.losingCondition());
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
