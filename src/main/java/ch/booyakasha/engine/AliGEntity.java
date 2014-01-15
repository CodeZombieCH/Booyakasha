package ch.booyakasha.engine;

import ch.booyakasha.resources.SpriteManager;

/**
 * The Ali G object
 */
public class AliGEntity extends Entity {
	private IGame game;
	private int horizontalBounds = 50;
	private long toggleIntervalRemaining = 500;
	private int spriteState = 0;

	public AliGEntity(IGame game, String reference, int x, int y) {
		super(reference, x, y);
		this.game = game;
	}

	public void move(long delta) {
		// Check bounds
		if(dx < 0 && x < horizontalBounds) {
			return;
		}

		if(dx > 0 && x > Configuration.getCurrent().screenWidth - horizontalBounds - sprite.getWidth()) {
			return;
		}

		toggleIntervalRemaining -= delta;
		if(toggleIntervalRemaining < 0) {
			spriteState = (spriteState + 1) % 2;
			this.sprite = SpriteManager.getInstance().getSprite("/sprites/alig_" + (spriteState + 1) + ".png");
			toggleIntervalRemaining = 500;
		}
		
		super.move(delta);
	}
}
