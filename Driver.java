import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Driver extends JPanel implements ActionListener, KeyListener { //add more things if you need to
	
	Court court;
	Hoops right, left;
	int screen_width = 1200;
	int screen_height = 600;
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		
		// painting the board png
		court.paint(g);
		right.paint(g);
		left.paint(g);
	}
	public void update() {

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
	
	public Driver(){
		JFrame f = new JFrame();
		f.setTitle("Basketballers");
		f.setSize(screen_width, screen_height);
		f.getContentPane().setBackground(Color.BLACK);
		
		f.setResizable(false);
		f.addKeyListener(this);
		
		court = new Court("basketballCourt.png");
		right = new Hoops("rightHoop.png", "right");
		left = new Hoops("leftHoop.png", "left");
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
	
}