package ch.booyakasha.resources;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * A resource manager for sprites. Loads resources and caches them for performance reasons.
 * Implements the Singleton pattern.
 */
public class SpriteManager {
	/**
	 * Single sprite manager instance (eagerly loaded)
	 */
	private static final SpriteManager instance = new SpriteManager();

	private HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();


	private SpriteManager() {

	}


	/**
	 * Gets the single instance of this class
	 *
	 * @return The single instance of this class
	 */
	public static SpriteManager getInstance() {
		return instance;
	}

	/**
	 * Retrieve a sprite from the store (by taking it from the cache or loading it from the resource file)
	 */
	public Sprite getSprite(String reference) {
		// Check if already in cache
		if(sprites.get(reference) != null) {
			return sprites.get(reference);
		}

		BufferedImage sourceImage = null;

		try {
			URL url = this.getClass().getClassLoader().getResource(reference);

			if(url == null) {
				System.err.println("Can't find resource by reference: " + reference);
			}

			sourceImage = ImageIO.read(url);
		}
		catch(IOException ex) {
			System.err.println("Failed to load resource \"" + reference + "\":" + ex.getMessage());
		}

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);

		image.getGraphics().drawImage(sourceImage, 0, 0, null);

		Sprite sprite = new Sprite(image);

		// Cache sprite
		sprites.put(reference, sprite);

		return sprite;
	}
}
