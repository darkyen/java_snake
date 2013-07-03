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

// Explanation of how the game works
// Our game works using the ticks architecture
// We make a timer which calls a function for changing the state of the game
// every 140ms , after the game state is changed. these changes are then
// printed on the screen.

// The whole game runs in 1 single thread
// So we cant use threads
// Hence i have used a timer


// 101 on event based programming
// You eat food when you are hungry
// you dont usually keep waiting for the food to come
// you do all your task of the day and when and only when you are hungry you eat food
// Another example a teacher has to give an assignment to 10 students
// Either he can give assignments to 1 student at a time and wait for them to complete
// Then when the submit check and move on to the next one.
// Or he can give assignments to all the students at a time
// as the student finish it they can submit them to the teacher
// He can check them and assign marks
// The former will take months to complete even if each student takes 10 days at max to submit
// But the latter can be done in a month even if a student takes 30 days at max.
// The same thing happens in our game
// The game state is changed after 140 ms
// During this time the game can still listen for keyboard changes
// And handle them accordingly
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