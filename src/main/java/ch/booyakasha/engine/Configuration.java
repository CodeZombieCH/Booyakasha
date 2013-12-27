package ch.booyakasha.engine;

public class Configuration {
	private static Configuration current;
	
	public int screenHeight = 600;
	public int screenWidth = 800;
	
	/**
	 * Player velocity [pixels/sec]
	 */
	public double playerVelocity = 300;
	public double enemyVelocity = 20;
	public double initialEnemyVerticalVelocity = 50;
	public double backgroundVelocity = 300;
	
	
	public Configuration() {
		
	}
	
	public static Configuration getCurrent() {
		return current;
	}
	
	public static void setCurrent(Configuration config) {
		current = config;
	}
}
