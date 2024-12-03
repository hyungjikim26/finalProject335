/**
 * File: EntryTest.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose:
 * 
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntryTest {
	Entry entry1;
	Entry entry2;

	@BeforeEach
	void setUp() {
		entry1 = new Entry("A", 100);
		entry2 = new Entry("B", 200);
	}

	@Test
	void testGetName() {
		assertEquals("A", entry1.getName());
		assertEquals("B", entry2.getName());
	}

	@Test
	void testGetScore() {
		assertEquals(100, entry1.getScore());
		assertEquals(200, entry2.getScore());
	}

	@Test
	void testCompareTo() {
		assertEquals(1, entry1.compareTo(entry2));
		assertEquals(-1, entry2.compareTo(entry1));
		assertEquals(0, entry1.compareTo(entry1));
	}

	@Test
	void testToString() {
		assertEquals("A: 100", entry1.toString());
		assertEquals("B: 200", entry2.toString());
	}
}
