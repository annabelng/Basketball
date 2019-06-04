import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * Background of the game
 * @author annabelng, jaidenSmith
 *
 */
public class Background extends JPanel{

	private int x,y;
	private int width, height;
	private Image img;
	
	/**
	 * Takes in the filename of the object so the 
	 * image and imageIcon can be initialized
	 * @param filename
	 */
	public Background(String filename) {
		//sets up board image to be drawn
		String src = new File("").getAbsolutePath() + "/src/";
		ImageIcon ast = new ImageIcon(src + filename);
		this.x = 0;
		this.y = 0;

		img = getImage(filename);
	}

	/**
	 * method that allows board to be painted in the paint method
	 * as opposed to simply being added into the frame
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, x, y, this);
	}

	/**
	 * Gets the image of the court and returns it
	 * @param path
	 * @return
	 */
	private Image getImage(String path) {

		img = Toolkit.getDefaultToolkit().getImage(path);
		return img;
	}
	
}
