package ch.booyakasha.engine;

/**
 * The Ali G object
 */
public class AliGEntity extends Entity {
	private IGame game;
	private int horizontalBounds = 50;

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

		super.move(delta);
	}
}
