package ch.booyakasha.engine;

public interface IGame {
	/**
	 * Request update of the game logic
	 */
	void updateLogic();

	void removeEntity(Entity entity);
}