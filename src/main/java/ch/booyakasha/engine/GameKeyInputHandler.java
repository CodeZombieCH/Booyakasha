package ch.booyakasha.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Handles keyboard input while the game is running.
 */
public class GameKeyInputHandler extends KeyAdapter {
	public class GameKeyInformation {
		private boolean isLeftPressed = false;
		private boolean isRightPressed = false;
		private boolean isFirePressed = false;
		
		public boolean isLeftPressed() {
			return isLeftPressed;
		}
		public void setLeftPressed(boolean isLeftPressed) {
			this.isLeftPressed = isLeftPressed;
		}
		public boolean isRightPressed() {
			return isRightPressed;
		}
		public void setRightPressed(boolean isRightPressed) {
			this.isRightPressed = isRightPressed;
		}
		public boolean isFirePressed() {
			return isFirePressed;
		}
		public void setFirePressed(boolean isFirePressed) {
			this.isFirePressed = isFirePressed;
		}
	}
	
	private GameKeyInformation keyInformation = new GameKeyInformation();
	
	public GameKeyInformation getKeyInformation() {
		return keyInformation;
	}

	/**
	 * Key has been pressed (but not yet released)
	 */
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			keyInformation.isLeftPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			keyInformation.isRightPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			keyInformation.isFirePressed = true;
		}
	} 
	
	/**
	 * Key has been released.
	 */
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			keyInformation.isLeftPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			keyInformation.isRightPressed = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			keyInformation.isFirePressed = false;
		}
	}

	/**
	 * Notification from AWT that a key has been type (pressed and released)
	 */
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			// Quit the game
			System.exit(0);
		}		
	}
}
