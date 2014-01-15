package ch.booyakasha.engine;

public interface IGame {
	/**
	 * Request update of the game logic
	 */
	void updateLogic();

	void requestRemoveEntity(Entity entity);
	
	void triggerGameOver();
}