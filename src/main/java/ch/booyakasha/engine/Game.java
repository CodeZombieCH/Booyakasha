package ch.booyakasha.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.booyakasha.engine.GameKeyInputHandler.GameKeyInformation;

/**
 * Game logic, including game loop
 */
public class Game extends Canvas implements IGame {
	/**
	 * Cached instance of the current configuration
	 */
	private Configuration config;
	private BufferStrategy strategy;
	private GameState state;
	
	private SplashScreen splashScreen = new SplashScreen();
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<ShotEntity> shotEntities = new ArrayList<ShotEntity>();
	private ArrayList<EnemyEntity> enemyEntities = new ArrayList<EnemyEntity>();
	
	private ArrayList<Entity> requestRemoveEntities = new ArrayList<Entity>();
	
	private Entity player;
	private Entity background1;
	private Entity background2;
	private Random rand = new Random();
	private GameKeyInputHandler gameKeyInputHandler;
	private long startTime;
	
	/** Time when the last shot was fired */
	private long lastFired;

	/** True if game logic needs to be applied this loop, normally as a result of a game event */
	private boolean logicRequiredThisLoop = false;
	
	/**
	 * Construct our game and set it running.
	 */
	public Game() {
		config = Configuration.getCurrent();
		
		// TODO: Switch to AWT components, mixing SWING and AWT was not a good idea
		JFrame container = new JFrame("Booyakasha");
		
		JPanel panel = (JPanel)container.getContentPane();
		//panel.setPreferredSize(new Dimension(config.screenWidth, config.screenHeight));
		//panel.setLayout(null);
		
		//setSize(config.screenWidth, config.screenHeight);
		container.setPreferredSize(new Dimension(config.screenWidth, config.screenHeight));
		panel.add(this);
		
		
		// Tell AWT not to bother repainting our canvas since we're going to do that our self in accelerated mode
		setIgnoreRepaint(true);
		
		container.pack();
		container.setResizable(false);
		container.setLocationRelativeTo(null);
		container.setVisible(true);
		
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Add key input handler
		gameKeyInputHandler = new GameKeyInputHandler();
		addKeyListener(gameKeyInputHandler);
				
		requestFocus();

		// Create the buffering strategy which will allow AWT to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		state = GameState.Ready;
	}
	
	/**
	 * Starts a new game
	 */
	private void startGame() {
		entities.clear();
		initEntities();
		startTime = System.currentTimeMillis();
		state = GameState.Running;
	}
	
	private void initEntities() {
		// Create background
		background1 = new BackgroundEntity(this, "/sprites/background.png", 0, -(4000 - config.screenHeight));
		entities.add(background1);
		background2 = new BackgroundEntity(this, "/sprites/background.png", 0, -(4000 + 4000 - config.screenHeight));
		entities.add(background2);
		
		// Create the player object
		player = new AliGEntity(this, "/sprites/alig.gif", 370, 500);
		entities.add(player);
	}
	
	private void spawnEnemies(long deltaSpawn) {
		for(int i = 0; i <= deltaSpawn / 1000; i++) {
			EnemyEntity enemy = new EnemyEntity(this, "/sprites/enemy.png", rand.nextInt(config.screenWidth - 2*config.horizontalPadding) + config.horizontalPadding, -50);
			entities.add(enemy);
			enemyEntities.add(enemy);
		}
	}
	
	/**
	 * Request removal of an entity
	 * @param entity Entity to remove in the game loop
	 */
	public void requestRemoveEntity(Entity entity) {
		requestRemoveEntities.add(entity);
	}
	
	/**
	 * Apply removal requests
	 */
	public void performRemoveEntity() {
		for(Entity entity : requestRemoveEntities) {
			removeEntity(entity);
		}
	}
	
	/**
	 * Remove entity
	 * @param entity Entity to remove
	 */
	private void removeEntity(Entity entity) {
		entities.remove(entity);
		
		if(entity instanceof EnemyEntity) {
			enemyEntities.remove(entity);
		}
		
		if(entity instanceof ShotEntity) {
			shotEntities.remove(entity);
		}
	}
	
