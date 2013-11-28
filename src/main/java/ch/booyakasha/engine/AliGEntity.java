package ch.booyakasha.engine;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

/**
* The Ali G object
*/
public class AliGEntity extends Entity {
		private long moveSpeed = 20;
        private IGame game;
        
        public AliGEntity(IGame game, String reference, int x, int y) {
                super(reference, x, y);
                this.game = game;
        }
        
        public void move(long delta) {
        	PointerInfo a = MouseInfo.getPointerInfo();
        	Point b = a.getLocation();
        	long x = (long) b.getX();
        	int pos = game.getPanelLocationX();
        	if(x-pos > super.x && super.x < 765){
        		dx = 100;
        		super.move(moveSpeed);
        	}
        	if(x-pos < super.x && super.x > 0){
        		dx = -100;
        		super.move(moveSpeed);
        	} 	
        }
}
