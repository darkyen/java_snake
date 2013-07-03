import java.util.*;
import java.awt.*;
import java.awt.geom.*;
// This file has the food definition and declarations
// A food object has an x position
// a y position
// and a quality , the quality of food decides how much the length of snake will increase
// when he eats it.

// When generating the food we dont want the food to be generated
// on the top of the snake and hence there is a special constructor
// which ensures our random generation of food doesnt creates food on the top of the snake

class Food{
	// Position feilds for the food
	public int x;
	public int y;
	// The quality of food, long snakes eat good food!
	public int quality;

	// Private helper function for randomization
	// This sets all the field values to random
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
	// The current collision we use is very simple
	// if two objects are colliding then the absolute difference of their
	// position coordinates must be less then the width of the smaller object
	// Try drawing two rectangles and test this theory if you still dont undrstand
	// since by constrains of our game we use blocks of fixed size we check it
	// against block_size
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

		// Good example of a do-while loop
		// This generates a food once then checks for the collision
		// If collision occurs then it re-creates the food
		// and checks for collision in the snake body 
		// This happens until food is generated in such a way that it 
		// doesn't overlaps the snake.
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
		// If the snake head is colliding with the food
		// then the food is consumed already
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