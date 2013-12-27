package ch.booyakasha.engine;

public class BackgroundEntity extends Entity {

	public BackgroundEntity(IGame game, String reference, int x, int y) {
		super(reference, x, y);
		dy = Configuration.getCurrent().backgroundVelocity;
	}

	/**
	 * Move the enemy based on time elapsed
	 */
	public void move(long delta) {
		// update the location of the entity based on move speeds
		y += ((delta * dy) / 1000d);
		if(y > Configuration.getCurrent().screenHeight)
			y = -(4000 + 4000 - Configuration.getCurrent().screenHeight);
	}

	public void doLogic() {
		//dx = -dx;
	}
}
