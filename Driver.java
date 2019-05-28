import java.awt.Color;
import javax.sound.sampled.Clip;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * 
 * @authors annabelNg, jaidenSmith, scottyKrysl, minjunKim
 */
public class Driver extends JPanel implements ActionListener, KeyListener { // add more things if you need to

	/**
	 * Sets up the different objects
	 */
	Court court;
	Hoops right, left;
	Player Player1;
	Player Player2;
	Basketball ball;
	
	//Music music;
	//Clip c;
	int screen_width = 1350;
	int screen_height = 660;

	int court_floor = 410;
	int court_wall = 1090;

	int radius = 50;// size
	int y = 0; // ball top left corner
	int x = 300; // ball top left corner
	int dx = 10; // velocity in the x
	int dy = 10; // velocity in the y

	double gravity = 15;
	double energyloss = .65;
	double dt = .2;

	/**
	 * Paints and repaints the images on the board rather than just adding the
	 * images to the JPanel
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		super.paintComponents(g);

		// painting the board png
		court.paint(g);
		right.paint(g);
		left.paint(g);
		ball.paint(g);
		/*
		 * if(Player1.getRun()==true) { Player1.run(g); Player1.setRun(false); }else {
		 * Player1.paint(g); }
		 * 
		 * if(Player2.getRun()==true) { Player2.run(g); Player2.setRun(false); }else {
		 * Player2.paint(g); }
		 */
		if (p1L || p1R) {
			Player1.run(g);
			Player1.setRun(false);
		} else {
			Player1.paint(g);
		}

		if (p2R || p2L) {
			Player2.run(g);
			Player2.setRun(false);
		} else {
			Player2.paint(g);
		}

	}

	public void update() {
		// fix ball paint method so that it doesn't repaint the ball after every
		// iteration of the below loops

		if (x + dx > court_wall - radius - 1) {
			x = court_wall - radius - 1;
			dx = -dx;
		} else if (x + dx < 100) {
			x = 100;
			dx = -dx;
		} else {
			x += dx;
		}

		if (y == court_floor - radius - 21) {
			dx *= .95;
			if (Math.abs(dx) < .1) {
				dx = 0;
			}
		}

		if (y > court_floor - radius - 21) {
			y = court_floor - radius - 21;
			//music.playSong();
			dy *= energyloss;
			dy = -dy;
		} else {
			dy += gravity * dt;
			y += dy * dt + .5 * gravity * dt * dt;
		}

		if (y == court_floor - radius - 21 && Math.abs(dy) < 5) {
			dy = 0;
			y = court_floor - radius - 21;
		}

		ball.setX(x);
		ball.setY(y);

	}

	// ============================ code above
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
	 * Initializes the images and objects
	 */
	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("Basketballers");
		f.setSize(screen_width, screen_height);
		// f.getContentPane().setBackground(Color.BLACK);

		f.setResizable(false);
		f.addKeyListener(this);

		court = new Court("basketballCourt.png");
		right = new Hoops("rightHoop.png", "right");
		left = new Hoops("leftHoop.png", "left");
		Player1 = new Player("leftStand.png", "rightStand.png", "right.gif", "left.gif", 550, 180);
		Player2 = new Player("leftStand2.png", "rightStand2.png", "right2.gif", "left2.gif", 400, 180);
		ball = new Basketball("basketball.png");
		//music = new Music("BOUNCE+1.wav", c);
		f.add(this);

		// end creating objects
		t = new Timer(100, this);

		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	boolean p1L = false;
	boolean p2R = false;
	boolean p1R = false;
	boolean p2L = false;

	/**
	 * Gets the key code so the players can move in according directions
	 * 
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// ideas --> use an arraylist and once key is pressed add to arraylist and then
		// remove
		if (e.getKeyCode() == 37) {
			Player1.setRight(false);
			Player1.setLeft(true);
			Player1.setRun(true);
			p1L = true;
		}

		if (e.getKeyCode() == 39) {
			Player1.setRight(true);
			Player1.setLeft(false);
			Player1.setRun(true);
			p1R = true;
		}

		if (e.getKeyCode() == 68) {
			p2R = true;
			Player2.setLeft(false);
			Player2.setRight(true);
			Player2.setRun(true);
		}

		if (e.getKeyCode() == 65) {
			p2L = true;
			Player2.setLeft(true);
			Player2.setRight(false);
			Player2.setRun(true);
		}

		System.out.println(e.getKeyCode());
	}

	/**
	 * Once the key is released, set the things back to default
	 * 
	 * @param e
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 37) {
			p1L = false;
		}

		if (e.getKeyCode() == 68) {
			p2R = false;
		}
		if (e.getKeyCode() == 65) {
			p2L = false;
		}
		if (e.getKeyCode() == 39) {
			p1R = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}