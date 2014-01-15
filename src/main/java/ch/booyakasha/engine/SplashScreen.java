package ch.booyakasha.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import ch.booyakasha.resources.Sprite;
import ch.booyakasha.resources.SpriteManager;

public class SplashScreen {
	private Sprite sprite;
	private long lastToggle = 0;
	private boolean visible = false;

	
	public SplashScreen() {
		sprite = SpriteManager.getInstance().getSprite("/sprites/booyakasha.png");	
	}
	
	public void render(Graphics g) {
		sprite.draw(g, 0, 0);
		
		if(lastToggle == 0 || lastToggle + 500 < System.currentTimeMillis()) {
			visible = !visible;
			lastToggle = System.currentTimeMillis();
		}
		
		if(visible) {
			String message = "PRESS [SPACE] TO START";
			
			g.setFont(new Font("Monospaced", Font.BOLD, 24)); 
			g.setColor(new Color(251, 210, 120));
			g.drawString(
					message,
					(Configuration.getCurrent().screenWidth - g.getFontMetrics().stringWidth(message)) / 2,
					Configuration.getCurrent().screenHeight - 50);
		}
	}
}
