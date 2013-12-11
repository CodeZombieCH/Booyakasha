package ch.booyakasha.engine;

/**
 * The enemy objects
 */
public class EnemyEntity extends Entity {
	private IGame game;
	private int horizontalBounds = 50;

	public EnemyEntity(IGame game, String reference, int x, int y) {
		super(reference, x, y);
		this.game = game;
		dx = -Configuration.getCurrent().enemyVelocity;
	}

	/**
	 * Move the enemy based on time elapsed
	 */
	public void move(long delta) {
		// If we have reached the left hand side of the screen and are moving left then request a logic update
		if(dx < 0 && x < horizontalBounds) {
			game.updateLogic();
		}
		// If we have reached the right hand side of the screen and are moving right, request a logic update
		if(dx > 0 && x > Configuration.getCurrent().screenWidth - horizontalBounds - sprite.getWidth()) {
			game.updateLogic();
		}

		super.move(delta);
	}

	public void doLogic() {
		dx = -dx;
	}
}
