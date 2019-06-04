import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * @author annabelng
 * Represents the Player object
 * Can be used to create both characters in the game
 * Uses still images and gif to mimic movement
 * 
 */
public class Player extends JPanel {

	// x and y location for board
	private int x, y;

	// img variable that will contain board image
	private Image img;
	private boolean run, shooting, checkDribble;
	private boolean right, left;
	private String rightStand, leftStand, rightRun, leftRun, shoot, dribble;
	
	/**
	 * Constructor for the Player object
	 * Should include the images and gifs to the object
	 * Sets up the image icons for the images and initializes beginning location
	 * 
	 * @param leftStand		png file
	 * @param rightStand		png file
	 * @param rightRun		gif file
	 * @param leftRun		gif file
	 */
	public Player(String leftStand, String rightStand, String rightRun, String leftRun, String shoot, String dribble, int x, int y) {
		// sets up board image to be drawn
		run = false;
		String src = new File("").getAbsolutePath() + "/src/";
		ImageIcon ast = new ImageIcon(src + rightStand);
		img = getImg(rightStand);
		this.x = x;
		this.y = y;
		
		this.rightStand = rightStand;
		this.leftStand = leftStand;
		this.rightRun = rightRun;
		this.leftRun = leftRun;
		//this.leftShoot = leftShoot;
		this.shoot = shoot;
		this.dribble = dribble;

	}

	/**
	 * Method that allows images to be painted in the paint method
	 * as opposed to simply being added into the frame
	 * 
	 * Checks if Player is facing left or right based off of direction last run was in
	 * Paints the standing image, not running gif
	 */
	public void stand(Graphics g) {
		if(left == true) {
		img = getImg(leftStand);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, x, y, this);
		}else {
			img = getImg(rightStand);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(img, x, y, this);
		}

	}
	
	public void shoot(Graphics g) {
		img = getImg(shoot);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, x+45, y, this);

	}

	public boolean getRight() {
		return right;
	}
	
	public boolean getLeft() {
		return left;
	}
	/**
	 * Chooses whether to make the player run or stand
	 * @param r
	 */
	public void setRun(boolean r) {
		run = r;
	}
	
	public void setShoot(boolean s) {
		shooting = s;
	}
	
	public boolean getShoot() {
		return shooting;
	}
	
	public void setDribble(boolean d) {
		checkDribble = d;
	}
	
	public boolean getDribble() {
		return checkDribble;
	}

	/**
	 * @return running boolean
	 */
	public boolean getRun() {
		return run;
	}
	
	/**
	 * Paints the running gif and checks for direction
	 * @param g
	 */
	public void run(Graphics g) {
		
		if(left == true) {
		
			if(checkDribble == true) {
				img = getImg(dribble);
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(img, x, y, this);
				if(x>100)
					x-=12;
			}else {
				img = getImg(leftRun);
				
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(img, x, y, this);
				if(x>100)
					x-=12;
			}
		}
		if(right == true) {
			if(checkDribble == true) {
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(img, x, y, this);
				if(x>100)
					x-=12;
			}else {
				img = getImg(rightRun);
				
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(img, x, y, this);
				if(x<1000)
				x+=12;
			}
		}

	}
	
	/**
	 * Sets direction - if left is true and right is false, go left
	 * @param left
	 */
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	/**
	 * Sets direction - if right is true and left is false, go right
	 * @param right
	 */
	public void setRight(boolean right) {
		this.right = right;
	}

	/**
	 * Gets the image filename
	 * @param path
	 * @return
	 */
	private Image getImg(String path) {

		img = Toolkit.getDefaultToolkit().getImage(path);
		return img;
	}

}
