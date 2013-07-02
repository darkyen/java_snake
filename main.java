// The main file


// Static Constants Class to creat and use constants in the game.
class Constants{
	//Change this too see drastic changes in gameplay
	static int block_size = 5;
	// Width of the screen
	static int WIDTH = 800;
	// Height of the screen
	static int HEIGHT = 600;
	// Playable Height of the screen
	static int GAME_HEIGHT = Constants.HEIGHT - Constants.block_size;
	// Playable widht of the Screen
	static int GAME_WIDTH = Constants.WIDTH - Constants.block_size;
	// Direction constants
	static int DIR_RIGHT = 0;
	static int DIR_UP = 1;
	static int DIR_LEFT = 2;
	static int DIR_DOWN = 3;
	// Food related constants
	static int MAX_QUALITY_FOOD = 4;
	static int MAX_NUM_FOOD = 1;
}

// The main class with the entry point
public class main{
	public static void main(String[] args){
		// Make the game window frame and panel
		GameFrame g_frame = new GameFrame("Snake");
		// Since game is dynamically assigned and created
		// this code could be used as a base to create multiple
		// Games or further more complex code
		// But since my example was supposed to be simple i kept it simple.
		Game g = new Game();
		g_frame.ctx.game = g;
	}
}