	public void tryToFire() {
		// Check for another shot fired within the firing interval
		if(System.currentTimeMillis() - lastFired < config.firingInterval) {
			return;
		}
		
		// All fine, fire a shot
		lastFired = System.currentTimeMillis();
		ShotEntity shot = new ShotEntity(this, "/sprites/shot.gif", player.getX() + 35, player.getY() - 5);
		entities.add(shot);
		shotEntities.add(shot);
	}
	
	/**
	 * Notification from a game entity that the logic of the game should be run
	 */
	public void updateLogic() {
		logicRequiredThisLoop = true;
	}
	
	public void triggerGameOver() {
		state = GameState.Over;
	}

	
	/**
	 * Main game loop
	 */
	public void gameLoop() {
		long lastLoopTime = System.currentTimeMillis();
		long lastSpawnTime = lastLoopTime;
		
		// Keep looping round until the game ends
		while(true) {
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			
			// Get graphic context
			Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
			
			GameKeyInformation keyInfo = gameKeyInputHandler.getKeyInformation();
			
			if(state == GameState.Ready) {
				splashScreen.render(g);
				
				if(keyInfo.isFirePressed()) {
					startGame();
				}
			}
			else if(state == GameState.Running) {
				
				/*
				// Draw street
				g.setColor(new Color(168, 168, 168));
				g.fillRect(0, 0, 800, 600);
				// Draw buildings on both sides
				g.setColor(new Color(139, 69, 19));
				g.fillRect(0, 0, 50, 600);
				g.fillRect(750, 0, 800, 600);
				*/
							
				// Let entities move
				for(int i = 0; i < entities.size(); i++) {
					Entity entity = entities.get(i);
					entity.move(delta);
				}
				
				// Spawn enemies
				long deltaSpawn = (lastLoopTime - lastSpawnTime);
				if(deltaSpawn / 1000 > 1) {
					spawnEnemies(deltaSpawn);
					lastSpawnTime = lastLoopTime;
				}
				
				// Collision detection
				for(EnemyEntity enemy : enemyEntities) {
					for(ShotEntity shot : shotEntities) {
						// Avoid multiple hits
						if(shot.hasHit()) {
							break;
						}
						
						if(enemy.collidesWith(shot)) {
							// Spawn a blood sprite
							entities.add(new BloodEntity(this, "/sprites/blood.png", enemy.getX() - 10, enemy.getY() - 20));
							
							// Request removal of enemy and shot
							requestRemoveEntity(enemy);
							
							shot.markHit();
							requestRemoveEntity(shot);
						}
					}
				}
	
				
				// Remove all requested entities
				performRemoveEntity();
				
				
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
				
				// Handle user input
				double playerVelocity = 0;
				if(keyInfo.isLeftPressed() && !keyInfo.isRightPressed()) {
					// Set left velocity
					playerVelocity = -config.playerVelocity;
				}
				else if(!keyInfo.isLeftPressed() && keyInfo.isRightPressed()) {
					// Set right velocity
					playerVelocity = config.playerVelocity;
				}
				player.setHorizontalMovement(playerVelocity);
				
				if(keyInfo.isFirePressed()) {
					tryToFire();
				}
			}
			else if(state == GameState.Over) {
				String message = "GAME OVER";
				
				g.setFont(new Font("Monospaced", Font.BOLD, 56)); 
				g.setColor(Color.RED);
				g.drawString(message, (800 - g.getFontMetrics().stringWidth(message)) / 2, 250);
				
				if(keyInfo.isFirePressed()) {
					System.exit(0);
				}
			}
			
			g.dispose();
			strategy.show();
			
			// Pause for 10ms --> 100 fps
			try { Thread.sleep(10); } catch (Exception e) {}
		}
	}
	

	public static void main(String argv[]) {
		Configuration.setCurrent(new Configuration());
		
		Game game = new Game();
		game.gameLoop();
	}
}
