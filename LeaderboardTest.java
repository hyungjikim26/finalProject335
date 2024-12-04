/**
 * File: LeaderboardTest.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: Contains unit tests for the Leaderboard class.
 */

import java.io.File;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LeaderboardTest {
	Leaderboard leaderboard;
	
	@BeforeEach
	void setUp() {
		leaderboard = new Leaderboard();
		leaderboard.clear();
	}

	@Test
	void testGetScores() {
		leaderboard.addScore("A", 100);
		leaderboard.addScore("B", 200);
		leaderboard.addScore("C", 300);
		leaderboard.addScore("D", 400);
		leaderboard.addScore("E", 500);

		ArrayList<Entry> entries = leaderboard.getAllScores();
		assertEquals(5, entries.size());

		assertEquals("E", entries.get(0).getName());
		assertEquals(500, entries.get(0).getScore());
		assertEquals("D", entries.get(1).getName());
		assertEquals(400, entries.get(1).getScore());
		assertEquals("C", entries.get(2).getName());
		assertEquals(300, entries.get(2).getScore());
		assertEquals("B", entries.get(3).getName());
		assertEquals(200, entries.get(3).getScore());
		assertEquals("A", entries.get(4).getName());
		assertEquals(100, entries.get(4).getScore());

		entries = leaderboard.getTopScore();
		assertEquals(5, entries.size());

		leaderboard.addScore("F", 600);
		leaderboard.addScore("G", 700);
		leaderboard.addScore("H", 800);
		leaderboard.addScore("I", 900);
		leaderboard.addScore("J", 1000);
		leaderboard.addScore("K", 1100);

		entries = leaderboard.getAllScores();
		assertEquals(11, entries.size());
		entries = leaderboard.getTopScore();
		assertEquals(10, entries.size());
	}

	@Test
	void testNoFile() {
		File myObj = new File("leaderboard.txt");
		myObj.delete();
		leaderboard = new Leaderboard();
	}
}

