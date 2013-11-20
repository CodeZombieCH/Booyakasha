package ch.booyakasha.engine;

/**
* The enemy objects
*/
public class EnemyEntity extends Entity {
        private double moveSpeed = 75;
        private IGame game;
        
        /**
         * Create a new enemy
         */
        public EnemyEntity(IGame game, String reference, int x, int y) {
                super(reference, x, y);
                this.game = game;
                dx = -moveSpeed;
        }

        /**
         * Move the enemy based on time elapsed
         */
        public void move(long delta) {
                // If we have reached the left hand side of the screen and are moving left then request a logic update
                if((dx < 0) && (x < 10)) {
                        game.updateLogic();
                }
                // If we have reached the right hand side of the screen and are moving right, request a logic update
                if((dx > 0) && (x > 750)) {
                        game.updateLogic();
                }
                
                super.move(delta);
        }
        
        /* (non-Javadoc)
         * @see ch.booyakasha.engine.IGame#doLogic()
         */
        public void doLogic() {
                dx = -dx;
        }
}
