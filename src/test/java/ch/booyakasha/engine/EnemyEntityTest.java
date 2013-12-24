package ch.booyakasha.engine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EnemyEntityTest {
	
	private EnemyEntity enemy;

	@Before
	public void setUp() throws Exception {
		Configuration.setCurrent(new Configuration());
		Game game = new Game();
		enemy = new EnemyEntity(game, "/sprites/enemy.gif", 300, 250);
	}

	@Test
	public void testMove() {
		enemy.setHorizontalMovement(50);
		enemy.move(500);
		assertEquals(325, enemy.getX());
	}

	@Test
	public void testSetHorizontalMovement() {
		enemy.setHorizontalMovement(100);
		assertEquals(100, enemy.getHorizontalMovement(), 0);
	}

	@Test
	public void testSetVerticalMovement() {
		enemy.setVerticalMovement(100);
		assertEquals(100, enemy.getVerticalMovement(), 0);
	}

	@Test
	public void testGetHorizontalMovement() {
		assertEquals(-100, enemy.getHorizontalMovement(), 0);
	}

	@Test
	public void testGetVerticalMovement() {
		assertEquals(0, enemy.getVerticalMovement(), 0);
	}

	@Test
	public void testGetX() {
		assertEquals(300, enemy.getX());
	}

	@Test
	public void testGetY() {
		assertEquals(250, enemy.getY());
	}
	
	@Test
	public void testDoLogic() {
		assertEquals(-100, enemy.dx, 0);
		enemy.doLogic();
		assertEquals(100, enemy.dx, 0);
		
	}

}
