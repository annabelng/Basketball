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

/**
 * 
 * @author jaidenSmith Sets up the main intro screen with buttons that link to
 *         an instruction page, a how to play page, and a button that exits
 *
 */
public class TitleScreen extends JPanel implements MouseListener, ActionListener {

	// intro music
	Music music;
	Clip c;

	// different buttons on the screen
	JButton start = new JButton("Start");
	JButton quit = new JButton("Quit");
	JButton htp = new JButton("How to Play");

	// JLabel to set up background gif
	JLabel background;
	String screen = "ball.gif";

	// background text
	JLabel basketballers = new JLabel("BASKETBALLERS");

	// initializing size
	int screen_width = 800;
	int screen_height = 800;

	JFrame f;

	Font font = new Font("DIN CONDENSED", 1, 45);

	// paint method
	public void paint(Graphics g) {
		super.paintComponents(g);

	}

	@Override

	/**
	 * Includes the click detection for the Start, Quit, and How to Play Buttons
	 */
	public void actionPerformed(ActionEvent arg0) {
		// update();
		repaint();
		
		//effects of clicking the start button
		if (arg0.getSource() == start) {
			Driver d = new Driver();
			music.getClip().stop();
			f.dispose();
			
		}

		//effects of clicking the quit button
		if (arg0.getSource() == quit) {
			System.exit(0);
			music.getClip().stop();
		}

		//effects of clicking the instructions button
		if (arg0.getSource() == htp) {
			Instructions i = new Instructions();
			music.getClip().stop();
			f.dispose();
		}

	}

	/**
	 * Initializes the images and objects
	 * Sets up all the different buttons and imageIcons
	 */
	public TitleScreen() {
		f = new JFrame();
		f.setTitle("Basketballers");
		f.setSize(screen_width, screen_height);
		f.getContentPane().setBackground(Color.BLACK);

		String src = new File("").getAbsolutePath() + "/src/"; // path to image setup
		ImageIcon backscreen = new ImageIcon(src + screen);

		background = new JLabel(backscreen);

		background.setBounds(0, 0, 800, 800);

		f.setResizable(false);
		f.setLayout(null);
		// f.addKeyListener(this);
		f.addMouseListener(this);

		//initializing music
		music = new Music("NBA 2K12 Intro and Greatest Players Intro.wav", c);
		music.playSong();

		//initializing start button
		start.setBounds(150, 500, 100, 50);
		start.addActionListener(this);

		//initializing how to play button
		htp.setBounds(350, 600, 100, 50);
		htp.addActionListener(this);

		//initializing quit button
		quit.setBounds(550, 500, 100, 50);
		quit.addActionListener(this);

		//add butons to screen
		f.add(start);
		f.add(htp);
		f.add(quit);

		//add text to screen
		basketballers.setBounds(210, 100, 500, 100);
		basketballers.setFont(font);
		basketballers.setForeground(Color.ORANGE);
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