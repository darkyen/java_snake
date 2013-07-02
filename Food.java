import java.util.*;
import java.awt.*;
import java.awt.geom.*;
// This file has the food.. the snakes apetite

// a class for Food!
class Food{
	// Coordinate geometry basics ... if u dont know them ... err GET A MATH BOOK ASAP!
	public int x;
	public int y;
	// The quality of food, long snakes eat good food!
	public int quality;
	private void randomize(){
		// Dont ... ever ... do (int) Math.random() * Something... causes horrific errors......

		// Generate some random data for the food
		this.x = (int )(Math.random() * (Constants.GAME_WIDTH));
		this.y = (int )(Math.random() * (Constants.GAME_HEIGHT));
		this.quality = (int)(Math.random() * (Constants.MAX_QUALITY_FOOD)) + 1;	
	}
	private boolean coll_det(int x,int y){
		return (Math.abs(x - this.x) < Constants.block_size
			&& Math.abs(y - this.y) < Constants.block_size);
	}
	public Food(){
		// Yeah the default constructor
		// Never used but ... is default
		// so awesome .. irony!
		this.randomize();
	}

	public Food( Snake s){
		// Generate food until its not on the top of the snakes head 0r body 
		// I mean it would be cheatin to give it straight in his mouth
		// SO MAKE IT A CHALLENGE!
		boolean collision = false;

		// Good example of a do-while loop though!
		do{
			collision = false;
			this.randomize();
			Iterator snakerator = s.getPartsIterator();
			Part part;
			while( snakerator.hasNext() ){
				part = (Part)snakerator.next();
				collision = this.coll_det( part.x, part.y );
				if( collision ){
					// Break halps you save complexity! how ? 
					break;
				}
			}
		}while(collision);
	}
	// Was i eaten already?
	// Is my existance a lie!
	public Boolean isConsumed(Snake s){
		Part snakeHead = s.Head();
		if(this.coll_det( snakeHead.x, snakeHead.y )){
			// Huston ..... i r dead!
			return true;
		}
		// I am alive
		return false;
	}
	public void Display(Graphics2D g2){
		// Show the yum yum yum!
		g2.drawRect( this.x, this.y, Constants.block_size, Constants.block_size );
	}
}