import java.util.*;
import java.awt.*;
import java.awt.geom.*;
// Helper class for Part
class Part{
	public int x;
	public int y;
	Part(int x,int y){
		this.x = x;
		this.y = y;
	}
}

// Snake... hisssssssss!
// For my English && Non_Indian_Readers
// Snake ... Rattleeeee!!
class Snake{
	private int length = 10;
	private ArrayDeque<Part> parts;
	private int direction = 0;

	private boolean did_i_ate_myself(){
		// Get in iterator to self
		Iterator iter = getPartsIterator();

		// If this is not true, then huston we have a problem...!
		Part snakeHead = (Part)iter.next(); 
		
		// Declare a temporary container
		Part temp_part;
		// Let see if i am trying to be selfivourous 
		// checking if i eat each and every of my part
		while( iter.hasNext() ){
			// I has a part
			temp_part = (Part)iter.next();
			if( Math.abs(temp_part.x - snakeHead.x) < Constants.block_size &&
				Math.abs(temp_part.y - snakeHead.y ) < Constants.block_size 
			 ){
			 	// Oh hey hey hey
				// I EAT MYSELF!! NOWWWWWWWWWW
				return true;
			}
		}
		return false;
	}
	public void setDirection(int dir){
		this.direction = dir;
	}
	public boolean move(){
		// Where are we gonna go!
		int dx = ((direction == Constants.DIR_RIGHT)?Constants.block_size:((direction == Constants.DIR_LEFT)?-Constants.block_size:0));
		int dy = ((direction == Constants.DIR_DOWN)?Constants.block_size:((direction == Constants.DIR_UP)?-Constants.block_size:0));
		// Get my head
		Part head = this.Head();

		// temporary new values
		int new_x = head.x + dx;
		int new_y = head.y + dy;

		// Lets stay in bounds
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
		if( this.length <= this.parts.size() ){
			parts.removeLast();
		}

		return this.did_i_ate_myself(); // Oh noes on that.. but well game will be over
	}
	public void Eat(Food f){
		// Yum Yum BURP!
		length += f.quality;
		// Now i increase my length; 
	}
	public void Display(Graphics2D g2){
		// I must show myself... i am a snake! 
		Iterator iter = parts.iterator();
		Part temp;
		while( iter.hasNext() ){
			temp = (Part)iter.next();
			g2.drawRect(temp.x,temp.y,Constants.block_size,Constants.block_size);
		}
	}
	public Part Head(){
		// My head !
		return (Part)this.parts.getFirst();
	}
	public Iterator getPartsIterator(){
		// If others want to know about my body they need
		// to have an iterator of my private part(s) ;D ;D pun intended :D 
		return this.parts.iterator();
	}
	public Snake(){
		// Create a new part... only the head actually.
		this.parts = new ArrayDeque<Part>();
		this.parts.addFirst(new Part(Constants.WIDTH/2,Constants.HEIGHT/2));
	}
}