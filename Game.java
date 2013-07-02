import java.util.*;
import java.awt.*;
import java.awt.event.*;
// This file has all the game logic
// And rendering stuff inside it
// The code is kind of horrible at places
// But its not that bad


class Game{
	// The private fields for the game!
	// The names are self explanatory 
	private boolean Game_Over;
	private int score;
	private Snake player;
	private Food[] foods;

	// The game constructor
	public Game(){
		// Create a snake object
		this.player = new Snake();
		// Create Some* food ( I am using the word some because the code is extensible you can easily create far complex scenarioes using multiple food 
		// keeping this as a base)
		this.foods = new Food[Constants.MAX_NUM_FOOD];
		for( int i = 0; i < Constants.MAX_NUM_FOOD; i ++ ){
			// Create the food, we pass the player reference since we dont
			// want the food to be generated on the player's head
			// that would be a bad jackpot
			// so we check it.
			this.foods[i] = new Food(this.player);
		}
	}

	// Is the game over ?
	// The getter for Game_Over private field
	public boolean isOver(){
		return Game_Over;
	}

	// A function , eventlistener for handling keys
	public void handleKeyDown( KeyEvent e ){
		// Gets which key was pressed
		int key = (e.getKeyCode());
		// This would look better with a switch
		// The code is self explanatiory
		// VK_<XYZ> are special values for assigning keys
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


	// The method to display the game
	public void Display(Graphics2D g2){
		// Display the snake
		player.Display(g2);
		for( int i = 0; i < Constants.MAX_NUM_FOOD; i ++ ){
			// Display the food
			foods[i].Display(g2);
		}
		// Show the score
		g2.drawString(("Score : " + this.score),Constants.WIDTH- 100,Constants.HEIGHT);
	}

	// Tick is a unit execution of game logic,
	// Each tick is one movement of snake
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
						// So change the food and increase the score
						this.player.Eat(this.foods[i]);
						this.score ++;
						this.foods[i] = new Food(this.player); //explained aboe
					}
				}
		}else{
			// For console readers show them they ate themselves to lose
			System.out.println("I ate myself");
		}
	}


}