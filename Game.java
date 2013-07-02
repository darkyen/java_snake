import java.util.*;
import java.awt.*;
import java.awt.event.*;

// Make the rendering loop and logic loop a differunt thread ladies!
// Now this way we move without waiting for input
class Game{
	// The privates for the game!
	private boolean Game_Over;
	private int score;
	private Snake player;
	private Food[] foods;
	
	// The game constructor
	public Game(){
		// This is a snake game so lets make a snake!
		this.player = new Snake();
		// Some apetizers would be nice
		this.foods = new Food[Constants.MAX_NUM_FOOD];
		for( int i = 0; i < Constants.MAX_NUM_FOOD; i ++ ){
			// Dont make it on the player.. it'd be and error... its a snake
			// Insects run from it its a terror... yeah bad rhyme
			this.foods[i] = new Food(this.player);
		}
	}
	// Is it over already ?? NOOOOOOOOOOOO!
	public boolean isOver(){
		return Game_Over;
	}
	// Lets handle them keys!
	public void handleKeyDown( KeyEvent e ){
		// Gets which key was pressed
		int key = (e.getKeyCode());
		// This would look better with a switch
		// But i am too lasy to do so.
		if( key == KeyEvent.VK_LEFT ){
			this.player.setDirection(Constants.DIR_LEFT);
		}
		if( key == KeyEvent.VK_DOWN ){
			this.player.setDirection(Constants.DIR_DOWN);
		}
		if( key == KeyEvent.VK_UP ){
			this.player.setDirection(Constants.DIR_UP);
		}
		if( key == KeyEvent.VK_RIGHT ){
			this.player.setDirection(Constants.DIR_RIGHT);
		}
	}
	// And then we shall make everything visible
	public void Display(Graphics2D g2){
		// Show the venemous
		player.Display(g2);
		for( int i = 0; i < Constants.MAX_NUM_FOOD; i ++ ){
			// Show the innocent(S)
			foods[i].Display(g2);
		}
		// Tell them how much burpin they have done!
		g2.drawString(("Score : " + this.score),Constants.WIDTH- 100,Constants.HEIGHT);
	}

	// Tick is a unit execution of game logic
	public void tick(){
		//Do it ony if the game is not over!
		if(!this.Game_Over){
				// Move the snake
				this.Game_Over = this.player.move();
				// Check the food
				for( int i = 0; i < Constants.MAX_NUM_FOOD; i ++ ){
					//Is the snake eating ?
					if(this.foods[i].isConsumed(this.player)){
						// The snake ate
						// So change world!
						this.player.Eat(this.foods[i]);
						this.score ++;
						this.foods[i] = new Food(this.player); // Dont make it on the top of player.
					}
				}
		}else{
			// EHOOOOOOOOOOOOOOOO!
			// For my console friendly kids read the console :D
			System.out.println("I ate myself");
		}
	}


}