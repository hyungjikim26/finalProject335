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
		board.moveUp();
		System.out.println(board);
		
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
	}

}
