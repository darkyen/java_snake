// Them constants help a lot !
class Constants{
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
		GameFrame g_frame = new GameFrame("Snake");
		Game g = new Game();
		g_frame.ctx.game = g;
	}
}