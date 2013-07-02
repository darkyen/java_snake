import java.util.*;
import java.awt.*;
import java.awt.geom.*;
// This file has the food definition and declarations

// a class for Food!
class Food{
	// Position feilds for the food
	public int x;
	public int y;
	// The quality of food, long snakes eat good food!
	public int quality;

	// Private helper function for randomization
	private void randomize(){
		// Dont ... ever ... do (int) Math.random() * Something... 
		// That will cause horrific error because it will convert Math.random() to int which is a 
		// Garunteed zero

		// Generate some random data for the food
		this.x = (int )(Math.random() * (Constants.GAME_WIDTH));
		this.y = (int )(Math.random() * (Constants.GAME_HEIGHT));
		this.quality = (int)(Math.random() * (Constants.MAX_QUALITY_FOOD)) + 1;	
	}

	//A private helper function for collision detection
	private boolean coll_det(int x,int y){
		return (Math.abs(x - this.x) < Constants.block_size
			&& Math.abs(y - this.y) < Constants.block_size);
	}

	// Default constructor
	public Food(){
		this.randomize();
	}

	// Parameterized constructor which takes the snake
	// The aim is to not generate new food on the top of the snake
	public Food( Snake s){
		boolean collision = false;

		// Good example of a do-while loop though!
		do{
			collision = false;
			// Generate food
			this.randomize();

			// Obtain an iterator for snake's parts
			Iterator snakerator = s.getPartsIterator();
			Part part;
			// Iterate over all the parts to see if our new point is
			// over some of the snake part.
			while( snakerator.hasNext() ){
				part = (Part)snakerator.next();
				collision = this.coll_det( part.x, part.y );
				if( collision ){
					// If there is collision then we must break out
					// Check it again is un-necessary and error causing.
					break;
				}
			}
		}while(collision);
	}

	// Was the food already eaten ? (In reality being eaten)
	public Boolean isConsumed(Snake s){
		Part snakeHead = s.Head();
		if(this.coll_det( snakeHead.x, snakeHead.y )){
			// Yes.
			return true;
		}
		// No
		return false;
	}

	// Public method to display the Food
	// It plots a rectangle at its position
	public void Display(Graphics2D g2){
		g2.drawRect( this.x, this.y, Constants.block_size, Constants.block_size );
	}
}