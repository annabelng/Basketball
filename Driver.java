import java.awt.Color;
import java.awt.Font;

import javax.sound.sampled.Clip;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * 
 * @authors annabelNg, jaidenSmith, scottyKrysl, minjunKim
 */
public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener { // add more things if you need to

	/**
	 * Sets up the different objects
	 */
	Court court;
	Background background;
	Hoops right, left;
	Player Player1;
	Player Player2;
	Basketball ball;
	Scoreboard scoreboard;
	
	JLabel scoreV = new JLabel("");
	int visit =0;
	JLabel scoreH = new JLabel("");
	int home =0;
	
	Font font = new Font("Courier New", 1, 50);
	
	Music music;
	Clip c;
	
	int screen_width = 1350;
	int screen_height = 660;

	int court_floor = 440;
	int court_wall = 1245;

	int radius = 30;// size
	int y = court_floor-radius; // ball top left corner
	int x = 300; // ball top left corner
	double dx = 0; // velocity in the x
	double dy = 0; // velocity in the y
	
	double dis = Math.abs(x - 1173);
	double hoopY = 169;
	double hoopXR= 1173;
	int maxShootHeight = 74;

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
		scoreboard.paint(g);
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
		
		if(Player1.getShoot() == true) {
			Player2.shoot(g);
		}

	}

	public void update() {
		// fix ball paint method so that it doesn't repaint the ball after every
		// iteration of the below loops

		ball.update();
		ball.move();
		

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
		f.addMouseListener(this);
		
		background = new Background("background.jpg");
		court = new Court("basketballCourt.png");
		right = new Hoops("rightHoop.png", "right");
		left = new Hoops("leftHoop.png", "left");
		Player1 = new Player("leftStand.png", "rightStand.png", "right.gif", "left.gif", "right1Shoot", 550, 220);
		Player2 = new Player("leftStand2.png", "rightStand2.png", "right2.gif", "left2.gif", "right1Shoot", 400, 220);
		ball = new Basketball("basketball.png");
		music = new Music("BOUNCE+1.wav", c);
		scoreboard = new Scoreboard("scoreboard.jpg");
		
		scoreV.setText(Integer.toString(visit));
		scoreV.setForeground(Color.RED);
		scoreV.setBounds(405, 0, 200, 100);
		f.add(scoreV);
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
	//boolean p1Shoot = false

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
		
		if(e.getKeyCode() == 38) {
			Player1.setShoot(true);
			
		}
		

		if(e.getKeyCode() == 32) {
			dx=30;
			ball.setdx(10);
			time = Math.abs(ball.getX()+ball.radius +1173)/dx;
			
			dy = (ball.getY()-170)/time+ 0.5*gravity*time;
			ball.setdy(-30);
			//time= (Math.abs(hoopXR-ball.getX()))/dx;
			//dy = -gravity +Math.abs(hoopY - ball.getY())/time ;
			
			//dy= -10;
		}
		
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("X:"+e.getX());
		System.out.println("Y:"+e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}