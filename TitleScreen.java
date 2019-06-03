import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TitleScreen extends JPanel implements MouseListener, ActionListener{
	
	Music music;
	Clip c;
	//Background b = new Background("ball.gif");
	
	JButton start = new JButton("Start");
	JButton quit = new JButton("Quit");
	
	JLabel background;
	String screen = "ball.gif";
	
	JLabel basketballers = new JLabel("Basketballers");
	
	int screen_width = 800;
	int screen_height = 700;
	
	JFrame f;
	
	Font font = new Font("Courier New", 1, 50);
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		
		g.setFont(font);
		

	}
	
	public void update() {
		
	}

	@Override

	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
		
		if(arg0.getSource() == start) {
			Driver d = new Driver();
			f.dispose();
		}
		
		if(arg0.getSource() == quit) {
			System.exit(0);
		}

	}

	
	/**
	 * Initializes the images and objects
	 */
	public TitleScreen() {
		f = new JFrame();
		f.setTitle("Basketballers");
		f.setSize(screen_width, screen_height);
		f.getContentPane().setBackground(Color.BLACK);
		
		String src = new File("").getAbsolutePath()+"/src/"; //path to image setup
		ImageIcon backscreen = new ImageIcon(src+screen);
		
		background = new JLabel(backscreen);
		
		background.setBounds(0, 0, 800, 800);

		f.setResizable(false);
		f.setLayout(null);
		//f.addKeyListener(this);
		f.addMouseListener(this);
		
		
		music = new Music("BOUNCE+1.wav", c);
		
		start.setBounds(200, 600, 100, 50);
		start.addActionListener(this);
		
		quit.setBounds(500, 600, 100, 50);
		quit.addActionListener(this);
		
		f.add(start);
		f.add(quit);
		
		basketballers.setBounds(150, 100, 500, 100);
		f.add(basketballers);
		
		f.add(background);
		f.add(this);
		
		

		// end creating objects
		t = new Timer(100, this);

		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TitleScreen t = new TitleScreen();

	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
