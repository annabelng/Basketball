import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Court extends JPanel{

	private int x,y;
	private int width, height;
	private Image img;
	
	public Court(String filename) {
		//sets up board image to be drawn
		String src = new File("").getAbsolutePath() + "/src/";
		ImageIcon ast = new ImageIcon(src + filename);
		this.x = 0;
		this.y = -100;

		img = getImage(filename);
	}

	//method that allows board to be painted in the paint method
	// as opposed to simply being added into the frame
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, x, y, this);
	}

	//getter for the image
	private Image getImage(String path) {

		img = Toolkit.getDefaultToolkit().getImage("basketballCourt.png");
		return img;
	}
	
}
