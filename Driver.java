import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener { //add more things if you need to
	
	Court court;
	Hoops right, left;
	Player Player1;
	Basketball ball;
	Music music;
	Clip c;
	
	int screen_width = 1350;
	int screen_height = 660;
	int court_floor = 410;
	int court_wall = 1090;
	
	int radius = 50;//size
	int y = 0; //ball top left corner
	int x = 300; //ball top left corner
	int dx = 10; //velocity in the x
	int dy = 10; //velocity in the y
	
	double gravity =15;
	double energyloss = .65;
	double dt = .2;
	
	/**
	 * Each of the objects that are to appear on-screen
	 * have their own paint method which is called
	 * below.
	 */
	public void paint(Graphics g) {
		super.paintComponents(g);
		
		// painting the board png
		court.paint(g);
		right.paint(g);
		left.paint(g);
		Player1.paint(g);
		ball.paint(g);
		
		
	}
	/**
	 * Update Method: basketball bounce formula
	 * Ball bounces on the court floor, and rises
	 * to a smaller height after every bounce
	 */
	public void update() {
		//fix ball paint method so that it doesn't repaint the ball after every iteration of the below loops
		
		/*if(x+dx > court_wall - radius-1){
			x = court_wall-radius-1;
			dx = -dx;
		}else if(x+dx < 100){
			x = 100;
			dx = -dx;
		}else{
			x+=dx;
		}
		
		if(y == court_floor - radius -21){
			dx *= .95;
			if(Math.abs(dx) < .1){
				dx =0;
			}
		}
		
		if(y > court_floor - radius -21){
			y = court_floor - radius-21;
			music.playSong();
			dy *= energyloss;
			dy = -dy;
		}else{
			dy+= gravity*dt;
			y += dy*dt + .5*gravity*dt*dt;
		}
		
		if(y == court_floor - radius -21 && Math.abs(dy) < 5){
			dy = 0;
			y = court_floor - radius - 21;
		}
		
		ball.setX(x);
		ball.setY(y);
		*/
		
		ball.update();

	}
	
	
	// ============================ code above
		// ==========================================
		@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
		
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Driver d = new Driver();

	}
	/**
	 * All on-screen objects are instantiated below in the Driver constructor
	 */
	public Driver(){
		JFrame f = new JFrame();
		f.setTitle("Basketballers");
		f.setSize(screen_width, screen_height);
		f.getContentPane().setBackground(Color.BLACK);
		
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseListener(this);
		
		court = new Court("basketballCourt.png");
		right = new Hoops("rightHoop.png", "right");
		left = new Hoops("leftHoop.png", "left");
		Player1 = new Player("runn.png");
		ball = new Basketball("basketball.jpg");
		music = new Music("BOUNCE+1.wav", c);
		
		f.add(this);

		// end creating objects
		t = new Timer(100, this);

		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getX());
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}