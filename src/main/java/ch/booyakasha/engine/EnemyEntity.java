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
		dy = Configuration.getCurrent().enemyVelocity;
	}

	/**
	 * Move the enemy based on time elapsed
	 */
	public void move(long delta) {
		super.move(delta);
		
		// If the enemy reached the bottom of the screen, request game over
		if(y > Configuration.getCurrent().screenHeight - 32) {
			game.triggerGameOver();
		}
	}
}
