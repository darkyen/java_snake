// This file is the main god almighty
// Everything starts here
// and ends here... i think... though not sure... err java :-(

// Them constants help a lot !
class Constants{
	//Change this too see drastic changes in gameplay
	static int block_size = 5;
	static int WIDTH = 800;
	static int HEIGHT = 600;
	static int GAME_HEIGHT = HEIGHT - Constants.block_size;
	static int GAME_WIDTH = WIDTH - Constants.block_size;
	static int DIR_RIGHT = 0;
	static int DIR_UP = 1;
	static int DIR_LEFT = 2;
	static int DIR_DOWN = 3;
	static int MAX_QUALITY_FOOD = 4;
	static int MAX_NUM_FOOD = 1;
}

// My object oriented small and
// Sweet Main!
public class main{
	public static void main(String[] args){
		// Make the game window frame and panel
		GameFrame g_frame = new GameFrame("Snake");
		// Give it a new game
		// Todo could be make multiple games
		// and new games
		// and high scores
		// and .. ... ...
		// but

		// AINT NOBODY GOT THE TIME FOR THAT
		// The code is expandable ... use the source!
		// The Force is with you young padawn.... 
		//  ... did i mixed 2 star war qoutes in above line ?
		Game g = new Game();
		g_frame.ctx.game = g;
	}
}