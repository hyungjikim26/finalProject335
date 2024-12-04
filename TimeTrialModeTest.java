import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class TimeTrialModeTest {

	TimeTrialMode createTimeLimitBoard(int[][] matrix) {
		TimeTrialMode board = new TimeTrialMode(matrix.length, 0);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				board.addTile(i, j, matrix[i][j]);
			}
		}
		return board;
	}

	@Test
	void testWin() {
		int[][] matrix = { { 2048, 2, 4 }, { 2, 2, 2 }, { 4, 4, 4 } };
		TimeTrialMode board = createTimeLimitBoard(matrix);
		assertTrue(board.isGameOver());
		String msg = board.getGameOverMessage();
		assertEquals(msg, "You win!");
	}

	@Test
	void testTimeLeftLoose() {
		int[][] mat = {{2,4,8}, {16,32,64},{128,256,512}};
		TimeTrialMode b = createTimeLimitBoard(mat);
		assertTrue(b.isGameOver());
		String msg = b.getGameOverMessage();
		assertEquals(msg, "You lose!");
		assertFalse(b.getTimeUp());
	}

	@Test
	void testTimeUpLoose() throws InterruptedException {
	    int[][] mat = { { 2, 2, 4 }, { 2, 2, 4 }, { 2, 2, 4 } };
	    TimeTrialMode board = createTimeLimitBoard(mat);

	    assertFalse(board.isGameOver());
	    
	    board.start(new TimeListener() {
	        @Override
	        public void onTimeUpdate(long remainingTime) {}
	    });

	        Thread.sleep(121000); 
	    assertTrue(board.isGameOver());
	    assertTrue(board.getTimeUp());
	    assertEquals(board.getGameOverMessage(), "Time is up. You lose!");
	}



}
