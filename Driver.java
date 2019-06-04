import java.awt.Color;
import javax.sound.sampled.Clip;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
	Background background;
	ArrayList keys = new ArrayList<Integer>();
	
	boolean p1Dribble, p2Dribble;
	Basketball ball;

	// Music music;
	// Clip c;
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
	double time = 0;
	/**
	 * Paints and repaints the images on the board rather than just adding the
	 * images to the JPanel
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		super.paintComponents(g);

		// painting the board png
		background.paint(g);
		court.paint(g);
		right.paint(g);
		left.paint(g);
		ball.paint(g);
		

		
		if (p1L || p1R) {
			Player1.run(g);
			Player1.setRun(false);
		} else if (Player1.getShoot() == true && p2Dribble == false) {
			Player1.setRun(false);
			Player1.shoot(g);
			Player1.setShoot(false);
		} else {
			Player1.stand(g);
		}

		if (p2R || p2L) {
			Player2.run(g);
			Player2.setRun(false);
		} else if (Player2.getShoot() == true && p1Dribble == false) {
			Player2.setRun(false);
			Player2.shoot(g);
			Player2.setShoot(false);
		} else {
			Player2.stand(g);
		
		}
		if(ball.getX()==right.getX() && ball.getY()==right.getY()+75) {
			g.drawString("3POINTER", 0, 0);
			System.out.println("shot!!!");
		}
	}

	public void update() {
		// fix ball paint method so that it doesn't repaint the ball after every
		// iteration of the below loops
		ball.bounce();

		Rectangle bball = new Rectangle(ball.getX(), ball.getY(), 30, 30);
		Rectangle p1 = new Rectangle(Player1.getX(), Player1.getY(), 95, 200);
		Rectangle p2 = new Rectangle(Player2.getX(), Player2.getY(), 95, 200);
		Rectangle rightRim = new Rectangle (right.getX(), right.getY()+50, 30, 30);
		
		if (p1.intersects(bball)) {
			p2Dribble = false;
			System.out.println(Player1.getX());
			if(Player1.getRight()) {
				ball.setX(Player1.getX() + 65);
			}else {
				ball.setX(Player1.getX() - 25);
			}
			ball.setY(Player1.getY() + 50);

			System.out.println("intersection");
			
			ball.dribbleRight(Player1.getY() + 50);
			p1Dribble = true;
		}

		if (p2.intersects(bball)) {
			p1Dribble = false;
			System.out.println(Player2.getX());
			
			if(Player2.getRight()) {
				ball.setX(Player2.getX() + 65);
			}else {
				ball.setX(Player2.getX() - 25);
			}
			
			System.out.println("intersection");
			ball.dribbleLeft(Player2.getY() + 50);
			p2Dribble = true;

		}

		if (p2.intersects(p1) && (p1Dribble == true || p2Dribble == true)) {
			int rand = (int) (Math.random() * 10);
			if (rand >= 3 && rand <= 5) {
				System.out.println("steal");

				if (p1Dribble == true) {
					p1Dribble = false;
					System.out.println("P2 stole the ball");
					x = Player2.getX() + 45;
					y = Player2.getY() + 50;
					
					System.out.println("STEAL");
					ball.setX(x);
					ball.setY(y);
					p2Dribble = true;
				}
				if (p2Dribble == true) {
					p2Dribble = false;
					System.out.println("P1 stole the ball");
					x = Player1.getX() + 45;
					y = Player1.getY() + 50;

					System.out.println("STEAL");
					ball.setX(x);
					ball.setY(y);
					p1Dribble = true;
				}
			}

		}
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

		background = new Background("background.jpg");
		court = new Court("basketballCourt.png");
		right = new Hoops("rightHoop.png", "right");
		left = new Hoops("leftHoop.png", "left");
		Player1 = new Player("leftStand.png", "rightStand.png", "right1.gif", "left.gif", "rightShoot.gif",
				"leftDribble.png", 550, 180);
		Player2 = new Player("leftStand2.png", "rightStand3.png", "right2.gif", "left2.gif", "leftShoot.gif",
				"leftDribble.gif", 400, 180);
		ball = new Basketball("basketball.png");
		// music = new Music("BOUNCE+1.wav", c);
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
	// boolean p1Shoot = false

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

		if (e.getKeyCode() == 38) {
			Player1.setLeft(false);
			Player1.setRight(true);
			Player1.setRun(false);
			Player1.setShoot(true);
			ball.shoot(5);
		}

		if (e.getKeyCode() == 87) {
			Player2.setLeft(true);
			Player2.setRight(false);
			Player2.setRun(false);
			Player2.setShoot(true);
			ball.shoot(-5);
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
		for(int i = 0; i < keys.size(); i++) {
			if(keys.get(i).equals(37)) {
				keys.remove(i);
			}
			if(keys.get(i).equals(68)) {
				keys.remove(i);
			}
			if(keys.get(i).equals(65)) {
				keys.remove(i);
			}
			if(keys.get(i).equals(39)) {
				keys.remove(i);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}