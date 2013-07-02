import java.util.*;
import java.awt.*;
import java.awt.geom.*;

class Food{
	public int x;
	public int y;
	public int quality;
	private void randomize(){
		// Dont ... ever ... do (int) Math.random() * Something... causes horrific errors..

		this.x = (int )(Math.random() * (Constants.GAME_WIDTH));
		this.y = (int )(Math.random() * (Constants.GAME_HEIGHT));
		this.quality = (int)(Math.random() * (Constants.MAX_QUALITY_FOOD));	
	}
	private boolean coll_det(int x,int y){
		return (Math.abs(x - this.x) < Constants.block_size
			&& Math.abs(y - this.y) < Constants.block_size);
	}
	public Food(){
		this.randomize();
	}

	public Food( Snake s){
		boolean collision = false;
		do{
			collision = false;
			this.randomize();
			Iterator snakerator = s.getPartsIterator();
			Part part;
			while( snakerator.hasNext() ){
				part = (Part)snakerator.next();
				collision = this.coll_det( part.x, part.y );
				if( collision ){
					break;
				}
			}
		}while(collision);
	}
	public Boolean isConsumed(Snake s){
		Part snakeHead = s.Head();
		if(this.coll_det( snakeHead.x, snakeHead.y )){
			return true;
		}
		return false;
	}
	public void Display(Graphics2D g2){
		g2.drawRect( this.x, this.y, Constants.block_size, Constants.block_size );
	}
}