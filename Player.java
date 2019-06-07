import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author annabelng Represents the Player object Can be used to create both
 *         characters in the game Uses various still images and gif to mimic
 *         movement and animation throughout the game
 * 
 */
public class Player extends JPanel {

	// x and y location for board
	private int x, y;

	// img variable that will contain board image
	private Image img;

	// various booleans that check for different actions
	// sets off different flags to check which image to display on screen
	private boolean run, shooting, checkDribble;
	private boolean right, left;

	// Strings to contain the filenames of the various gifs and pngs
	private String rightStand, leftStand, rightRun, leftRun, shoot, dribble;

	/**
	 * Constructor for the Player object Should include the images and gifs to the
	 * object Sets up the image icons for the images and initializes beginning
	 * location
	 * 
	 * @param leftStand
	 *            png file
	 * @param rightStand
	 *            png file
	 * @param rightRun
	 *            gif file
	 * @param leftRun
	 *            gif file
	 */
	public Player(String leftStand, String rightStand, String rightRun, String leftRun, String shoot, String dribble,
			int x, int y) {
		// sets run boolean to false so player starts off standing
		run = false;

		// sets up image of the player standing
		String src = new File("").getAbsolutePath() + "/src/";
		ImageIcon ast = new ImageIcon(src + rightStand);
		img = getImg(rightStand);

		// initializes x and y variables
		this.x = x;
		this.y = y;

		// initializes the rest of the gifs and pngs
		this.rightStand = rightStand;
		this.leftStand = leftStand;
		this.rightRun = rightRun;
		this.leftRun = leftRun;
		this.shoot = shoot;
		this.dribble = dribble;

	}

	/**
	 * Method that allows images to be painted in the paint method as opposed to
	 * simply being added into the frame
	 * 
	 * Checks if Player is facing left or right based off of direction last run was
	 * in Paints the standing image, not running gif
	 */
	public void stand(Graphics g) {
		if (left == true) {
			img = getImg(leftStand);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(img, x, y, this);
		} else {
			img = getImg(rightStand);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(img, x, y, this);
		}

	}

	/**
	 * Changes the image to the shooting gif Simulates the shooting movement
	 * 
	 * @param g
	 */
	public void shoot(Graphics g) {
		img = getImg(shoot);

		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, x, y, this);

	}

	/**
	 * Getters to check which direction the player is facing so the correct png and
	 * gif can be drawn
	 * 
	 * @return
	 */
	public boolean getRight() {
		return right;
	}

	/**
	 * Getters to check which direction the player is facing so the correct png and
	 * gif can be drawn
	 * 
	 * @return
	 */
	public boolean getLeft() {
		return left;
	}

	/**
	 * Chooses whether to make the player run or stand
	 * 
	 * @param r
	 */
	public void setRun(boolean r) {
		run = r;
	}

	/**
	 * Boolean to check whether player is shooting or not If player is shooting, the
	 * shoot method will be called in driver
	 * 
	 * @param s
	 */
	public void setShoot(boolean s) {
		shooting = s;
	}

	/**
	 * Getter for the shoot boolean
	 * 
	 * @return
	 */
	public boolean getShoot() {
		return shooting;
	}

	/**
	 * Setter for dribble boolean
	 * 
	 * If set to true, the image will display the dribbling gif
	 * 
	 * @param d
	 */
	public void setDribble(boolean d) {
		checkDribble = d;
	}

	/**
	 * getter for the dribble boolean
	 * 
	 * @return
	 */
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
	 * Paints the running gif and checks for direction Also changes the x to
	 * simulate motion Checks for the court boundaries
	 * 
	 * @param g
	 */
	public void run(Graphics g) {

		// checks which direction player is facing
		if (left == true) {

			// if player is dribbling, change image to dribbling gif
			if (checkDribble == true) {
				img = getImg(dribble);
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(img, x, y, this);
				if (x > 100)
					x -= 12;
				// else, player is running without the ball, so code needs to set up the running
				// gif
			} else {
				img = getImg(leftRun);

				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(img, x, y, this);
				if (x > 100)
					x -= 12;
			}
		}
		// checks which direction player is facing
		if (right == true) {
			// if player is dribbling, change image to dribbling gif
			if (checkDribble == true) {
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(img, x, y, this);
				if (x > 100)
					x -= 12;
				// else, player is running without the ball, so code needs to set up the running
				// gif
			} else {
				img = getImg(rightRun);

				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(img, x, y, this);
				if (x < 1100)
					x += 12;
			}
		}

	}

	/**
	 * Sets direction - if left is true and right is false, go left
	 * 
	 * @param left
	 */
	public void setLeft(boolean left) {
		this.left = left;
	}

	/**
	 * Getter for x variable
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * getter for y variable
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Sets direction - if right is true and left is false, go right
	 * 
	 * @param right
	 */
	public void setRight(boolean right) {
		this.right = right;
	}

	/**
	 * Gets the image filename
	 * 
	 * @param path
	 * @return img
	 */
	private Image getImg(String path) {

		img = Toolkit.getDefaultToolkit().getImage(path);
		return img;
	}
	
	/**
	 * Setting x variable
	 * @param x
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Setting y variable
	 * @param y
	 */
	public void setY(int y){
		this.y = y;
	}

}
