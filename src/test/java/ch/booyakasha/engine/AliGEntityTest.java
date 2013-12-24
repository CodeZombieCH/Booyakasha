package ch.booyakasha.engine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AliGEntityTest {
	
	private AliGEntity alig;

	@Before
	public void setUp() throws Exception {
		Configuration.setCurrent(new Configuration());
		Game game = new Game();
		alig = new AliGEntity(game, "/sprites/alig.gif", 100, 500);
	}

	@Test
	public void testMove() {
		alig.setHorizontalMovement(50);
		alig.move(500);
		assertEquals(125, alig.getX());
	}

	@Test
	public void testSetHorizontalMovement() {
		alig.setHorizontalMovement(200);
		assertEquals(200, alig.getHorizontalMovement(), 0);
	}

	@Test
	public void testSetVerticalMovement() {
		alig.setVerticalMovement(200);
		assertEquals(200, alig.getVerticalMovement(), 0);
	}

	@Test
	public void testGetHorizontalMovement() {
		assertEquals(0, alig.getHorizontalMovement(), 0);
	}

	@Test
	public void testGetVerticalMovement() {
		assertEquals(0, alig.getVerticalMovement(), 0);
	}

	@Test
	public void testGetX() {
		assertEquals(100, alig.getX());
	}

	@Test
	public void testGetY() {
		assertEquals(500, alig.getY());
	}
}
