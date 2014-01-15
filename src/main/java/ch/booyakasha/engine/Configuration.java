package ch.booyakasha.engine;

public class Configuration {
	private static Configuration current;
	
	public int screenHeight = 600;
	public int screenWidth = 800;
	
	public int horizontalPadding = 80;
	
	/**
	 * Player velocity [pixels/sec]
	 */
	public double playerVelocity = 300;
	public double enemyVelocity = 40;
	public double initialEnemyVerticalVelocity = 50;
	public double backgroundVelocity = 20;
	
	public int firingInterval = 300;
	
	
	public Configuration() {
		
	}
	
	public static Configuration getCurrent() {
		return current;
	}
	
	public static void setCurrent(Configuration config) {
		current = config;
	}
}
