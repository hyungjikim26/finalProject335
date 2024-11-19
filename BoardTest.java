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

}
