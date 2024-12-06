import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

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
	void test_GameMode() {
		Controller controllerA = new Controller(GameModeType.TRADITIONAL);
		assertEquals(GameModeType.TRADITIONAL, controllerA.getGameMode());
		
		Controller controllerB = new Controller(GameModeType.MOVE_LIMIT);
		assertEquals(GameModeType.MOVE_LIMIT, controllerB.getGameMode());
		
		Controller controllerC = new Controller(GameModeType.TIME_LIMIT);
		assertEquals(GameModeType.TIME_LIMIT, controllerC.getGameMode());
	}
	
	@Test
	void test_moveUp() {
		Controller controllerA = new Controller();
		int[][] matrixA = 	{{0, 0, 0, 2},
							 {2, 0, 0, 0},
						 	 {0, 0, 2, 0},
						 	 {0, 2, 0, 0}};
		createBoard(controllerA, matrixA);
		
		int countA = countTiles(controllerA);
		
		controllerA.moveBoardUp();
		
		int countNewA = countTiles(controllerA);
		
		assertEquals(1, countNewA - countA);
		
		Controller controllerB = new Controller();
		int[][] matrixB = 	{{8, 4, 2, 2},
							 {0, 0, 0, 0},
						 	 {0, 0, 0, 0},
						 	 {0, 0, 0, 0}};
		createBoard(controllerB, matrixB);
		
		int countB = countTiles(controllerB);
		
		controllerB.moveBoardUp();
		
		int countNewB = countTiles(controllerB);
		
		assertEquals(0, countNewB - countB);
		
	}
	
	@Test
	void test_moveRight() {
		Controller controllerA = new Controller();
		int[][] matrixA = 	{{2, 0, 0, 0},
							 {0, 0, 0, 0},
						 	 {0, 0, 0, 0},
						 	 {0, 0, 0, 0}};
		createBoard(controllerA, matrixA);
		
		int countA = countTiles(controllerA);
		
		controllerA.moveBoardRight();
		
		int countNewA = countTiles(controllerA);
		
		assertEquals(1, countNewA - countA);
		
		Controller controllerB = new Controller();
		int[][] matrixB = 	{{0, 0, 0, 2},
							 {0, 0, 0, 0},
						 	 {0, 0, 0, 0},
						 	 {0, 0, 0, 0}};
		createBoard(controllerB, matrixB);
		
		int countB = countTiles(controllerB);
		
		controllerB.moveBoardRight();
		
		int countNewB = countTiles(controllerB);
		
		assertEquals(0, countNewB - countB);
		
	}
	
	@Test
	void test_moveDown() {
		Controller controllerA = new Controller();
		int[][] matrixA = 	{{2, 0, 0, 0},
							 {0, 0, 16, 4},
						 	 {0, 4, 0, 0},
						 	 {0, 0, 0, 1024}};
		createBoard(controllerA, matrixA);
		
		int countA = countTiles(controllerA);
		
		controllerA.moveBoardDown();
		
		int countNewA = countTiles(controllerA);
		
		assertEquals(1, countNewA - countA);
		
		Controller controllerB = new Controller();
		int[][] matrixB = 	{{0, 0, 0, 0},
							 {0, 0, 0, 0},
						 	 {4, 0, 0, 2},
						 	 {2, 8, 2, 16}};
		createBoard(controllerB, matrixB);
		
		int countB = countTiles(controllerB);
		
		controllerB.moveBoardDown();
		
		int countNewB = countTiles(controllerB);
		
		assertEquals(0, countNewB - countB);
		
	}
	
	@Test
	void test_moveLeft() {
		Controller controllerA = new Controller();
		int[][] matrixA = 	{{2, 0, 0, 0},
							 {4, 0, 16, 4},
						 	 {0, 0, 0, 0},
						 	 {2, 0, 0, 32}};
		createBoard(controllerA, matrixA);
		
		int countA = countTiles(controllerA);
		
		controllerA.moveBoardLeft();
		
		int countNewA = countTiles(controllerA);
		
		assertEquals(1, countNewA - countA);
		
		Controller controllerB = new Controller();
		int[][] matrixB = 	{{2, 4, 8, 0},
							 {0, 0, 0, 0},
						 	 {2, 0, 0, 0},
						 	 {2, 8, 2, 16}};
		createBoard(controllerB, matrixB);
		
		int countB = countTiles(controllerB);
		
		controllerB.moveBoardLeft();
		
		int countNewB = countTiles(controllerB);
		
		assertEquals(0, countNewB - countB);
		
	}
	
	@Test
	void test_movesLeft() {
		Controller controllerMLA = new Controller(GameModeType.MOVE_LIMIT);
		int[][] matrixMLA = 	{{2, 0, 0, 0},
				 			 	 {4, 0, 16, 4},
				 			 	 {0, 0, 0, 0},
				 			 	 {2, 0, 0, 32}};
		createBoard(controllerMLA, matrixMLA);
		int movesLeftA = controllerMLA.getMovesLeft();
		controllerMLA.moveBoardDown();
		assertEquals(1, movesLeftA - controllerMLA.getMovesLeft());
		
		Controller controllerMLB = new Controller(GameModeType.MOVE_LIMIT);
		int[][] matrixMLB = 	{{0, 0, 0, 2},
				 			 	 {0, 4, 16, 4},
				 			 	 {0, 0, 0, 2},
				 			 	 {0, 0, 2, 32}};
		createBoard(controllerMLB, matrixMLB);
		int movesLeftB = controllerMLB.getMovesLeft();
		controllerMLB.moveBoardRight();
		assertEquals(0, movesLeftB - controllerMLB.getMovesLeft());
		
		Controller controllerTR = new Controller(GameModeType.TRADITIONAL);
		assertEquals(-1, controllerTR.getMovesLeft());
	}
	
	@Test
	void test_GameOver() {
		Controller controllerA = new Controller(GameModeType.TRADITIONAL);
		int[][] matrixA = 	{{2, 0, 1024, 0},
				 			 	 {256, 0, 16, 4},
				 			 	 {0, 2048, 512, 0},
				 			 	 {64, 0, 0, 32}};
		createBoard(controllerA, matrixA);
		assertTrue(controllerA.won());
		assertFalse(controllerA.lost());
		assertEquals(-1, controllerA.getMovesLeft());
		assertFalse(controllerA.getTimeUp());
		assertTrue(controllerA.isGameOver());
		
		Controller controllerB = new Controller(GameModeType.MOVE_LIMIT);
		int[][] matrixB = 	{{2, 4, 8, 16},
				 			 {16, 2, 4, 8},
				 			 {8, 16, 2, 4},
				 			 {4, 8, 16, 2}};
		createBoard(controllerB, matrixB);
		assertFalse(controllerB.won());
		assertTrue(controllerB.lost());
		assertEquals(125, controllerB.getMovesLeft());
		assertFalse(controllerB.getTimeUp());
		assertTrue(controllerB.isGameOver());
		
		Controller controllerC = new Controller(GameModeType.MOVE_LIMIT);
		int[][] matrixC = 	{{0, 0, 0, 0},
				 			 {0, 0, 2, 0},
				 			 {0, 0, 0, 0},
				 			 {0, 0, 0, 0}};
		while (controllerC.getMovesLeft() != 0) {
			controllerC.moveBoardDown();
			createBoard(controllerC, matrixC);
		}
		assertFalse(controllerC.won());
		assertFalse(controllerC.lost());
		assertEquals(0, controllerC.getMovesLeft());
		assertFalse(controllerC.getTimeUp());
		assertTrue(controllerC.isGameOver());
		
		Controller controllerD = new Controller(GameModeType.TIME_LIMIT);
		assertFalse(controllerD.won());
		assertFalse(controllerD.lost());
		assertFalse(controllerD.getTimeUp());
		assertEquals(-1, controllerD.getMovesLeft());
		assertFalse(controllerD.isGameOver());
	}
	
	@Test
	void test_Timer() {

	}
	
	@Test
	void test_getScore() {
		Controller controllerA = new Controller();
		int[][] matrixA = 	{{2, 0, 8, 0},
							 {4, 4, 16, 4},
						 	 {0, 2, 0, 2},
						 	 {2, 0, 32, 32}};
		createBoard(controllerA, matrixA);
		
		int score = controllerA.getScore();
		
		controllerA.moveBoardLeft();
		
		int scoreNew = controllerA.getScore();
		
		assertEquals(76, scoreNew - score);
	}
	
	@Test
	void test_leaderboard() {
		Controller controller = new Controller();
		
		ArrayList<Entry> allScoresI = controller.getAllScores();
		ArrayList<Entry> topScoresI = controller.getTopScores();
		
		controller.addScore("z", 0);
		
		ArrayList<Entry> allScoresF = controller.getAllScores();
		ArrayList<Entry> topScoresF = controller.getTopScores();
		assertEquals(1, allScoresF.size() - allScoresI.size());
		
		if (topScoresF.size() != allScoresF.size()) {
			assertEquals(10, topScoresF.size());
		}
		else {
			assertEquals(allScoresF.size(), topScoresF.size());
		}
	}


}
