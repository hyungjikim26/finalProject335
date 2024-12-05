import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ControllerTest {
	
	void createBoard(Controller controller, int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				controller.addTile(i, j, matrix[i][j]);
			}
		}
	}
	
	int countTiles(Controller controller) {
		Tile[][] grid = controller.getGrid();
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (!grid[i][j].isEmpty())
					count++;
			}
		}
		return count;
	}
	
	@Test
	void test_moveMethods() {
		Controller controller = new Controller();
		int[][] matrix = 	{{2, 0, 0, 0},
							 {0, 0, 0, 0},
						 	 {0, 0, 0, 0},
						 	 {0, 0, 0, 0}};
		createBoard(controller, matrix);
		
		int count = countTiles(controller);
		
		controller.moveBoardRight();
		
		int countNew = countTiles(controller);
		
		assertEquals(1, countNew - count);
		
	}
	
	@Test
	void test_movesLeft() {
		
	}
	
	@Test
	void test_Timer() {

	}
	
	@Test
	void test_getScore() {

	}
	
	@Test
	void test_leaderboard() {

	}


}
