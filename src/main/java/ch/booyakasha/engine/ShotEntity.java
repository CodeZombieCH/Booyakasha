package ch.booyakasha.engine;

/**
 * A shot fired by Ali G
 */
public class ShotEntity extends Entity {
	private double moveSpeed = -300;
	private IGame game;
	
	/**
	 * Create a new shot
	 * 
	 * @param game The game in which the shot has been created
	 * @param sprite The sprite representing this shot
	 * @param x The initial x location of the shot
	 * @param y The initial y location of the shot
	 */
	public ShotEntity(IGame game, String reference, int x, int y) {
		super(reference, x, y);
		
		this.game = game;
		
		dy = moveSpeed;
	}

	/**
	 * Request that this shot moved based on time elapsed
	 * 
	 * @param delta The time that has elapsed since last move
	 */
	public void move(long delta) {
		super.move(delta);
		
		// Remove entity if out of the screen
		if(y < -50) {
			game.requestRemoveEntity(this);
		}
	}
}