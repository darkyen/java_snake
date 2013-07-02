import java.util.*;
import java.awt.*;
import java.awt.geom.*;
// This file manages snake and its parts


// Helper class for Part of a snake
class Part{
	// Position of the part
	public int x;
	public int y;
	// A constructor, more or less its used
	// to save lines.
	Part(int x,int y){
		this.x = x;
		this.y = y;
	}
}

//The snake class
class Snake{
	// Private feilds for snake
	private int length = 10;

	// I manage a deque since to move the snake
	// I simply add one element in the front
	// and remove one from the rear
	private ArrayDeque<Part> parts;
	private int direction = 0;

	// Method to check if the snake ate itself
	private boolean did_i_ate_myself(){
		// Get in iterator to self
		Iterator iter = getPartsIterator();

		// If this is not true, then huston we have a problem...!
		Part snakeHead = (Part)iter.next(); 
		
		// Declare a temporary container
		Part temp_part;
		// Let see if the snake has gone selfivourous 
		// checking whats in its mouth using an iterator
		// iterating over each part
		while( iter.hasNext() ){
			// Gets a part
			temp_part = (Part)iter.next();

			// Checks if we are eating it.
			if( Math.abs(temp_part.x - snakeHead.x) < Constants.block_size &&
				Math.abs(temp_part.y - snakeHead.y ) < Constants.block_size 
			 ){
			 	// Dear God it ate itself, game over time.
				return true;
			}
		}
		// Okay it hasnt ate itself
		return false;
	}
	public void setDirection(int dir){
		this.direction = dir;
	}
	public boolean move(){
		// Where are we gonna go!
		int dx = ((direction == Constants.DIR_RIGHT)?Constants.block_size:((direction == Constants.DIR_LEFT)?-Constants.block_size:0));
		int dy = ((direction == Constants.DIR_DOWN)?Constants.block_size:((direction == Constants.DIR_UP)?-Constants.block_size:0));
		// Get a reference to the current head
		Part head = this.Head();

		// temporary new values
		int new_x = head.x + dx;
		int new_y = head.y + dy;

		// Constrain the values
		new_x %= Constants.WIDTH;
		new_y %= Constants.HEIGHT;
		// on the other side too
		new_x += ((new_x < 0)?Constants.WIDTH:0);
		new_y += ((new_y < 0)?Constants.HEIGHT:0);

		// Add my new head... basically its just adding
		// the new coordinate as the top of Deque
		Part temp = new Part(new_x,new_y);
		this.parts.addFirst(temp);

		// If i am growing why pop ?
		// In case the snake ate his length is to be increased
		// This will be helpful
		if( this.length <= this.parts.size() ){
			parts.removeLast();
		}

		return this.did_i_ate_myself(); // Oh noes on that.. but well game will be over
	}
	public void Eat(Food f){
		// The snake ate some food
		// So it will now grow in size
		this.length += f.quality;
	}

	// Displays the snake
	public void Display(Graphics2D g2){
		Iterator iter = parts.iterator();
		Part temp;
		// Get an iterator of its parts
		// And render each part as a rectangle one after the other
		while( iter.hasNext() ){
			temp = (Part)iter.next();
			g2.drawRect(temp.x,temp.y,Constants.block_size,Constants.block_size);
		}
	}

	// A method to get snake's head
	public Part Head(){
		// My head !
		return (Part)this.parts.getFirst();
	}

	// A method to obtain an iterator for the snake's parts
	public Iterator getPartsIterator(){
		return this.parts.iterator();
	}

	// Public constructor for a snake
	public Snake(){
		// Create a deque
		this.parts = new ArrayDeque<Part>();
		// Add the first element aka snake-head
		this.parts.addFirst(new Part(Constants.WIDTH/2,Constants.HEIGHT/2));
	}
}