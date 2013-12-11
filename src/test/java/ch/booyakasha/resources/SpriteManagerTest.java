package ch.booyakasha.resources;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class SpriteManagerTest {
	private SpriteManager manager;

	@Before
	public void setUp() throws Exception {
		manager = SpriteManager.getInstance();
	}

	@Test
	public void testGetSprite() {
		// Check gif
		Sprite sprite = manager.getSprite("/sprites/test.gif");
		
		assertEquals(10, sprite.getWidth());
		assertEquals(20, sprite.getHeight());

		// Check png
		sprite = manager.getSprite("/sprites/test.png");
		
		assertEquals(10, sprite.getWidth());
		assertEquals(20, sprite.getHeight());
	}
}
