package ch.booyakasha.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Game logic, including game loop
 */
public class Game extends Canvas implements IGame {
	private BufferStrategy strategy;
	private boolean gameRunning = true;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Entity player;
	
	/** True if game logic needs to be applied this loop, normally as a result of a game event */
	private boolean logicRequiredThisLoop = false;
	
	/**
	 * Construct our game and set it running.
	 */
	public Game() {
		JFrame container = new JFrame("Booyakasha");
		
		JPanel panel = (JPanel)container.getContentPane();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);
		
		setBounds(0, 0, 800, 600);
		panel.add(this);
		
		// Tell AWT not to bother repainting our canvas since we're going to do that our self in accelerated mode
		setIgnoreRepaint(true);
		
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		requestFocus();

		// Create the buffering strategy which will allow AWT to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		startGame();
	}
	
	/**
	 * Starts a new game
	 */
	private void startGame() {
		entities.clear();
		initEntities();
	}
	
	private void initEntities() {
		// Create the player object
		player = new AliGEntity(this, "sprites/alig.gif", 370, 550);
		entities.add(player);
		
		// Create enemies
		for(int row = 0; row < 5; row++) {
			for(int x = 0; x < 12; x++) {
				Entity enemy = new EnemyEntity(this, "sprites/enemy.gif", 100 + (x * 50), 50 + row * 36);
				entities.add(enemy);
			}
		}
	}
	
	/**
	 * Notification from a game entity that the logic of the game should be run
	 */
	public void updateLogic() {
		logicRequiredThisLoop = true;
	}

	
	/**
	 * Main game loop
	 */
	public void gameLoop() {
		long lastLoopTime = System.currentTimeMillis();
		
		// Keep looping round until the game ends
		while(gameRunning) {
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			
			// Get hold of a graphics context for the accelerated 
			// surface and blank it out
			Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);
			
			// Let entities move
			for(int i = 0; i < entities.size(); i++) {
				Entity entity = entities.get(i);
				entity.move(delta);
			}
			
			// Draw all entities
			for(int i = 0; i < entities.size(); i++) {
				Entity entity = entities.get(i);
				entity.draw(g);
			}

			if(logicRequiredThisLoop) {
				for (int i=0;i<entities.size();i++) {
					Entity entity = entities.get(i);
					entity.doLogic();
				}
				
				logicRequiredThisLoop = false;
			}
			
			g.dispose();
			strategy.show();
			
			// Pause for 10ms --> 100 fps
			try { Thread.sleep(10); } catch (Exception e) {}
		}
	}
	

	public static void main(String argv[]) {
		Game game = new Game();
		game.gameLoop();
	}
}
