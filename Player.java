import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Player extends JPanel {

	// x and y location for board
	private int x, y;

	// img variable that will contain board image
	private Image img, img2;
	private boolean run;
	private boolean right, left;
	private String rightStand, leftStand, rightRun, leftRun;
	
	public Player(String rightStand, String leftStand, String rightRun, String leftRun) {
		// sets up board image to be drawn
		run = false;
		String src = new File("").getAbsolutePath() + "/src/";
		ImageIcon ast = new ImageIcon(src + rightStand);
		img = getImg(rightStand);
		this.x = 550;
		this.y = 180;
		
		this.rightStand = rightStand;
		this.leftStand = leftStand;
		this.rightRun = rightRun;
		this.leftRun = leftRun;

	}

	// method that allows board to be painted in the paint method
	// as opposed to simply being added into the frame
	public void paint(Graphics g) {
		if(left == true) {
		img = getImg("leftStand.png");
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, x, y, this);
		}else {
			img = getImg("rightStand.png");
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(img, x, y, this);
		}

	}

	public void setRun(boolean r) {
		run = r;
	}

	public boolean getRun() {
		return run;
	}
	
	public void run(Graphics g) {
		/*String src2 = new File("").getAbsolutePath() + "/src2/";
		ImageIcon ast2 = new ImageIcon(src2 + "giphy.gif");*/
		if(left == true) {
		img2 = getImg("left.gif");
		
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img2, x, y, this);
		if(x>100)
		x-=12;
		}
		if(right == true) {
			img2 = getImg("right.gif");
			
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(img2, x, y, this);
			if(x<1200)
			x+=12;
		}

	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}

	// getter for the image
	private Image getImg(String path) {

		img = Toolkit.getDefaultToolkit().getImage(path);
		return img;
	}

}
