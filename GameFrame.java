import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
// This file accounts
// for all the things that happen while handling the window management
// and makes the game environment window fashioend


// Well they say its better to use a panel and then use it inside the frame
// But since i was doing the basic game it thought
// Doing Jpanel and then Jframe is a waste of coding space & time... it actually worked with JFrame aswell
// Since canvas is low level but then i had problems with spacing and layouut
// Talking about waste of space and time we are in JAVA!!!!
// So finally ended up 


// The Panel for holding the game..
// Cause its time to play the game !!
// Mwahaha... [<insert HHH music here>]
class GamePanel extends JPanel implements ActionListener{
	// Is our window hidden ?
	// We need a canvas
	private boolean is_over;
	public Game game;
	private Timer t;
	public void paint_game(Graphics2D g2){
		if( this.game != null ){
			this.game.Display(g2);
		}
	}
	public void paint_game_over(Graphics2D g2){
		g2.drawString("Game Over",Constants.WIDTH/2 - 60,Constants.HEIGHT/2-5);
	}
	public void paint(Graphics win){
		super.paint(win);
		Graphics2D g2 =(Graphics2D) win;
		this.paint_game(g2);
		if( is_over ){
			this.paint_game_over(g2);
		}
		// Synchronise looks with the canvas
		Toolkit.getDefaultToolkit().sync();
		// Dispose off the events.
     	win.dispose();
	}

	public void actionPerformed(ActionEvent e) {
		// Calls the tick;
		if( this.game != null && !is_over ){
			this.game.tick();
		}
		// Repaint
		is_over = this.game.isOver();
        this.repaint();
    }
    
    // Required for key handling
    // Well the internets said it
    // Maybe ugly java code practise
    // Apologies to all the javatars
    private class KeyBoardAgent extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            game.handleKeyDown(e);
        }
    }
    
    GamePanel(){
		this.setFocusable(true);
    	this.addKeyListener(new KeyBoardAgent());
    	Timer timer = new Timer(50, this);
        timer.start();
	}
}


// The window frame TOP LEVEL OBJECT
// FOR THE GAME
// This provides the magical "close" button!
class GameFrame extends JFrame{
	GamePanel ctx;
	public GameFrame(String title){
		super(title);
		ctx = new GamePanel();
		this.add(ctx);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.white);
		Container c = this.getContentPane();
		Dimension Dim = new Dimension(Constants.WIDTH,Constants.HEIGHT);
		c.setPreferredSize(Dim);
		ctx.setPreferredSize(Dim);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

}
