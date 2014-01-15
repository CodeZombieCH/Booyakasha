package ch.booyakasha.engine;

public class BloodEntity extends Entity {
	private IGame game;

	public BloodEntity(IGame game, String reference, int x, int y) {
		super(reference, x, y);
		this.game = game;
		
		dy = Configuration.getCurrent().enemyVelocity;
	}

	/**
	 * Move the enemy based on time elapsed
	 */
	public void move(long delta) {
		super.move(delta);
		
		// If the blood reached the bottom of the screen, remove it
		if(y > Configuration.getCurrent().screenHeight) {
			game.requestRemoveEntity(this);
		}
	}
}
