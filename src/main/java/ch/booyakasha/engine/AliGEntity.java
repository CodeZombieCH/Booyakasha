package ch.booyakasha.engine;

/**
* The Ali G object
*/
public class AliGEntity extends Entity {
        private IGame game;
        
        public AliGEntity(IGame game, String reference, int x, int y) {
                super(reference, x, y);
                this.game = game;
        }
        
        public void move(long delta) {
                // TODO
        }
}
