package ch.booyakasha.engine;

import java.awt.Graphics;
import ch.booyakasha.resources.Sprite;
import ch.booyakasha.resources.SpriteManager;

/**
 * Base class for all objects on the screen.
 */
public abstract class Entity {
	/**
	 * The current x location of this entity
	 */ 
	protected double x;
	/**
	 * The current y location of this entity
	 */
	protected double y;
	/**
	 * The sprite that represents this entity
	 */
	protected Sprite sprite;
	/**
	 * The current speed of this entity horizontally (pixels/seconds)
	 */
	protected double dx;
	/**
	 * The current speed of this entity vertically (pixels/seconds)
	 */
	protected double dy;

	
	/**
	 * Construct a entity based on a sprite image and a location.
	 * 
	 * @param reference The reference to the image to be displayed for this entity
 	 * @param x The initial x location of this entity
	 * @param y The initial y location of this entity
	 */
	public Entity(String reference, int x, int y) {
		this.sprite = SpriteManager.getInstance().getSprite(reference);
		this.x = x;
		this.y = y;
	}
	
	public void move(long delta) {
		// update the location of the entity based on move speeds
		x += (delta * dx) / 1000;
		y += (delta * dy) / 1000;
	}
	
	/**
	 * Set the horizontal speed of this entity
	 */
	public void setHorizontalMovement(double dx) {
		this.dx = dx;
	}

	/**
	 * Set the vertical speed of this entity
	 */
	public void setVerticalMovement(double dy) {
		this.dy = dy;
	}
	
	/**
	 * Get the horizontal speed of this entity
	 */
	public double getHorizontalMovement() {
		return dx;
	}

	/**
	 * Get the vertical speed of this entity
	 */
	public double getVerticalMovement() {
		return dy;
	}
	
	/**
	 * Draw this entity to the graphics context provided
	 */
	public void draw(Graphics g) {
		sprite.draw(g, (int)x, (int)y);
	}
	
	/**
	 * Get the x location of this entity
	 */
	public int getX() {
		return (int)x;
	}

	/**
	 * Get the y location of this entity
	 */
	public int getY() {
		return (int)y;
	}
	
	/**
	 * Do the logic associated with this entity. This method will be called periodically based on game events
	 */
	public void doLogic() {
	}
}