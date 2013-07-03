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
// Our Snake is basically a double ended queue of parts, with a field direction and length
// Each part is of class Part and has an x and y value
// What is Deque ?
// Imagine a queue in front of a ticket window
// People come and they stand at the back
// The window keeper entertains the front person
// This is an example of a queue
// Imagine a queue where people can come from both ends
// and go from both ends
// Example a group of students making a voluntary line
// They can come and go from both the ends ( say they cant move from center )
// That will be an example of the deque
// The reason i kept the snake a deque structure
// is because to move the snake i compute the next position of the head
// of the snake using the Top element of the deque then i create a new element
// now i will insert this element in the front of the deque 
// The last element can be removed and hence just by an insert and remove
// I get the movement of the snake
// This also allows me to have a snake of dynamic length
// If the snake is lesser in length then the maximum size of snake possible
// we will just skip removing on that instance.
class Snake{
	// Private feilds for snake
	private int length = 10;

	// I manage a deque since to move the snake
	// I simply add one element in the front
	// and remove one from the rear
	private ArrayDeque<Part> parts;
	private int direction = 0;

	// Method to check if the snake ate itself
	// If the position of head is same
	// as the same position of any other
	// part in the snake then he is eating himself
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
			// The current collision we use is very simple
			// if two objects are colliding then the absolute difference of their
			// position coordinates must be less then the width of the smaller object
			// Try drawing two rectangles and test this theory if you still dont undrstand
			// since by constrains of our game we use blocks of fixed size we check it
			// against block_size

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

	// This method is used to move the snake
	// At the end of its execution this will call the check
	// for eating itself
	// this is more or less shortand
	// since we need to check the rules after every move
	// we call the did i ate myself from the move function
	public boolean move(){
		// The unit movement of snake in one tick
		// or one movement is the block_size.
		// Dx is the change in X position
		// Dy is change in y position
		// Depending on the directions we compute the change in positions
		// The following block uses ? : The conditional operator

		// If Direction is Right then set dx to Constants.block_size else if its left then set it to -1 * Constants.block_Size else zero;
		int dx = ((direction == Constants.DIR_RIGHT)?Constants.block_size:((direction == Constants.DIR_LEFT)?-Constants.block_size:0));
		// Same as above line but on y axis
		int dy = ((direction == Constants.DIR_DOWN)?Constants.block_size:((direction == Constants.DIR_UP)?-Constants.block_size:0));
		// Get a reference to the current head
		Part head = this.Head();

		// temporary new values
		// of x and y
		// The new values of head will be current top elements X & Y
		// added with dy and dx;

		int new_x = head.x + dx;
		int new_y = head.y + dy;

		// Constrain the values
		// We dont want a value greater then Width at any case
		// we dont want a value greter then height at any case.
		new_x %= Constants.WIDTH; // new_x = new_x % Constants.Width; this will make sure that new_x is always among -Constants.Width - Constants.Width
		new_y %= Constants.HEIGHT;
		// on the other side too
		// If the new position is lower then Zero then we add it with Constants
		// Since upper modulas operation already constrains it in the limit hence
		// adding the padding makes sure it is > 0;
		new_x += ((new_x < 0)?Constants.WIDTH:0);
		new_y += ((new_y < 0)?Constants.HEIGHT:0);

		// Add my new head... basically its just adding
		// the new coordinate as the top of Deque
		Part temp = new Part(new_x,new_y);
		this.parts.addFirst(temp);

		// If i am growing why pop ?
		// In case the snake ate his length is to be increased
		// In that case the snake will not remove his tail element
		// by doing so his length increases.
		if( this.length <= this.parts.size() ){
			parts.removeLast();
		}
		// Check if the movement caused a disaster
		return this.did_i_ate_myself(); // Oh noes on that.. but well game will be over
	}
	public void Eat(Food f){
		// The snake ate some food
		// So it will now grow in size
		this.length += f.quality;
	}

	// Displays the snake
	public void Display(Graphics2D g2){
		// Get an iterator of its parts
		Iterator iter = this.parts.iterator();
		Part temp;
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